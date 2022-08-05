package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.ksp.lib.ClassCastsIncluded
import kotlinx.serialization.Serializable

@Serializable(KeyboardMarkupSerializer::class)
@ClassCastsIncluded
sealed interface KeyboardMarkup
