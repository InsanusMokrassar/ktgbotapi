package dev.inmo.tgbotapi.types.chat.abstracts.extended

import dev.inmo.tgbotapi.types.chat.ChatPermissions
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat

interface ExtendedGroupChat : GroupChat, ExtendedPublicChat {
    val permissions: ChatPermissions
}