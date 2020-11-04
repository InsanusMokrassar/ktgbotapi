package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.chat.abstracts.ChannelChat

interface ExtendedChannelChat : ChannelChat, ExtendedPublicChat {
    val linkedGroupChatId: ChatId?
}
