package com.github.insanusmokrassar.TelegramBotAPI.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatPhoto(
    @SerialName("small_file_id")
    val smallFileId: String,
    @SerialName("big_file_id")
    val bigFileId: String
)
