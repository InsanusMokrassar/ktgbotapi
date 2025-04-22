package dev.inmo.tgbotapi.extensions.api.edit.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.media.EditInlineMessageMedia
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageMedia(
    inlineMessageId: InlineMessageId,
    media: TelegramFreeMedia,
    replyMarkup: InlineKeyboardMarkup? = null,
): Boolean = execute(EditInlineMessageMedia(inlineMessageId, media, replyMarkup))
