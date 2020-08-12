package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.shortcuts

import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.aggregateFlows
import com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates.asContentMessagesFlow
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.InvoiceContent
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

inline fun <reified T : MessageContent> filterForContentMessage(): suspend (ContentMessage<*>) -> ContentMessage<T>? = {
    if (it.content is T) {
        it as ContentMessage<T>
    } else {
        null
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T: MessageContent> Flow<BaseSentMessageUpdate>.filterContentMessages(
): Flow<ContentMessage<T>> = asContentMessagesFlow().mapNotNull(filterForContentMessage())

/**
 * @param scopeToIncludeChannels This parameter is required when you want to include [textMessages] for channels too.
 * In this case will be created new channel which will agregate messages from [FlowsUpdatesFilter.messageFlow] and
 * [FlowsUpdatesFilter.channelPostFlow]. In case it is null will be used [Flow]s mapping
 */
@Suppress("UNCHECKED_CAST")
inline fun <reified T: MessageContent> FlowsUpdatesFilter.filterContentMessages(
    scopeToIncludeChannels: CoroutineScope? = null
): Flow<ContentMessage<T>> {
    return (scopeToIncludeChannels ?.let { scope ->
        aggregateFlows(
            scope,
            messageFlow,
            channelPostFlow
        )
    } ?: messageFlow).filterContentMessages()
}

fun Flow<BaseSentMessageUpdate>.animationMessages() = filterContentMessages<AnimationContent>()
fun FlowsUpdatesFilter.animationMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AnimationContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.audioMessages() = filterContentMessages<AudioContent>()
fun FlowsUpdatesFilter.audioMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<AudioContent>(scopeToIncludeChannels)

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
/**
 * Shortcut for [photoMessages]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun FlowsUpdatesFilter.imageMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = photoMessages(scopeToIncludeChannels)

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

fun Flow<BaseSentMessageUpdate>.videoNoteMessages() = filterContentMessages<VideoNoteContent>()
fun FlowsUpdatesFilter.videoNoteMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VideoNoteContent>(scopeToIncludeChannels)

fun Flow<BaseSentMessageUpdate>.voiceMessages() = filterContentMessages<VoiceContent>()
fun FlowsUpdatesFilter.voiceMessages(
    scopeToIncludeChannels: CoroutineScope? = null
) = filterContentMessages<VoiceContent>(scopeToIncludeChannels)


