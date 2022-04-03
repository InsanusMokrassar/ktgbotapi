package dev.inmo.tgbotapi.extensions.utils.shortcuts

import dev.inmo.tgbotapi.extensions.utils.aggregateFlows
import dev.inmo.tgbotapi.extensions.utils.flatMap
import dev.inmo.tgbotapi.extensions.utils.updates.asContentMessagesFlow
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified T : MessageContent> filterForContentMessage(): suspend (ContentMessage<*>) -> ContentMessage<T>? = {
    if (it.content is T) {
        @Suppress("UNCHECKED_CAST")
        it as ContentMessage<T>
    } else {
        null
    }
}

@Suppress("UNCHECKED_CAST")
@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified T: MessageContent> Flow<BaseSentMessageUpdate>.filterContentMessages(
): Flow<ContentMessage<T>> = asContentMessagesFlow().mapNotNull(filterForContentMessage())

@RiskFeature("This method is low-level")
inline fun <reified T : MediaGroupContent> Flow<SentMediaGroupUpdate>.filterMediaGroupMessages(
): Flow<List<CommonMessage<T>>> = map {
    it.data.mapNotNull { message ->
        if (message.content is T) {
            @Suppress("UNCHECKED_CAST")
            message as CommonMessage<T>
        } else {
            null
        }
    }
}

/**
 * @param scopeToIncludeChannels This parameter is required when you want to include [textMessages] for channels too.
 * In this case will be created new channel which will aggregate messages from [FlowsUpdatesFilter.messagesFlow] and
 * [FlowsUpdatesFilter.channelPostsFlow]. In case it is null will be used [Flow]s mapping
 */
@Suppress("UNCHECKED_CAST")
@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified T: MessageContent> FlowsUpdatesFilter.filterContentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<ContentMessage<T>> {
    return (scopeToIncludeChannels ?.let { scope ->
        aggregateFlows(
            scope,
            messagesFlow,
            channelPostsFlow
        )
    } ?: messagesFlow).filterContentMessages()
}

/**
 * @param scopeToIncludeChannels This parameter is required when you want to include [SentMediaGroupUpdate] for channels
 * too. In this case will be created new channel which will aggregate messages from [FlowsUpdatesFilter.messagesFlow] and
 * [FlowsUpdatesFilter.channelPostsFlow]. In case it is null will be used [Flow]s mapping
 */
@Suppress("UNCHECKED_CAST")
@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified T: MediaGroupContent> FlowsUpdatesFilter.filterMediaGroupMessages(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<List<CommonMessage<T>>> {
    return (scopeToIncludeChannels ?.let { scope ->
        aggregateFlows(
            scope,
            messageMediaGroupsFlow,
            channelPostMediaGroupsFlow
        )
    } ?: messageMediaGroupsFlow).filterMediaGroupMessages()
}

fun FlowsUpdatesFilter.sentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<ContentMessage<MessageContent>> = filterContentMessages(scopeToIncludeChannels)
fun FlowsUpdatesFilter.sentMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<ContentMessage<MessageContent>> = merge(
    sentMessages(scopeToIncludeChannels),
    mediaGroupMessages(scopeToIncludeChannels).flatMap {
        it.mapNotNull {
            @Suppress("UNCHECKED_CAST")
            it as? ContentMessage<MessageContent>
        }
    }
)

fun Flow<BaseSentMessageUpdate>.animationMessages() = filterContentMessages<AnimationContent>()
fun FlowsUpdatesFilter.animationMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AnimationContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.audioMessages() = filterContentMessages<AudioContent>()
fun FlowsUpdatesFilter.audioMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AudioContent>(scopeToIncludeChannels)
fun FlowsUpdatesFilter.audioMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
) = merge(
    filterContentMessages<AudioContent>(scopeToIncludeChannels),
    mediaGroupAudioMessages(scopeToIncludeChannels).flatMap()
)

fun Flow<BaseSentMessageUpdate>.contactMessages() = filterContentMessages<ContactContent>()
fun FlowsUpdatesFilter.contactMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<ContactContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.diceMessages() = filterContentMessages<DiceContent>()
fun FlowsUpdatesFilter.diceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<DiceContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.documentMessages() = filterContentMessages<DocumentContent>()
fun FlowsUpdatesFilter.documentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<DocumentContent>(scopeToIncludeChannels)
fun FlowsUpdatesFilter.documentMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
) = merge(
    filterContentMessages<DocumentContent>(scopeToIncludeChannels),
    mediaGroupDocumentMessages(scopeToIncludeChannels).flatMap()
)

fun Flow<BaseSentMessageUpdate>.gameMessages() = filterContentMessages<GameContent>()
fun FlowsUpdatesFilter.gameMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<GameContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.invoiceMessages() = filterContentMessages<InvoiceContent>()
fun FlowsUpdatesFilter.invoiceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<InvoiceContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.locationMessages() = filterContentMessages<LocationContent>()
fun FlowsUpdatesFilter.locationMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<LocationContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.photoMessages() = filterContentMessages<PhotoContent>()
fun Flow<BaseSentMessageUpdate>.imageMessages() = photoMessages()
fun FlowsUpdatesFilter.photoMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<PhotoContent>(scopeToIncludeChannels)
fun FlowsUpdatesFilter.photoMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
) = merge(
    filterContentMessages<PhotoContent>(scopeToIncludeChannels),
    mediaGroupPhotosMessages(scopeToIncludeChannels).flatMap()
)
/**
 * Shortcut for [photoMessages]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FlowsUpdatesFilter.imageMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = photoMessages(scopeToIncludeChannels)
fun FlowsUpdatesFilter.imageMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
) = photoMessagesWithMediaGroups(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.pollMessages() = filterContentMessages<PollContent>()
fun FlowsUpdatesFilter.pollMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<PollContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.stickerMessages() = filterContentMessages<StickerContent>()
fun FlowsUpdatesFilter.stickerMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<StickerContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.textMessages() = filterContentMessages<TextContent>()
fun FlowsUpdatesFilter.textMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<TextContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.venueMessages() = filterContentMessages<VenueContent>()
fun FlowsUpdatesFilter.venueMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VenueContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.videoMessages() = filterContentMessages<VideoContent>()
fun FlowsUpdatesFilter.videoMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VideoContent>(scopeToIncludeChannels)
fun FlowsUpdatesFilter.videoMessagesWithMediaGroups(
    scopeToIncludeChannels: CoroutineScope? = null
) = merge(
    filterContentMessages<VideoContent>(scopeToIncludeChannels),
    mediaGroupVideosMessages(scopeToIncludeChannels).flatMap()
)

fun Flow<BaseSentMessageUpdate>.videoNoteMessages() = filterContentMessages<VideoNoteContent>()
fun FlowsUpdatesFilter.videoNoteMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VideoNoteContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.voiceMessages() = filterContentMessages<VoiceContent>()
fun FlowsUpdatesFilter.voiceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VoiceContent>(scopeToIncludeChannels)


fun Flow<SentMediaGroupUpdate>.mediaGroupMessages() = filterMediaGroupMessages<MediaGroupContent>()
fun FlowsUpdatesFilter.mediaGroupMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<MediaGroupContent>(scopeToIncludeChannels)

fun Flow<SentMediaGroupUpdate>.mediaGroupPhotosMessages() = filterMediaGroupMessages<PhotoContent>()
fun FlowsUpdatesFilter.mediaGroupPhotosMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<PhotoContent>(scopeToIncludeChannels)

fun Flow<SentMediaGroupUpdate>.mediaGroupVideosMessages() = filterMediaGroupMessages<VideoContent>()
fun FlowsUpdatesFilter.mediaGroupVideosMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<VideoContent>(scopeToIncludeChannels)

fun Flow<SentMediaGroupUpdate>.mediaGroupVisualMessages() = filterMediaGroupMessages<VisualMediaGroupContent>()
fun FlowsUpdatesFilter.mediaGroupVisualMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<VisualMediaGroupContent>(scopeToIncludeChannels)

fun Flow<SentMediaGroupUpdate>.mediaGroupAudioMessages() = filterMediaGroupMessages<AudioContent>()
fun FlowsUpdatesFilter.mediaGroupAudioMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<AudioContent>(scopeToIncludeChannels)

fun Flow<SentMediaGroupUpdate>.mediaGroupDocumentMessages() = filterMediaGroupMessages<DocumentContent>()
fun FlowsUpdatesFilter.mediaGroupDocumentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterMediaGroupMessages<DocumentContent>(scopeToIncludeChannels)
