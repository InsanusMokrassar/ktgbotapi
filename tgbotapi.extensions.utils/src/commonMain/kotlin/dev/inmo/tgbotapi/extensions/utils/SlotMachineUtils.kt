package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.DiceResult
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

enum class SlotMachineReelImages {
    BAR, BERRIES, LEMON, SEVEN
}
val Int.asSlotMachineReelImage
    get() = when (this) {
        0 -> SlotMachineReelImages.BAR
        1 -> SlotMachineReelImages.BERRIES
        2 -> SlotMachineReelImages.LEMON
        else -> SlotMachineReelImages.SEVEN
    }

@Serializable
data class SlotMachineResult(
    val rawValue: DiceResult
) {
    @Transient
    val left = rawValue and 3
    @Transient
    val center = rawValue shr 2 and 3
    @Transient
    val right = rawValue shr 4

    @Transient
    val leftReel = left.asSlotMachineReelImage
    @Transient
    val centerReel = center.asSlotMachineReelImage
    @Transient
    val rightReel = right.asSlotMachineReelImage
}

fun Dice.calculateSlotMachineResult() = if (animationType == SlotMachineDiceAnimationType) {
    SlotMachineResult(value - 1)
} else {
    null
}
