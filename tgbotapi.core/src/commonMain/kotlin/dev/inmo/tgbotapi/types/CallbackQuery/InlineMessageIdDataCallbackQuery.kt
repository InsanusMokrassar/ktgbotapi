package dev.inmo.tgbotapi.types.CallbackQuery

import dev.inmo.tgbotapi.types.*

data class InlineMessageIdDataCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    override val inlineMessageId: InlineMessageIdentifier,
    override val data: String
) : DataCallbackQuery, InlineMessageIdCallbackQuery
