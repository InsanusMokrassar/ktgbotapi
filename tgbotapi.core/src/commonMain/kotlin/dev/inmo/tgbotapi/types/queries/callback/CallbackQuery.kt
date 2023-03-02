package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User

sealed interface CallbackQuery : FromUser {
    val id: CallbackQueryIdentifier
    val chatInstance: String
    override val from: CommonUser
    override val user: CommonUser
        get() = from
}

data class UnknownCallbackQueryType(
    override val id: CallbackQueryIdentifier,
    override val from: CommonUser,
    override val chatInstance: String,
    val raw: String
) : CallbackQuery
