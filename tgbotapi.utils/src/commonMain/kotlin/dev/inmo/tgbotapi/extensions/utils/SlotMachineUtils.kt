package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.DiceResult
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.dice.SlotMachineDiceAnimationType
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * @param text Is a text representation
 * @param number Internal representation of reel
 */
enum class SlotMachineReelImage(val text: String, val number: Int) {
    BAR("[bar]", 0),
    BERRIES("\uD83C\uDF52", 1),
    LEMON("\uD83C\uDF4B", 2),
    SEVEN("7", 3),
}

/**
 * @return First [SlotMachineReelImage] with [SlotMachineReelImage.number] equal to receiver OR [SlotMachineReelImage.SEVEN]
 */
val Int.asSlotMachineReelImage
    get() = SlotMachineReelImage.values().firstOrNull { it.number == this } ?: SlotMachineReelImage.SEVEN

/**
 * @return First [SlotMachineReelImage] with [SlotMachineReelImage.text] equal to receiver OR [SlotMachineReelImage.SEVEN]
 */
val String.asSlotMachineReelImage
    get() = SlotMachineReelImage.values().firstOrNull { it.text == this } ?: SlotMachineReelImage.SEVEN

@Serializable
data class SlotMachineResult(
    val rawValue: DiceResult,
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
