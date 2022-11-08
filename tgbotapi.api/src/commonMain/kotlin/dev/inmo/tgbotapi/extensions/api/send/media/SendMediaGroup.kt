package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlin.jvm.JvmName

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendMediaGroup<MediaGroupPartContent>(
        chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendPlaylist(
        chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendDocumentsGroup(
        chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendVisualMediaGroup(
        chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply
)
