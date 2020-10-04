package dev.inmo.tgbotapi.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.SendMediaGroup
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.ForwardInfo
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MediaGroupContent
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.SentMediaGroupUpdate

val List<CommonMessage<MediaGroupContent>>.forwardInfo: ForwardInfo?
    get() = firstOrNull() ?.forwardInfo
val List<CommonMessage<MediaGroupContent>>.replyTo: Message?
    get() = firstOrNull() ?.replyTo
val List<CommonMessage<MediaGroupContent>>.chat: Chat?
    get() = firstOrNull() ?.chat
val List<MediaGroupMessage>.mediaGroupId: MediaGroupIdentifier?
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
    disableNotification: Boolean = false,
    replyTo: MessageIdentifier? = null
) = SendMediaGroup(
    chatId,
    map { it.content.toMediaGroupMemberInputMedia() },
    disableNotification,
    replyTo
)

fun List<CommonMessage<MediaGroupContent>>.createResend(
    chat: Chat,
    disableNotification: Boolean = false,
    replyTo: MessageIdentifier? = null
) = createResend(
    chat.id,
    disableNotification,
    replyTo
)

fun SentMediaGroupUpdate.createResend(
    disableNotification: Boolean = false,
    replyTo: MessageIdentifier? = null
) = data.createResend(
    chat,
    disableNotification,
    replyTo
)
