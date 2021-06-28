package dev.inmo.tgbotapi.extensions.utils.extensions

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
fun Update.sourceChat(): Chat? = when (this) {
    is MediaGroupUpdate -> when (this) {
        is SentMediaGroupUpdate -> data.chat
        is EditMediaGroupUpdate -> data.chat
        else -> null
    }
    is BaseMessageUpdate -> data.chat
    is InlineQueryUpdate -> data.from
    is ChosenInlineResultUpdate -> data.user
    is CallbackQueryUpdate -> data.user
    is PreCheckoutQueryUpdate -> data.user
    is PollAnswerUpdate -> data.user
    is ShippingQueryUpdate -> data.user
    else -> null
}

@PreviewFeature
fun Update.sourceUser(): User? = when (this) {
    is MediaGroupUpdate -> when (this) {
        is SentMediaGroupUpdate -> data.chat?.asUser()
        is EditMediaGroupUpdate -> data.chat.asUser()
        else -> null
    }
    is BaseMessageUpdate -> data.chat.asUser()
    is InlineQueryUpdate -> data.from
    is ChosenInlineResultUpdate -> data.user
    is CallbackQueryUpdate -> data.user
    is PreCheckoutQueryUpdate -> data.user
    is PollAnswerUpdate -> data.user
    is ShippingQueryUpdate -> data.user
    else -> null
}
