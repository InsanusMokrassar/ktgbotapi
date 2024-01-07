package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.*
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
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = execute(
    SendMediaGroup<MediaGroupPartContent>(
        chatId, media, threadId, disableNotification, protectContent, replyParameters
    )
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = execute(
    SendPlaylist(
        chatId, media, threadId, disableNotification, protectContent, replyParameters
    )
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendPlaylist(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = execute(
    SendDocumentsGroup(
        chatId, media, threadId, disableNotification, protectContent, replyParameters
    )
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendDocumentsGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = execute(
    SendVisualMediaGroup(
        chatId, media, threadId, disableNotification, protectContent, replyParameters
    )
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, disableNotification, protectContent, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null
) = sendVisualMediaGroup(
    chat.id, media, threadId, disableNotification, protectContent, replyParameters
)
