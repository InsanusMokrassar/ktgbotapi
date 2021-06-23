package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.ChatPhoto
import dev.inmo.tgbotapi.types.chat.ExtendedChatSerializer
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedChat : Chat {
    val chatPhoto: ChatPhoto?
}

data class UnknownExtendedChat(
    override val id: ChatId,
    val raw: String
) : ExtendedChat {
    override val chatPhoto: ChatPhoto? = null
}
