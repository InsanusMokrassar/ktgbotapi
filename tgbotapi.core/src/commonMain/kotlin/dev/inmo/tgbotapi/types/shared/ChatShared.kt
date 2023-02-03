package dev.inmo.tgbotapi.types.shared

import dev.inmo.tgbotapi.types.Identifier
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.requestIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatShared(
    @SerialName(requestIdField)
    val requestId: Identifier,

    @SerialName(chatIdField)
    val chatId: Identifier,
)
