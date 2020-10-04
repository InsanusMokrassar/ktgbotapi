package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendMediaGroup
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.MediaGroupMemberInputMedia
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

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
