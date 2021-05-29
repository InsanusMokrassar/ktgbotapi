package dev.inmo.tgbotapi.types.dice

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(DiceAnimationTypeSerializer::class)
sealed interface DiceAnimationType {
    val emoji: String
    val valueLimits: IntRange
}
@Serializable(DiceAnimationTypeSerializer::class)
object CubeDiceAnimationType : DiceAnimationType {
    override val emoji: String = "\uD83C\uDFB2"
    override val valueLimits: IntRange
        get() = dartsCubeAndBowlingDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
object DartsDiceAnimationType : DiceAnimationType {
    override val emoji: String = "\uD83C\uDFAF"
    override val valueLimits: IntRange
        get() = dartsCubeAndBowlingDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
object BasketballDiceAnimationType : DiceAnimationType {
    override val emoji: String = "\uD83C\uDFC0"
    override val valueLimits: IntRange
        get() = basketballAndFootballDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
object FootballDiceAnimationType : DiceAnimationType {
    override val emoji: String = "⚽"
    override val valueLimits: IntRange
        get() = basketballAndFootballDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
object BowlingDiceAnimationType : DiceAnimationType {
    override val emoji: String = "\uD83C\uDFB3"
    override val valueLimits: IntRange
        get() = dartsCubeAndBowlingDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
object SlotMachineDiceAnimationType : DiceAnimationType {
    override val emoji: String = "\uD83C\uDFB0"
    override val valueLimits: IntRange
        get() = slotMachineDiceResultLimit
}
@Serializable(DiceAnimationTypeSerializer::class)
data class CustomDiceAnimationType(
    override val emoji: String
) : DiceAnimationType {
    override val valueLimits: IntRange
        get() = error("Custom dice animation type have unknown value limits")
}

@Serializer(DiceAnimationType::class)
internal object DiceAnimationTypeSerializer : KSerializer<DiceAnimationType> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DiceAnimationType", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): DiceAnimationType {
        return when (val type = decoder.decodeString()) {
            CubeDiceAnimationType.emoji -> CubeDiceAnimationType
            DartsDiceAnimationType.emoji -> DartsDiceAnimationType
            BasketballDiceAnimationType.emoji -> BasketballDiceAnimationType
            SlotMachineDiceAnimationType.emoji -> SlotMachineDiceAnimationType
            FootballDiceAnimationType.emoji -> FootballDiceAnimationType
            BowlingDiceAnimationType.emoji -> BowlingDiceAnimationType
            else -> CustomDiceAnimationType(type)
        }
    }

    override fun serialize(encoder: Encoder, value: DiceAnimationType) {
        encoder.encodeString(value.emoji)
    }
}
