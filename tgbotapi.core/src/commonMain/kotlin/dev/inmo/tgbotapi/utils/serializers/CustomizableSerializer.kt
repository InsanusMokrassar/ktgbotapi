package dev.inmo.tgbotapi.utils.serializers

import dev.inmo.tgbotapi.types.update.RawUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.types.update.abstracts.UpdateDeserializationStrategy.deserialize
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement

interface CustomizableSerializer<T> : KSerializer<T>, CustomizableSerializationStrategy<T>, CustomizableDeserializationStrategy<T> {
}

open class CallbacksCustomizableDeserializationStrategy<T>(
    override val descriptor: SerialDescriptor,
    defaultDeserializeCallback: (decoder: Decoder, jsonElement: JsonElement?) -> T,
    defaultSerializeCallback: (encoder: Encoder, value: T) -> Unit,
    fallbackDeserialization: (initialException: Throwable, decoder: Decoder, jsonElement: JsonElement?) -> T = { initialException, _, _ -> throw initialException },
    fallbackSerialization: (initialException: Throwable, encoder: Encoder, value: T) -> T = { initialException, _, _ -> throw initialException }
) : CustomizableSerializer<T>,
    CustomizableSerializationStrategy<T> by CallbackCustomizableSerializationStrategy(descriptor, defaultSerializeCallback, fallbackSerialization),
    CustomizableDeserializationStrategy<T> by CallbackCustomizableDeserializationStrategy(descriptor, defaultDeserializeCallback, fallbackDeserialization){
}
