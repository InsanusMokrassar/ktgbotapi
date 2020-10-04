package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface Chat {
    val id: ChatId
}

data class UnknownChatType(
    override val id: ChatId,
    val raw: String
) : Chat
