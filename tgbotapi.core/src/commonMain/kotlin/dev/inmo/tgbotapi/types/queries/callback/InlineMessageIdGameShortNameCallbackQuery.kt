package dev.inmo.tgbotapi.types.queries.callback

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User

data class InlineMessageIdGameShortNameCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val from: CommonUser,
    override val chatInstance: String,
    override val inlineMessageId: InlineMessageIdentifier,
    override val gameShortName: String
) : GameShortNameCallbackQuery, InlineMessageIdCallbackQuery
