package com.github.insanusmokrassar.TelegramBotAPI.types.CallbackQuery

import com.github.insanusmokrassar.TelegramBotAPI.types.*

data class InlineMessageIdDataCallbackQuery(
    override val id: CallbackQueryIdentifier,
    override val user: User,
    override val chatInstance: String,
    override val inlineMessageId: InlineMessageIdentifier,
    override val data: String
) : DataCallbackQuery, InlineMessageIdCallbackQuery
