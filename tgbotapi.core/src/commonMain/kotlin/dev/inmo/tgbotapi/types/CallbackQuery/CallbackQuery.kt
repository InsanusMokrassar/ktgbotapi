package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.User

sealed interface CallbackQuery : FromUser {
    val id: CallbackQueryIdentifier
    val chatInstance: String
}

data class UnknownCallbackQueryType(
    override val id: CallbackQueryIdentifier,
    override val from: User,
    override val chatInstance: String,
    val raw: String
) : CallbackQuery
