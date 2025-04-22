package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditInlineMessageCaption
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageId,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean = execute(EditInlineMessageCaption(inlineMessageId, text, parseMode, replyMarkup))

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageId,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean = execute(EditInlineMessageCaption(inlineMessageId, entities, replyMarkup))
