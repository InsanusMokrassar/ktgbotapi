package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface PrivateChat : Chat, UsernameChat {
    override val id: UserId
    val firstName: String
    val lastName: String
}
