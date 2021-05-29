package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditInlineMessageText
import dev.inmo.tgbotapi.types.InlineMessageIdentifier
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup

suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageText(inlineMessageId, text, parseMode, disableWebPagePreview, replyMarkup))

suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageIdentifier,
    entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>,
    disableWebPagePreview: Boolean? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(EditInlineMessageText(inlineMessageId, entities, disableWebPagePreview, replyMarkup))
