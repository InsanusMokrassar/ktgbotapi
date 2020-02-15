package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.edit.ReplyMarkup

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.ReplyMarkup.EditInlineMessageReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun RequestsExecutor.editMessageReplyMarkup(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
