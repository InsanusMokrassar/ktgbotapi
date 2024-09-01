package dev.inmo.tgbotapi.utils.serializers

import dev.inmo.micro_utils.common.Optional
import dev.inmo.micro_utils.common.onPresented
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
        fun deserializeOrNull(json: JsonElement): Optional<T>
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

    /**
     * Trying to get [JsonElement] if [decoder] is [JsonDecoder]. Then it will use [defaultDeserializeCallback] to
     * deserialize data. In case if [defaultDeserializeCallback] will throw exception it will firstly try to deserialize
     * data by strategies from [customDeserializationStrategies] and, if no one will return presented data in [Optional]
     * it will use [fallbackDeserialization] as last option to deserialize data
     */
    override fun deserialize(decoder: Decoder): T {
        val jsonDecoder = decoder as? JsonDecoder
        val jsonElement = jsonDecoder ?.decodeJsonElement()
        return runCatching {
            defaultDeserializeCallback(decoder, jsonElement)
        }.getOrElse {
            (jsonElement ?.let {
                customDeserializationStrategies.forEach {
                    it.deserializeOrNull(jsonElement).onPresented {
                        return@deserialize it
                    }
                }
            })
            fallbackDeserialization(it, decoder, jsonElement)
        }
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
