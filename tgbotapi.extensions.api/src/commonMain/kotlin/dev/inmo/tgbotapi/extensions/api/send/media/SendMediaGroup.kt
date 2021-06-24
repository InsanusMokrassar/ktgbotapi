package dev.inmo.tgbotapi.extensions.api.send.media

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.api.send.replyWithPlaylist
import dev.inmo.tgbotapi.extensions.api.send.replyWithDocuments
import dev.inmo.tgbotapi.extensions.api.send.replyWithGallery
import dev.inmo.tgbotapi.extensions.api.send.replyWithMediaGroup
import dev.inmo.tgbotapi.requests.send.media.*
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.InputMedia.*
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.chat.abstracts.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaGroupContent
import dev.inmo.tgbotapi.utils.RiskFeature

/**
 * @see SendMediaGroup
 */
@RiskFeature(rawSendingMediaGroupsWarning)
suspend fun TelegramBot.sendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendMediaGroup<MediaGroupContent>(
        chatId, media, disableNotification, replyToMessageId, allowSendingWithoutReply
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
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendMediaGroup(
    chat.id, media, disableNotification, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendPlaylist(
        chatId, media, disableNotification, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendPlaylist
 */
suspend fun TelegramBot.sendPlaylist(
    chat: Chat,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendPlaylist(
    chat.id, media, disableNotification, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendDocumentsGroup(
        chatId, media, disableNotification, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendDocumentsGroup
 */
suspend fun TelegramBot.sendDocumentsGroup(
    chat: Chat,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendDocumentsGroup(
    chat.id, media, disableNotification, replyToMessageId, allowSendingWithoutReply
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = execute(
    SendVisualMediaGroup(
        chatId, media, disableNotification, replyToMessageId, allowSendingWithoutReply
    )
)

/**
 * @see SendVisualMediaGroup
 */
suspend fun TelegramBot.sendVisualMediaGroup(
    chat: Chat,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null
) = sendVisualMediaGroup(
    chat.id, media, disableNotification, replyToMessageId, allowSendingWithoutReply
)


@Deprecated(
    "Replaced",
    ReplaceWith("replyWithMediaGroup", "dev.inmo.tgbotapi.extensions.api.send.replyWithMediaGroup")
)
@RiskFeature(rawSendingMediaGroupsWarning)
suspend inline fun TelegramBot.replyWithMediaGroup(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = replyWithMediaGroup(to, media, disableNotification, allowSendingWithoutReply)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithPlaylist", "dev.inmo.tgbotapi.extensions.api.send.replyWithPlaylist")
)
suspend inline fun TelegramBot.replyWithPlaylist(
    to: Message,
    media: List<AudioMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = replyWithPlaylist(to, media, disableNotification, allowSendingWithoutReply)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithDocuments", "dev.inmo.tgbotapi.extensions.api.send.replyWithDocuments")
)
suspend inline fun TelegramBot.replyWithDocumentsGroup(
    to: Message,
    media: List<DocumentMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = replyWithDocuments(to, media, disableNotification, allowSendingWithoutReply)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithGallery", "dev.inmo.tgbotapi.extensions.api.send.replyWithGallery")
)
suspend inline fun TelegramBot.replyWithVisualMediaGroup(
    to: Message,
    media: List<VisualMediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = replyWithGallery(to, media, disableNotification, allowSendingWithoutReply)

@Deprecated(
    "Replaced",
    ReplaceWith("replyWithMediaGroup", "dev.inmo.tgbotapi.extensions.api.send.replyWithMediaGroup")
)
suspend inline fun TelegramBot.reply(
    to: Message,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    allowSendingWithoutReply: Boolean? = null
) = replyWithMediaGroup(to, media, disableNotification, allowSendingWithoutReply)
