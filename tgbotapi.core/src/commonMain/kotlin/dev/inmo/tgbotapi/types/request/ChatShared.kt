package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chatIdField
import dev.inmo.tgbotapi.types.requestIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(chatIdField)
    override val chatId: ChatId
) : ChatSharedRequest
