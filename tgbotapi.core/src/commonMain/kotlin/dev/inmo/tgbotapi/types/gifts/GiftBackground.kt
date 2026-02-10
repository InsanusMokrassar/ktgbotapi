package dev.inmo.tgbotapi.types.gifts

import dev.inmo.tgbotapi.types.backgroundField
import dev.inmo.tgbotapi.types.files.PathedFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GiftBackground(
    @SerialName(backgroundField)
    val background: PathedFile
)
