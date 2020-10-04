package dev.inmo.tgbotapi.extensions.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.abstracts.MessageContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.payments.InvoiceContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull
import kotlin.reflect.KClass

fun <T : MessageContent> Flow<ContentMessage<*>>.withContentType(contentType: KClass<T>) = mapNotNull {
    if (contentType.isInstance(it.content)) {
        @Suppress("UNCHECKED_CAST")
        it as ContentMessage<T>
    } else {
        null
    }
}

fun Flow<ContentMessage<*>>.onlyAnimationContentMessages() = withContentType(AnimationContent::class)
fun Flow<ContentMessage<*>>.onlyAudioContentMessages() = withContentType(AudioContent::class)
fun Flow<ContentMessage<*>>.onlyContactContentMessages() = withContentType(ContactContent::class)
fun Flow<ContentMessage<*>>.onlyDiceContentMessages() = withContentType(DiceContent::class)
fun Flow<ContentMessage<*>>.onlyDocumentContentMessages() = withContentType(DocumentContent::class)
fun Flow<ContentMessage<*>>.onlyGameContentMessages() = withContentType(GameContent::class)
fun Flow<ContentMessage<*>>.onlyInvoiceContentMessages() = withContentType(InvoiceContent::class)
fun Flow<ContentMessage<*>>.onlyLocationContentMessages() = withContentType(LocationContent::class)
fun Flow<ContentMessage<*>>.onlyPhotoContentMessages() = withContentType(PhotoContent::class)
fun Flow<ContentMessage<*>>.onlyPollContentMessages() = withContentType(PollContent::class)
fun Flow<ContentMessage<*>>.onlyStickerContentMessages() = withContentType(StickerContent::class)
fun Flow<ContentMessage<*>>.onlyTextContentMessages() = withContentType(TextContent::class)
fun Flow<ContentMessage<*>>.onlyVenueContentMessages() = withContentType(VenueContent::class)
fun Flow<ContentMessage<*>>.onlyVideoContentMessages() = withContentType(VideoContent::class)
fun Flow<ContentMessage<*>>.onlyVideoNoteContentMessages() = withContentType(VideoNoteContent::class)
fun Flow<ContentMessage<*>>.onlyVoiceContentMessages() = withContentType(VoiceContent::class)
