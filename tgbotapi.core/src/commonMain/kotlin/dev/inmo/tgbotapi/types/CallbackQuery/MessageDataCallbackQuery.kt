package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.abstracts.Message

data class MessageDataCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val from: User,
    override val chatInstance: String,
    override val message: Message,
    override val data: String
) : DataCallbackQuery, MessageCallbackQuery
