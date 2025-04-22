package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.chat.CommonUser

sealed interface CallbackQuery : FromUser {
    val id: CallbackQueryId
    val chatInstance: String
    override val from: CommonUser
    override val user: CommonUser
        get() = from
}

data class UnknownCallbackQueryType(
    override val id: CallbackQueryId,
    override val from: CommonUser,
    override val chatInstance: String,
    val raw: String,
) : CallbackQuery
