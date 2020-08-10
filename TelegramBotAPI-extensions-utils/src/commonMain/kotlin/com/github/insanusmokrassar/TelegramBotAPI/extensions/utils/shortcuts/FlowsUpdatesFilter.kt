package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates.asContentMessagesFlow
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.InvoiceContent
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

/**
 * @param scopeToIncludeChannels This parameter is required when you want to include [textMessages] for channels too.
 * In this case will be created new channel which will agregate messages from [FlowsUpdatesFilter.messageFlow] and
 * [FlowsUpdatesFilter.channelPostFlow]. In case it is null will be used [Flow]s mapping
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T: MessageContent> FlowsUpdatesFilter.filterContentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<ContentMessage<T>> {
    return scopeToIncludeChannels ?.let { scope ->
        val bc = BroadcastChannel<ContentMessage<T>>(Channel.BUFFERED)
        channelPostFlow.asContentMessagesFlow().mapNotNull {
            if (it.content is T) {
                it as ContentMessage<T>
            } else {
                null
            }
        }.onEach {
            bc.send(it)
        }.launchIn(scope)
        messageFlow.asContentMessagesFlow().mapNotNull {
            if (it.content is T) {
                it as ContentMessage<T>
            } else {
                null
            }
        }.onEach {
            bc.send(it)
        }.launchIn(scope)
        bc.asFlow()
    } ?: messageFlow.asContentMessagesFlow().mapNotNull {
        if (it.content is T) {
            it as ContentMessage<T>
        } else {
            null
        }
    }
}

fun FlowsUpdatesFilter.animationMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AnimationContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.audioMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AudioContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.contactMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<ContactContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.diceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<DiceContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.documentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<DocumentContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.gameMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<GameContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.invoiceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<InvoiceContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.locationMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<LocationContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.photoMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<PhotoContent>(scopeToIncludeChannels)
/**
 * Shortcut for [photoMessages]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FlowsUpdatesFilter.imageMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = photoMessages(scopeToIncludeChannels)

fun FlowsUpdatesFilter.pollMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<PollContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.stickerMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<StickerContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.textMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<TextContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.venueMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VenueContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.videoMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VideoContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.videoNoteMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VideoNoteContent>(scopeToIncludeChannels)

fun FlowsUpdatesFilter.voiceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VoiceContent>(scopeToIncludeChannels)


