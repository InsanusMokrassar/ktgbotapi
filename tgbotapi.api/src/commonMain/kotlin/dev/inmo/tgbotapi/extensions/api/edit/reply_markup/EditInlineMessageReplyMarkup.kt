package dev.inmo.tgbotapi.extensions.api.edit.reply_markup

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.reply_markup.EditInlineMessageReplyMarkup
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageReplyMarkup(
    inlineMessageId: InlineMessageId,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean = execute(EditInlineMessageReplyMarkup(inlineMessageId, replyMarkup))
