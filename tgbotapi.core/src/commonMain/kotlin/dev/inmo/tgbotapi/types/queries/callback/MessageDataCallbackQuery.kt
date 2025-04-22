package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MessageContent

data class MessageDataCallbackQuery(
    override val id: CallbackQueryId,
    override val from: CommonUser,
    override val chatInstance: String,
    override val message: ContentMessage<MessageContent>,
    override val data: String,
) : DataCallbackQuery, MessageCallbackQuery
