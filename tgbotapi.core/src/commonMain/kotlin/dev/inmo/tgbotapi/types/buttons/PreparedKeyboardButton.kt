package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.idField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreparedKeyboardButton(
    @SerialName(idField)
    val id: PreparedKeyboardId
)
