@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.steps.expectations

import dev.inmo.tgbotapi.extensions.steps.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asContentMessage
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import kotlinx.coroutines.flow.toList

typealias ContentMessageToContentMapper<T> = suspend ContentMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitContentMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend ContentMessage<MessageContent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asMessageUpdate() ?.data ?.asContentMessage() ?.mapper()
}.toList().toList()

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: ContentMessageToContentMapper<T>? = null
) : List<T> = waitContentMessage<T>(
    count,
    initRequest,
    errorFactory
) {
    if (content is T) {
        @Suppress("UNCHECKED_CAST")
        val message = (this as ContentMessage<T>)
        if (filter == null) {
            message.content
        } else {
            filter(message)
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitContact(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<ContactContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<DiceContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitGame(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<GameContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitLocation(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<LocationContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPoll(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<PollContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitText(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<TextContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVenue(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VenueContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<AudioMediaGroupContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<DocumentMediaGroupContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMedia(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<MediaContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<MediaGroupContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVisualMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VisualMediaGroupContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitAnimation(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<AnimationContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitAudio(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<AudioContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocument(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<DocumentContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPhoto(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<PhotoContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitSticker(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<StickerContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideo(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VideoContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideoNote(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VideoNoteContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVoice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VoiceContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitInvoice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<InvoiceContent>? = null
) = waitContent(count, initRequest, errorFactory, filter)
