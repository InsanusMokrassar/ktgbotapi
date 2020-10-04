package dev.inmo.tgbotapi.extensions.api.edit.ReplyMarkup

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.ReplyMarkup.EditInlineMessageReplyMarkup
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.editMessageReplyMarkup(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
