package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.ChatPhoto
import dev.inmo.tgbotapi.types.chat.ExtendedChatSerializer
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedChat : Chat {
    val chatPhoto: ChatPhoto?
}