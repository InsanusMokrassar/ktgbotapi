package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.message.AnonymousForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.AnonymousGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ChannelContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.CommonGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.ConnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.FromChannelForumContentMessageImpl
import dev.inmo.tgbotapi.types.message.PrivateContentMessageImpl
import dev.inmo.tgbotapi.types.message.UnconnectedFromChannelGroupContentMessageImpl
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.AnonymousGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ChannelContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.ConnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.FromChannelForumContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.PrivateContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.UnconnectedFromChannelGroupContentMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature("This API is experimental and can be changed without any notice, use with caution")
fun List<CommonMessage<MediaGroupPartContent>>.asMediaGroupContent(
    mediaGroupIdentifier: MediaGroupIdentifier
): PossiblySentViaBotCommonMessage<MediaGroupContent> {
    val content = MediaGroupContent(
        map { MediaGroupCollectionContent.PartWrapper(it.messageId, it.content, it) },
        mediaGroupIdentifier
    )
    return when (val sourceMessage = first()) {
        is MediaGroupMessage -> TODO()
        is ChannelContentMessage -> ChannelContentMessageImpl(
            sourceMessage.messageId,
            sourceMessage.chat,
            content,
            sourceMessage.date,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.forwardInfo,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
        is PrivateContentMessage -> PrivateContentMessageImpl(
            sourceMessage.messageId,
            sourceMessage.user,
            sourceMessage.chat,
            content,
            sourceMessage.date,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.forwardInfo,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            sourceMessage.senderBot
        )
        is AnonymousGroupContentMessage -> AnonymousGroupContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.messageId,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
        is CommonGroupContentMessage -> CommonGroupContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.messageId,
            sourceMessage.user,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot
        )
        is ConnectedFromChannelGroupContentMessage -> ConnectedFromChannelGroupContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.channel,
            sourceMessage.messageId,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
        is UnconnectedFromChannelGroupContentMessage -> UnconnectedFromChannelGroupContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.channel,
            sourceMessage.messageId,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
        is AnonymousForumContentMessage -> AnonymousForumContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.messageId,
            sourceMessage.threadId,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
        is CommonForumContentMessage -> CommonForumContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.messageId,
            sourceMessage.threadId,
            sourceMessage.user,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot
        )
        is FromChannelForumContentMessage -> FromChannelForumContentMessageImpl(
            sourceMessage.chat,
            sourceMessage.channel,
            sourceMessage.messageId,
            sourceMessage.threadId,
            sourceMessage.date,
            sourceMessage.forwardInfo,
            sourceMessage.editDate,
            sourceMessage.hasProtectedContent,
            sourceMessage.replyTo,
            sourceMessage.replyMarkup,
            content,
            sourceMessage.senderBot,
            sourceMessage.authorSignature
        )
    }
}
