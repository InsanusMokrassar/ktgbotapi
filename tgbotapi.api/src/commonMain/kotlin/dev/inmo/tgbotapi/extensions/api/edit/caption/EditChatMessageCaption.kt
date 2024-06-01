package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditChatMessageCaption
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.RiskFeature

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageCaption(chatId, messageId, text, parseMode, showCaptionAboveMedia, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageId,
    text: String,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageCaption(chat.id, messageId, text, parseMode, showCaptionAboveMedia, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, text, parseMode, showCaptionAboveMedia, replyMarkup) as ContentMessage<T>
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageId,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageCaption(chatId, messageId, entities, showCaptionAboveMedia, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageId,
    entities: List<TextSource>,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageCaption(chat.id, messageId, entities, showCaptionAboveMedia, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    entities: List<TextSource>,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, entities, showCaptionAboveMedia, replyMarkup) as ContentMessage<T>
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not media message this method will throw an exception")
suspend fun <T> TelegramBot.editMessageCaption(
    message: AccessibleMessage,
    entities: List<TextSource>,
    showCaptionAboveMedia: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message.chat.id, message.messageId, entities, showCaptionAboveMedia, replyMarkup)
}
