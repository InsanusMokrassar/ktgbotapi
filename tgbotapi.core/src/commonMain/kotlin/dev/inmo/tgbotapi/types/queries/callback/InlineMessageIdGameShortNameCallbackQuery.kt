package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser

data class InlineMessageIdGameShortNameCallbackQuery(
    override val id: CallbackQueryId,
    override val from: CommonUser,
    override val chatInstance: String,
    override val inlineMessageId: InlineMessageId,
    override val gameShortName: String
) : GameShortNameCallbackQuery, InlineMessageIdCallbackQuery
