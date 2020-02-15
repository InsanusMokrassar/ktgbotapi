package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.edit.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.media.EditInlineMessageMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.InlineMessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup

suspend fun RequestsExecutor.editMessageCaption(
    inlineMessageId: InlineMessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageMedia(inlineMessageId, media, replyMarkup))
