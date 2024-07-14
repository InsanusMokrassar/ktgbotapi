package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlin.jvm.JvmName

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
public suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<MediaGroupPartContent>> = execute(
    SendMediaGroup<MediaGroupPartContent>(
        chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
    )
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
public suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<MediaGroupPartContent>> = sendMediaGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
public suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<MediaGroupPartContent>> = sendMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
@JvmName("sendMedaGroupByContent")
public suspend fun TelegramBot.sendMediaGroup(
    chat: Chat,
    media: List<MediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<MediaGroupPartContent>> = sendMediaGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendPlaylist
 */
public suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<AudioContent>> = execute(
    SendPlaylist(
        chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
    )
)

/**
 * @see SendPlaylist
 */
public suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<AudioContent>> = sendPlaylist(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
public suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<AudioContent>> = sendPlaylist(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendPlaylist
 */
@JvmName("sendPlaylistByContent")
public suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<AudioContent>> = sendPlaylist(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
public suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<DocumentContent>> = execute(
    SendDocumentsGroup(
        chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
    )
)

/**
 * @see SendDocumentsGroup
 */
public suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<DocumentContent>> = sendDocumentsGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
public suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<DocumentContent>> = sendDocumentsGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendDocumentsGroup
 */
@JvmName("sendDocumentsByContent")
public suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<DocumentContent>> = sendDocumentsGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
public suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = execute(
    SendVisualMediaGroup(
        chatId, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
    )
)

/**
 * @see SendVisualMediaGroup
 */
public suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = sendVisualMediaGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
public suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = sendVisualMediaGroup(
    chatId, media.map { it.toMediaGroupMemberTelegramMedia() }, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)

/**
 * @see SendVisualMediaGroup
 */
@JvmName("sendVisualMediaGroupByContent")
public suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupPartContent>,
    threadId: MessageThreadId? = chat.id.threadId,
    businessConnectionId: BusinessConnectionId? = chat.id.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): ContentMessage<MediaGroupContent<VisualMediaGroupPartContent>> = sendVisualMediaGroup(
    chat.id, media, threadId, businessConnectionId, disableNotification, protectContent, effectId, replyParameters
)
