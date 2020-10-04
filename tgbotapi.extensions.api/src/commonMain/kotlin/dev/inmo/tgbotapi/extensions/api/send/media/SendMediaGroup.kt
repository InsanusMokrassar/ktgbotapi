package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.media.SendMediaGroup
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.MediaGroupMemberInputMedia
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message

suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = execute(
    SendMediaGroup(
        chatId, media, disableNotification, replyToMessageId
    )
)

suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = sendMediaGroup(
    chat.id, media, disableNotification, replyToMessageId
)

suspend inline fun TelegramBot.replyWithMediaGroup(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = sendMediaGroup(to.chat, media, disableNotification, to.messageId)

suspend inline fun TelegramBot.reply(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = replyWithMediaGroup(to, media, disableNotification)
