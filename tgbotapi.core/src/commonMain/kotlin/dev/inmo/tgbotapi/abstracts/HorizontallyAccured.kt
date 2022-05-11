package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.Meters

interface HorizontallyAccured {
    val horizontalAccuracy: Meters?
}
