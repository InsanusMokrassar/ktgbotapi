package dev.inmo.tgbotapi.extensions.api.edit.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.media.EditInlineMessageMedia
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.InputMedia.InputMedia
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.editMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageMedia(inlineMessageId, media, replyMarkup))
