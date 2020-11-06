package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface PrivateChat : Chat, UsernameChat {
    val firstName: String
    val lastName: String
}
