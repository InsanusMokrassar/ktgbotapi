package dev.inmo.tgbotapi.extensions.api.edit.caption

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.caption.EditChatMessageCaption
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.MediaContent
import dev.inmo.tgbotapi.utils.RiskFeature

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageCaption(chatId, messageId, text, parseMode, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageIdentifier,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageCaption(chat.id, messageId, text, parseMode, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    text: String,
    parseMode: ParseMode? = null,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, text, parseMode, replyMarkup) as ContentMessage<T>
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    entities: TextSourcesList,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageCaption(chatId, messageId, entities, replyMarkup)
)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun TelegramBot.editMessageCaption(
    chat: Chat,
    messageId: MessageIdentifier,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageCaption(chat.id, messageId, entities, replyMarkup)

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
suspend fun <T> TelegramBot.editMessageCaption(
    message: ContentMessage<T>,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<T> where T : TextedWithTextSources, T : MediaContent {
    @Suppress("UNCHECKED_CAST")
    return editMessageCaption(message.chat.id, message.messageId, entities, replyMarkup) as ContentMessage<T>
}

/**
 * @param replyMarkup Some [InlineKeyboardMarkup]. See [dev.inmo.tgbotapi.extensions.utils.types.buttons.inlineKeyboard]
 * as a builder for that
 */
@RiskFeature("This method is unsafe due to absence of any guaranties about the type of message. In case if message is not media message this method will throw an exception")
suspend fun <T> TelegramBot.editMessageCaption(
    message: Message,
    entities: List<TextSource>,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<MediaContent> where T : TextedWithTextSources, T : MediaContent {
    return editMessageCaption(message.chat.id, message.messageId, entities, replyMarkup)
}
