package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.utils.RiskFeature

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
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

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = sendMediaGroup(
    chat.id, media, disableNotification, replyToMessageId
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = execute(
    SendPlaylist(
        chatId, media, disableNotification, replyToMessageId
    )
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = sendPlaylist(
    chat.id, media, disableNotification, replyToMessageId
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = execute(
    SendDocumentsGroup(
        chatId, media, disableNotification, replyToMessageId
    )
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = sendDocumentsGroup(
    chat.id, media, disableNotification, replyToMessageId
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = execute(
    SendVisualMediaGroup(
        chatId, media, disableNotification, replyToMessageId
    )
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
) = sendVisualMediaGroup(
    chat.id, media, disableNotification, replyToMessageId
)

suspend inline fun TelegramBot.replyWithMediaGroup(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = sendMediaGroup(to.chat, media, disableNotification, to.messageId)

suspend inline fun TelegramBot.replyWithPlaylist(
    to: Message,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = sendPlaylist(to.chat, media, disableNotification, to.messageId)

suspend inline fun TelegramBot.replyWithDocumentsGroup(
    to: Message,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = sendDocumentsGroup(to.chat, media, disableNotification, to.messageId)

suspend inline fun TelegramBot.replyWithVisualMediaGroup(
    to: Message,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = sendVisualMediaGroup(to.chat, media, disableNotification, to.messageId)

suspend inline fun TelegramBot.reply(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false
) = replyWithMediaGroup(to, media, disableNotification)
