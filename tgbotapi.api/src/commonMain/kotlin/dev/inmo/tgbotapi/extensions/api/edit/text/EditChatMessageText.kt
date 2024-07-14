package dev.inmo.tgbotapi.extensions.api.edit.text

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.text.EditChatMessageText
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.LinkPreviewOptions
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
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
public suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = execute(
    EditChatMessageText(chatId, messageId, text, parseMode, businessConnectionId, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(chat.id, messageId, text, parseMode, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, text, parseMode, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = execute(
    EditChatMessageText(chatId, messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(chatId, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chatId: ChatIdentifier,
    messageId: MessageId,
    separator: String,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(chatId, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(chat.id, messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(chat.id, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    chat: Chat,
    messageId: MessageId,
    separator: String,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(chat.id, messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageText(
    message: ContentMessage<TextContent>,
    separator: String,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
public suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, entities, businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
public suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    separator: TextSource? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not text message this method will throw an exception")
public suspend fun TelegramBot.editMessageText(
    message: AccessibleMessage,
    separator: String,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    linkPreviewOptions: LinkPreviewOptions? = null,
    replyMarkup: InlineKeyboardMarkup? = null,
    builderBody: EntitiesBuilderBody
): ContentMessage<TextContent> = editMessageText(message.chat.id, message.messageId, buildEntities(separator, builderBody), businessConnectionId, linkPreviewOptions, replyMarkup)
