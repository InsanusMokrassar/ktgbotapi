@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import kotlinx.coroutines.flow.toList

typealias CommonMessageToContentMapper<T> = suspend CommonMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitCommonMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
    mapper: suspend CommonMessage<MessageContent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    if (includeMediaGroups) {
        it.asSentMediaGroupUpdate() ?.data ?.mapNotNull {
            (it as CommonMessage<MessageContent>).mapper()
        } ?.let { return@expectFlow it }
    }
    it.asBaseSentMessageUpdate() ?.data ?.asCommonMessage() ?.mapper().let(::listOfNotNull)
}.toList().toList()

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: CommonMessageToContentMapper<T>? = null
) : List<T> = waitCommonMessage<T>(
    count,
    initRequest,
    includeMediaGroups,
    errorFactory
) {
    if (content is T) {
        @Suppress("UNCHECKED_CAST")
        val message = (this as CommonMessage<T>)
        if (filter == null) {
            message.content
        } else {
            safelyWithoutExceptions { filter(message) }
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<MessageContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<ContactContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<DiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<GameContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<LocationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<PollContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<TextContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<VenueContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: CommonMessageToContentMapper<AudioMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: CommonMessageToContentMapper<DocumentMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: CommonMessageToContentMapper<MediaContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: CommonMessageToContentMapper<MediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: CommonMessageToContentMapper<VisualMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<AnimationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: CommonMessageToContentMapper<AudioContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: CommonMessageToContentMapper<DocumentContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: CommonMessageToContentMapper<PhotoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<StickerContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: CommonMessageToContentMapper<VideoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<VideoNoteContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<VoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: CommonMessageToContentMapper<InvoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
