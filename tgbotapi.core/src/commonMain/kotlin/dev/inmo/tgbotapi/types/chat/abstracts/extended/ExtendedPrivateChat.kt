package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.chat.ExtendedChatSerializer
import dev.inmo.tgbotapi.types.chat.abstracts.PrivateChat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedPrivateChat : PrivateChat, ExtendedChat {
    val bio: String
    val hasPrivateForwards: Boolean

    val allowCreateUserIdLink: Boolean
        get() = hasPrivateForwards
}
