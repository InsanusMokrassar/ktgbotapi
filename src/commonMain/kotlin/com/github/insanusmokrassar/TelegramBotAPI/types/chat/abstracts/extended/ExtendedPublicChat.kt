package com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PublicChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializer
import kotlinx.serialization.Serializable

interface ExtendedPublicChat : ExtendedChat, PublicChat {
    val description: String
    val inviteLink: String?
    @Serializable(TelegramBotAPIMessageDeserializeOnlySerializer::class)
    val pinnedMessage: Message?
}