package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditInlineMessageCaption
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageCaption(inlineMessageId, text, parseMode, replyMarkup))

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageCaption(inlineMessageId, entities, replyMarkup))
