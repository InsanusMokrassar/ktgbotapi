package com.github.insanusmokrassar.TelegramBotAPI.types.dice

import kotlinx.serialization.*

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
class UnknownDiceAnimationType(
    override val emoji: String
) : DiceAnimationType()

@Serializer(DiceAnimationType::class)
internal object DiceAnimationTypeSerializer : KSerializer<DiceAnimationType> {
    override val descriptor: SerialDescriptor = PrimitiveDescriptor("DiceAnimationType", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): DiceAnimationType {
        return when (val type = decoder.decodeString()) {
            CubeDiceAnimationType.emoji -> CubeDiceAnimationType
            DartsDiceAnimationType.emoji -> DartsDiceAnimationType
            else -> UnknownDiceAnimationType(type)
        }
    }

    override fun serialize(encoder: Encoder, value: DiceAnimationType) {
        encoder.encodeString(value.emoji)
    }
}
