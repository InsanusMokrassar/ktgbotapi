package dev.inmo.tgbotapi.utils.serializers

import dev.inmo.tgbotapi.types.update.RawUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy.deserialize
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Encoder

interface CustomizableSerializationStrategy<T> : SerializationStrategy<T> {
    fun interface CustomSerializerStrategy<T> {
        fun optionallySerialize(
            encoder: Encoder,
            value: T,
        ): Boolean
    }

    /**
     * Contains [CustomSerializerStrategy] which will be used in [Serialize] method when standard
     * [RawUpdate] serializer will be unable to create [RawUpdate] (and [Update] as well)
     */
    val customSerializationStrategies: Set<CustomSerializerStrategy<T>>

    /**
     * Adding [deserializationStrategy] into [customSerializationStrategies] for using in case of unknown update
     */
    fun addUpdateSerializationStrategy(deserializationStrategy: CustomSerializerStrategy<T>): Boolean

    /**
     * Removing [deserializationStrategy] from [customSerializationStrategies]
     */
    fun removeUpdateSerializationStrategy(deserializationStrategy: CustomSerializerStrategy<T>): Boolean
}

/**
 * @param defaultSerializeCallback Default way of serialization
 * @param fallbackSerialization Fallback way which will be used in case when [defaultSerializeCallback] and [customSerializationStrategies]
 * were unable to serialize data
 */
open class CallbackCustomizableSerializationStrategy<T>(
    override val descriptor: SerialDescriptor,
    private val defaultSerializeCallback: (encoder: Encoder, value: T) -> Unit,
    private val fallbackSerialization: (
        initialException: Throwable,
        encoder: Encoder,
        value: T,
    ) -> T = { initialException, _, _ -> throw initialException },
) : CustomizableSerializationStrategy<T> {
    protected val _customSerializationStrategies = LinkedHashSet<CustomizableSerializationStrategy.CustomSerializerStrategy<T>>()

    /**
     * Contains [JsonSerializerStrategy] which will be used in [deserialize] method when standard
     * [RawUpdate] serializer will be unable to create [RawUpdate] (and [Update] as well)
     */
    override val customSerializationStrategies: Set<CustomizableSerializationStrategy.CustomSerializerStrategy<T>>
        get() = _customSerializationStrategies.toSet()

    /**
     * Trying to serialize data by [defaultSerializeCallback]. If [defaultSerializeCallback] it will try to use each
     * strategy from [customSerializationStrategies] until one of them will return true (means, serialized). If there
     * are no any strategy success serialization and [defaultSerializeCallback] thrown exception will be used
     * [fallbackSerialization] callback
     */
    override fun serialize(
        encoder: Encoder,
        value: T,
    ) {
        runCatching {
            defaultSerializeCallback(encoder, value)
        }.getOrElse {
            customSerializationStrategies.forEach {
                if (it.optionallySerialize(encoder, value)) {
                    return@getOrElse
                }
            }
            // next line will be called onle in case all
            fallbackSerialization(it, encoder, value)
        }
    }

    /**
     * Adding [deserializationStrategy] into [customSerializationStrategies] for using in case of unknown update
     */
    override fun addUpdateSerializationStrategy(
        deserializationStrategy: CustomizableSerializationStrategy.CustomSerializerStrategy<T>,
    ): Boolean = _customSerializationStrategies.add(deserializationStrategy)

    /**
     * Removing [deserializationStrategy] from [customSerializationStrategies]
     */
    override fun removeUpdateSerializationStrategy(
        deserializationStrategy: CustomizableSerializationStrategy.CustomSerializerStrategy<T>,
    ): Boolean = _customSerializationStrategies.remove(deserializationStrategy)
}
