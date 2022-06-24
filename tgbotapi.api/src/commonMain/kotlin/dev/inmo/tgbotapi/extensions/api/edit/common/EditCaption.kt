package dev.inmo.tgbotapi.extensions.api.edit.common

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.edit.caption.editMessageCaption
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaContent

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, text, parseMode, replyMarkup)
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.edit(
    message: ContentMessage<T>,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message, entities, replyMarkup)
}
