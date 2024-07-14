package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditInlineMessageText
import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.LinkPreviewOptions
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.EntitiesBuilderBody
import dev.inmo.tgbotapi.utils.buildEntities

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageId,
    text: String,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Boolean = execute(EditInlineMessageText(inlineMessageId, text, parseMode, showCaptionAboveMedia, linkPreviewOptions, replyMarkup))

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): Boolean = execute(EditInlineMessageText(inlineMessageId, entities, showCaptionAboveMedia, linkPreviewOptions, replyMarkup))

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageId,
    separator: TextSource? = null,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Boolean = editMessageText(inlineMessageId, buildEntities(separator, builderBody), showCaptionAboveMedia, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    inlineMessageId: InlineMessageId,
    separator: String,
    showCaptionAboveMedia: Boolean = false,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): Boolean = editMessageText(inlineMessageId, buildEntities(separator, builderBody), showCaptionAboveMedia, linkPreviewOptions, replyMarkup)
