package com.github.insanusmokrassar.TelegramBotAPI.types.dice

import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(DiceAnimationTypeSerializer::class)
sealed class DiceAnimationType {
    abstract val emoji: String
}
@Serializable(DiceAnimationTypeSerializer::class)
object CubeDiceAnimationType : DiceAnimationType() {
    override val emoji: String = "\uD83C\uDFB2"
}
@Serializable(DiceAnimationTypeSerializer::class)
object DartsDiceAnimationType : DiceAnimationType() {
    override val emoji: String = "\uD83C\uDFAF"
}
@Serializable(DiceAnimationTypeSerializer::class)
object BasketballDiceAnimationType : DiceAnimationType() {
    override val emoji: String = "\uD83C\uDFC0"
}
@Serializable(DiceAnimationTypeSerializer::class)
data class CustomDiceAnimationType(
    override val emoji: String
) : DiceAnimationType()

@Serializer(DiceAnimationType::class)
internal object DiceAnimationTypeSerializer : KSerializer<DiceAnimationType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DiceAnimationType", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): DiceAnimationType {
        return when (val type = decoder.decodeString()) {
            CubeDiceAnimationType.emoji -> CubeDiceAnimationType
            DartsDiceAnimationType.emoji -> DartsDiceAnimationType
            BasketballDiceAnimationType.emoji -> BasketballDiceAnimationType
            else -> CustomDiceAnimationType(type)
        }
    }

    override fun serialize(encoder: Encoder, value: DiceAnimationType) {
        encoder.encodeString(value.emoji)
    }
}
