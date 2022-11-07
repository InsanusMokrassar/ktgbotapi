package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.tgbotapi.requests.send.media.SendMediaGroup
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.ForwardInfo
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate

val List<CommonMessage<out MediaGroupContent>>.forwardInfo: ForwardInfo?
    get() = firstOrNull() ?.forwardInfo
val List<CommonMessage<out MediaGroupContent>>.replyTo: Message?
    get() = firstOrNull() ?.replyTo
val List<CommonMessage<out MediaGroupContent>>.chat: Chat?
    get() = firstOrNull() ?.chat
val List<MediaGroupMessage<*>>.mediaGroupId: MediaGroupIdentifier?
    get() = firstOrNull() ?.mediaGroupId

val SentMediaGroupUpdate.forwardInfo: ForwardInfo?
    get() = data.first().forwardInfo
val SentMediaGroupUpdate.replyTo: Message?
    get() = data.first().replyTo
val SentMediaGroupUpdate.chat: Chat
    get() = data.chat!!
val SentMediaGroupUpdate.mediaGroupId: MediaGroupIdentifier
    get() = data.mediaGroupId!!

fun List<CommonMessage<MediaGroupContent>>.createResend(
    chatId: ChatId,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = SendMediaGroup<MediaGroupContent>(
    chatId,
    map { it.content.toMediaGroupMemberTelegramMedia() },
    threadId,
    disableNotification,
    protectContent,
    replyTo
)

fun List<CommonMessage<MediaGroupContent>>.createResend(
    chat: Chat,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = createResend(
    chat.id,
    threadId,
    disableNotification,
    protectContent,
    replyTo
)

fun SentMediaGroupUpdate.createResend(
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = data.createResend(
    chat,
    threadId,
    disableNotification,
    protectContent,
    replyTo
)
