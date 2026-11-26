package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditChatMessageCaption
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionId
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.types.message.content.TextedContent
import dev.inmo.tgbotapi.types.message.content.WidgetContent
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlin.jvm.JvmName

/**
 * Edits a caption by message ID. The result contains the edited media, paid media or widget.
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextedContent> = execute(
    EditChatMessageCaption(chatId, messageId, text, parseMode, businessConnectionId, showCaptionAboveMedia, replyMarkup)
)

/**
 * Edits a caption by message ID and returns the edited content in [chat].
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextedContent> = editMessageCaption(chat.id, messageId, text, parseMode, businessConnectionId, showCaptionAboveMedia, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, text, parseMode, businessConnectionId, showCaptionAboveMedia, replyMarkup) as ContentMessage<T>
}

/** Edits a widget's caption and returns the updated widget message. */
@JvmName("editWidgetMessageCaption")
public suspend fun TelegramBot.editMessageCaption(
    message: ContentMessage<WidgetContent>,
    text: String,
    parseMode: ParseMode? = null,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<WidgetContent> {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, text, parseMode, businessConnectionId, showCaptionAboveMedia, replyMarkup) as ContentMessage<WidgetContent>
}

/**
 * Edits a caption by message ID using formatted entities. The returned content can be media, paid media or a widget.
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextedContent> = execute(
    EditChatMessageCaption(chatId, messageId, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup)
)

/**
 * Edits a caption by message ID using formatted entities and returns the edited content in [chat].
 *
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageId,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextedContent> = editMessageCaption(chat.id, messageId, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
public suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup) as ContentMessage<T>
}

/** Edits a widget's caption using its formatted text entities. */
@JvmName("editWidgetMessageCaption")
public suspend fun TelegramBot.editMessageCaption(
    message: ContentMessage<WidgetContent>,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<WidgetContent> {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup) as ContentMessage<WidgetContent>
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("Telegram requires a message with an editable caption.")
public suspend fun <T> TelegramBot.editMessageCaption(
    message: AccessibleMessage,
    entities: TextSourcesList,
    businessConnectionId: BusinessConnectionId? = message.chat.id.businessConnectionId,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<TextedContent> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message.chat.id, message.messageId, entities, businessConnectionId, showCaptionAboveMedia, replyMarkup)
}
