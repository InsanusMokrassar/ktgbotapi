package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.tgbotapi.requests.send.media.SendMediaGroup
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.ForwardInfo
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate

val List<CommonMessage<out MediaGroupPartContent>>.forwardInfo: ForwardInfo?
    get() = firstOrNull() ?.forwardInfo
val List<CommonMessage<out MediaGroupPartContent>>.replyTo: Message?
    get() = firstOrNull() ?.replyTo
val List<CommonMessage<out MediaGroupPartContent>>.chat: Chat?
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

fun List<CommonMessage<MediaGroupPartContent>>.createResend(
    chatId: ChatId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = SendMediaGroup<MediaGroupPartContent>(
    chatId,
    map { it.content.toMediaGroupMemberTelegramMedia() },
    disableNotification,
    protectContent,
    replyTo
)

fun List<CommonMessage<MediaGroupPartContent>>.createResend(
    chat: Chat,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = createResend(
    chat.id,
    disableNotification,
    protectContent,
    replyTo
)

fun SentMediaGroupUpdate.createResend(
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyTo: MessageId? = null
) = data.createResend(
    chat,
    disableNotification,
    protectContent,
    replyTo
)
