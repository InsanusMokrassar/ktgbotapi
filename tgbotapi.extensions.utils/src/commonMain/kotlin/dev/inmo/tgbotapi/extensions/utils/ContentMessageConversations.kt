package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlin.reflect.KClass

@Suppress("UNCHECKED_CAST")
inline fun <reified T : MessageContent> ContentMessage<*>.withContent() = if (content is T) {
    this as ContentMessage<T>
} else {
    null
}

@Suppress("UNCHECKED_CAST")
inline fun <reified T : MessageContent> ContentMessage<*>.requireWithContent() = this as ContentMessage<T>

private inline fun <reified T : MessageContent> Flow<ContentMessage<*>>.withContentType() = mapNotNull {
    it.withContent<T>()
}

@Deprecated("This method will be removed in next major update")
fun <T : MessageContent> Flow<ContentMessage<*>>.withContentType(contentType: KClass<T>) = mapNotNull {
    if (contentType.isInstance(it.content)) {
        @Suppress("UNCHECKED_CAST")
        it as ContentMessage<T>
    } else {
        null
    }
}

fun Flow<ContentMessage<*>>.onlyAnimationContentMessages() = withContentType<AnimationContent>()
fun Flow<ContentMessage<*>>.onlyAudioContentMessages() = withContentType<AudioContent>()
fun Flow<ContentMessage<*>>.onlyContactContentMessages() = withContentType<ContactContent>()
fun Flow<ContentMessage<*>>.onlyDiceContentMessages() = withContentType<DiceContent>()
fun Flow<ContentMessage<*>>.onlyDocumentContentMessages() = withContentType<DocumentContent>()
fun Flow<ContentMessage<*>>.onlyGameContentMessages() = withContentType<GameContent>()
fun Flow<ContentMessage<*>>.onlyInvoiceContentMessages() = withContentType<InvoiceContent>()
fun Flow<ContentMessage<*>>.onlyLocationContentMessages() = withContentType<LocationContent>()
fun Flow<ContentMessage<*>>.onlyPhotoContentMessages() = withContentType<PhotoContent>()
fun Flow<ContentMessage<*>>.onlyPollContentMessages() = withContentType<PollContent>()
fun Flow<ContentMessage<*>>.onlyStickerContentMessages() = withContentType<StickerContent>()
fun Flow<ContentMessage<*>>.onlyTextContentMessages() = withContentType<TextContent>()
fun Flow<ContentMessage<*>>.onlyVenueContentMessages() = withContentType<VenueContent>()
fun Flow<ContentMessage<*>>.onlyVideoContentMessages() = withContentType<VideoContent>()
fun Flow<ContentMessage<*>>.onlyVideoNoteContentMessages() = withContentType<VideoNoteContent>()
fun Flow<ContentMessage<*>>.onlyVoiceContentMessages() = withContentType<VoiceContent>()
