package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.Serializable

@Serializable(KeyboardMarkupSerializer::class)
@ClassCastsIncluded
sealed interface KeyboardMarkup
