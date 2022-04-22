package dev.inmo.tgbotapi.extensions.api.edit.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.media.EditInlineMessageMedia
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    media: TelegramMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageMedia(inlineMessageId, media, replyMarkup))
