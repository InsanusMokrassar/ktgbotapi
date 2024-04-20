package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.messageField
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.types.titleField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusinessIntro(
    @SerialName(titleField)
    val title: String? = null,
    @SerialName(messageField)
    val message: String? = null,
    @SerialName(stickerField)
    val sticker: Sticker? = null,
)
