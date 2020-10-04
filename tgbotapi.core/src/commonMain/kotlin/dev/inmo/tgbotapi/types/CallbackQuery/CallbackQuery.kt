package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.User

interface CallbackQuery {
    val id: CallbackQueryIdentifier
    val user: User
    val chatInstance: String
}

data class UnknownCallbackQueryType(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    val raw: String
) : CallbackQuery
