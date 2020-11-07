package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.ExtendedChatSerializer
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import kotlinx.serialization.Serializable

@Serializable(ExtendedChatSerializer::class)
interface ExtendedGroupChat : GroupChat, ExtendedPublicChat {
    val permissions: ChatPermissions
}