package dev.inmo.tgbotapi.extensions.utils

import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

private inline fun <reified T : MessageContent> Flow<ContentMessage<*>>.withContentType() = mapNotNull {
    it.withContent<T>()
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

fun Flow<ContentMessage<*>>.onlyStoryContentMessages() = withContentType<StoryContent>()

fun Flow<ContentMessage<*>>.onlyVenueContentMessages() = withContentType<VenueContent>()

fun Flow<ContentMessage<*>>.onlyVideoContentMessages() = withContentType<VideoContent>()

fun Flow<ContentMessage<*>>.onlyVideoNoteContentMessages() = withContentType<VideoNoteContent>()

fun Flow<ContentMessage<*>>.onlyVoiceContentMessages() = withContentType<VoiceContent>()
