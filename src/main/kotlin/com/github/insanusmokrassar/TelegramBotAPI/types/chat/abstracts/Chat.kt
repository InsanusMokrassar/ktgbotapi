package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface Chat {
    val id: ChatId
}