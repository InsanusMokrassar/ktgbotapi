package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPhoto(
    @SerialName(smallFileIdField)
    val smallFileId: String,
    @SerialName(bigFileIdField)
    val bigFileId: String,
    @SerialName(smallFileUniqueIdField)
    val smallFileUniqueId: TgFileUniqueId,
    @SerialName(bigFileUniqueIdField)
    val bigFileUniqueId: TgFileUniqueId,
)
