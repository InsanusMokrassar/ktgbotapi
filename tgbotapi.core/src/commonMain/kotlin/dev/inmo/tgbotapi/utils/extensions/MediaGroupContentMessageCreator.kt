package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.message.content.MediaGroupCollectionContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.utils.RiskFeature

@RiskFeature("This API is experimental and can be changed without any notice, use with caution")
fun <T : MediaGroupPartContent> List<PossiblySentViaBotCommonMessage<T>>.asMediaGroupMessage(): PossiblySentViaBotCommonMessage<MediaGroupContent<T>> {
    val sourceMessage = first()
    val content = MediaGroupContent(
        map { MediaGroupCollectionContent.PartWrapper(it.messageId, it.content, it) },
        sourceMessage.mediaGroupId ?: error("Can't create media group message with the first message without media group id")
    )
    return when (sourceMessage) {
        is ChannelContentMessage -> ChannelContentMessageImpl(
            messageId = sourceMessage.messageId,
            chat = sourceMessage.chat,
            senderChat = sourceMessage.senderChat,
            content = content,
            date = sourceMessage.date,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            forwardOrigin = sourceMessage.forwardOrigin,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
        is BusinessContentMessage -> BusinessContentMessageImpl(
            messageId = sourceMessage.messageId,
            from = sourceMessage.user,
            chat = sourceMessage.chat,
            businessConnectionId = sourceMessage.businessConnectionId,
            content = content,
            date = sourceMessage.date,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            forwardOrigin = sourceMessage.forwardOrigin,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            senderBot = sourceMessage.senderBot,
            mediaGroupId = sourceMessage.mediaGroupId,
            senderBusinessBot = sourceMessage.senderBusinessBot,
            fromOffline = sourceMessage.fromOffline
        )
        is PrivateContentMessage -> PrivateContentMessageImpl(
            messageId = sourceMessage.messageId,
            from = sourceMessage.user,
            chat = sourceMessage.chat,
            content = content,
            date = sourceMessage.date,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            forwardOrigin = sourceMessage.forwardOrigin,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            senderBot = sourceMessage.senderBot,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline,
            effectId = sourceMessage.effectId
        )
        is AnonymousGroupContentMessage -> AnonymousGroupContentMessageImpl(
            chat = sourceMessage.chat,
            messageId = sourceMessage.messageId,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
        is CommonGroupContentMessage -> CommonGroupContentMessageImpl(
            chat = sourceMessage.chat,
            messageId = sourceMessage.messageId,
            from = sourceMessage.user,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            mediaGroupId = sourceMessage.mediaGroupId,
            senderBoostsCount = sourceMessage.senderBoostsCount,
            fromOffline = sourceMessage.fromOffline
        )
        is ConnectedFromChannelGroupContentMessage -> ConnectedFromChannelGroupContentMessageImpl(
            chat = sourceMessage.chat,
            channel = sourceMessage.channel,
            messageId = sourceMessage.messageId,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
        is UnconnectedFromChannelGroupContentMessage -> UnconnectedFromChannelGroupContentMessageImpl(
            chat = sourceMessage.chat,
            channel = sourceMessage.channel,
            messageId = sourceMessage.messageId,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
        is AnonymousForumContentMessage -> AnonymousForumContentMessageImpl(
            chat = sourceMessage.chat,
            messageId = sourceMessage.messageId,
            threadId = sourceMessage.threadId,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
        is CommonForumContentMessage -> CommonForumContentMessageImpl(
            chat = sourceMessage.chat,
            messageId = sourceMessage.messageId,
            threadId = sourceMessage.threadId,
            from = sourceMessage.user,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            mediaGroupId = sourceMessage.mediaGroupId,
            senderBoostsCount = sourceMessage.senderBoostsCount,
            fromOffline = sourceMessage.fromOffline
        )
        is FromChannelForumContentMessage -> FromChannelForumContentMessageImpl(
            chat = sourceMessage.chat,
            channel = sourceMessage.channel,
            messageId = sourceMessage.messageId,
            threadId = sourceMessage.threadId,
            date = sourceMessage.date,
            forwardOrigin = sourceMessage.forwardOrigin,
            editDate = sourceMessage.editDate,
            hasProtectedContent = sourceMessage.hasProtectedContent,
            replyInfo = sourceMessage.replyInfo,
            replyMarkup = sourceMessage.replyMarkup,
            content = content,
            senderBot = sourceMessage.senderBot,
            authorSignature = sourceMessage.authorSignature,
            mediaGroupId = sourceMessage.mediaGroupId,
            fromOffline = sourceMessage.fromOffline
        )
    }
}
