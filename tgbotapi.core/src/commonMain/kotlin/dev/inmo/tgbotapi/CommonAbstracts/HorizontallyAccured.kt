package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.Meters

interface HorizontallyAccured {
    val horizontalAccuracy: Meters?
}
