package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface Chat {
    val id: ChatId
}

data class UnknownChatType(
    override val id: ChatId,
    val raw: String
) : Chat
