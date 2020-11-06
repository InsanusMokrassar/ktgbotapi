package dev.inmo.tgbotapi.types.chat.abstracts

import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.PreviewChatSerializer
import kotlinx.serialization.Serializable

@Serializable(PreviewChatSerializer::class)
interface UsernameChat : Chat {
    val username: Username?
}
