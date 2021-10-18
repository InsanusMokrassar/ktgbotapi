package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.CommonAbstracts.WithUser
import dev.inmo.tgbotapi.extensions.utils.asFromUser
import dev.inmo.tgbotapi.extensions.utils.asUser
import dev.inmo.tgbotapi.extensions.utils.shortcuts.chat
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
fun Update.sourceChat(): Chat? = when {
    this is MediaGroupUpdate -> when (this) {
        is SentMediaGroupUpdate -> data.chat
        is EditMediaGroupUpdate -> data.chat
    }
    this is BaseMessageUpdate -> data.chat
    else -> {
        when (val data = data) {
            is FromUser -> data.from
            is WithUser -> data.user
            else -> null
        }
    }
}

@PreviewFeature
fun Update.sourceUser(): User? = when (val data = data) {
    is FromUser -> data.from
    is WithUser -> data.user
    else -> sourceChat()?.asUser()
}
