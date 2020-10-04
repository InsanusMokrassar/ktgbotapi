package dev.inmo.tgbotapi.extensions.api.edit.ReplyMarkup

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.ReplyMarkup.EditInlineMessageReplyMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.editMessageReplyMarkup(
    inlineMessageId: InlineMessageIdentifier,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
