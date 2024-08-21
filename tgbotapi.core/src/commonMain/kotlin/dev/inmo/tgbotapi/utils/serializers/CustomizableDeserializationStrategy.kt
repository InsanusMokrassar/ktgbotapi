package dev.inmo.tgbotapi.utils.serializers

import dev.inmo.tgbotapi.types.update.RawUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy.deserialize
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

interface CustomizableDeserializationStrategy<T> : DeserializationStrategy<T> {
    fun interface JsonDeserializerStrategy<T> {
        fun deserializeOrNull(json: JsonElement): T?
    }
    /**
     * Contains [JsonDeserializerStrategy] which will be used in [deserialize] method when standard
     * [RawUpdate] serializer will be unable to create [RawUpdate] (and [Update] as well)
     */
    val customDeserializationStrategies: Set<JsonDeserializerStrategy<T>>

    /**
     * Adding [deserializationStrategy] into [customDeserializationStrategies] for using in case of unknown update
     */
    fun addUpdateDeserializationStrategy(
        deserializationStrategy: JsonDeserializerStrategy<T>
    ): Boolean

    /**
     * Removing [deserializationStrategy] from [customDeserializationStrategies]
     */
    fun removeUpdateDeserializationStrategy(
        deserializationStrategy: JsonDeserializerStrategy<T>
    ): Boolean
}

open class CallbackCustomizableDeserializationStrategy<T>(
    override val descriptor: SerialDescriptor,
    private val defaultDeserializeCallback: (decoder: Decoder, jsonElement: JsonElement?) -> T,
    private val fallbackDeserialization: (initialException: Throwable, decoder: Decoder, jsonElement: JsonElement?) -> T = { initialException, _, _ -> throw initialException }
) : CustomizableDeserializationStrategy<T> {
    protected val _customDeserializationStrategies = LinkedHashSet<CustomizableDeserializationStrategy.JsonDeserializerStrategy<T>>()

    /**
     * Contains [JsonDeserializerStrategy] which will be used in [deserialize] method when standard
     * [RawUpdate] serializer will be unable to create [RawUpdate] (and [Update] as well)
     */
    override val customDeserializationStrategies: Set<CustomizableDeserializationStrategy.JsonDeserializerStrategy<T>>
        get() = _customDeserializationStrategies.toSet()

    override fun deserialize(decoder: Decoder): T {
        val jsonDecoder = decoder as? JsonDecoder
        val jsonElement = jsonDecoder ?.decodeJsonElement()
        return runCatching {
            defaultDeserializeCallback(decoder, jsonElement)
        }.onFailure {
            return (jsonElement ?.let {
                customDeserializationStrategies.firstNotNullOfOrNull {
                    it.deserializeOrNull(jsonElement)
                }
            }) ?: fallbackDeserialization(it, decoder, jsonElement)
        }.getOrThrow()
    }

    /**
     * Adding [deserializationStrategy] into [customDeserializationStrategies] for using in case of unknown update
     */
    override fun addUpdateDeserializationStrategy(
        deserializationStrategy: CustomizableDeserializationStrategy.JsonDeserializerStrategy<T>
    ): Boolean = _customDeserializationStrategies.add(deserializationStrategy)

    /**
     * Removing [deserializationStrategy] from [customDeserializationStrategies]
     */
    override fun removeUpdateDeserializationStrategy(
        deserializationStrategy: CustomizableDeserializationStrategy.JsonDeserializerStrategy<T>
    ): Boolean = _customDeserializationStrategies.remove(deserializationStrategy)
}
