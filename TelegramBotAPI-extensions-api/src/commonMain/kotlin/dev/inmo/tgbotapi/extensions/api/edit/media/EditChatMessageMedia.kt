package dev.inmo.tgbotapi.extensions.api.edit.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.edit.media.EditChatMessageMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.InputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaContent

suspend fun TelegramBot.editMessageMedia(
    chatId: ChatIdentifier,
    messageId: MessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    EditChatMessageMedia(chatId, messageId, media, replyMarkup)
)

suspend fun TelegramBot.editMessageMedia(
    chat: Chat,
    messageId: MessageIdentifier,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(chat.id, messageId, media, replyMarkup)

suspend fun TelegramBot.editMessageMedia(
    message: ContentMessage<out MediaContent>,
    media: InputMedia,
    replyMarkup: InlineKeyboardMarkup? = null
) = editMessageMedia(message.chat.id, message.messageId, media, replyMarkup)
