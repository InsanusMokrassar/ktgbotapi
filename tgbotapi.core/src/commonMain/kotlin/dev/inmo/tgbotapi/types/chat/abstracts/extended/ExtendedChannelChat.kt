package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.chat.ExtendedChatSerializer
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedChannelChat : ChannelChat, ExtendedPublicChat {
    val linkedGroupChatId: ChatId?
}
