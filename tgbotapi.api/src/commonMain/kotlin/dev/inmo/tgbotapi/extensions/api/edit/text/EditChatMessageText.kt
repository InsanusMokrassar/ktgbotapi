package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditChatMessageText
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.LinkPreviewOptions
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.utils.*

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageText(chatId, messageId, text, parseMode, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chat.id, messageId, text, parseMode, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    text: String,
    parseMode: ParseMode? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(message.chat.id, message.messageId, text, parseMode, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageText(chatId, messageId, entities, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(chatId, messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(chatId, messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(chat.id, messageId, entities, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(chat.id, messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(chat.id, messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(message.chat.id, message.messageId, entities, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    entities: TextSourcesList,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageText(message.chat.id, message.messageId, entities, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    separator: TextSource? = null,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    separator: String,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
) = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), linkPreviewOptions, replyMarkup)
