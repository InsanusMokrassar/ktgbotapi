package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.CallbackQueryIdentifier
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.InaccessibleMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

data class InaccessibleMessageDataCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val from: CommonUser,
    override val chatInstance: String,
    override val message: InaccessibleMessage,
    override val data: String
) : DataCallbackQuery, InaccessibleMessageCallbackQuery
