@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import kotlinx.coroutines.flow.toList

typealias ContentMessageToContentMapper<T> = suspend ContentMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitContentMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend ContentMessage<MessageContent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    if (includeMediaGroups) {
        it.asSentMediaGroupUpdate() ?.data ?.mapNotNull {
            (it as ContentMessage<MessageContent>).mapper()
        } ?.let { return@expectFlow it }
    }
    it.asMessageUpdate() ?.data ?.asContentMessage() ?.mapper().let(::listOfNotNull)
}.toList().toList()

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: ContentMessageToContentMapper<T>? = null
) : List<T> = waitContentMessage<T>(
    count,
    initRequest,
    includeMediaGroups,
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
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitDice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<DiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitGame(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<GameContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLocation(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<LocationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitPoll(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<PollContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitText(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<TextContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVenue(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VenueContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<AudioMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<DocumentMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitMedia(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<MediaContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<MediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVisualMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<VisualMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnimation(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<AnimationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudio(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<AudioContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocument(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<DocumentContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitPhoto(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<PhotoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitSticker(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<StickerContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVideo(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true,
    filter: ContentMessageToContentMapper<VideoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVideoNote(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VideoNoteContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVoice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<VoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitInvoice(
    count: Int = 1,
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: ContentMessageToContentMapper<InvoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
