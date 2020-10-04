package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.abstracts.Message

data class MessageGameShortNameCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    override val message: Message,
    override val gameShortName: String
) : GameShortNameCallbackQuery, MessageCallbackQuery
