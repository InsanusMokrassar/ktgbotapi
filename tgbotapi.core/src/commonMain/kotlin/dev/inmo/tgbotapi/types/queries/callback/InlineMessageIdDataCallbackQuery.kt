package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.*

data class InlineMessageIdDataCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val from: User,
    override val chatInstance: String,
    override val inlineMessageId: InlineMessageIdentifier,
    override val data: String
) : DataCallbackQuery, InlineMessageIdCallbackQuery
