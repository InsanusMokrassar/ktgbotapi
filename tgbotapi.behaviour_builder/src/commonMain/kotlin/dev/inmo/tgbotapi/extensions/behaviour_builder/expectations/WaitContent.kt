@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.content.media.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias CommonMessageToContentMapper<T> = suspend CommonMessage<T>.() -> T?

private suspend fun <O> BehaviourContext.waitCommonMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: suspend CommonMessage<MessageContent>.() -> O?
): Flow<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val messages = when (it) {
        is SentMediaGroupUpdate -> {
            if (includeMediaGroups) {
                it.data.map { it as CommonMessage<MessageContent> }
            } else {
                emptyList()
            }
        }
        is BaseSentMessageUpdate -> listOf(it.data)
        else -> return@expectFlow emptyList()
    }
    messages.mapNotNull { message ->
        val asCommonMessage = message as CommonMessage<MessageContent>
        if (filter == null || filter(asCommonMessage)) {
            asCommonMessage.mapper()
        } else {
            null
        }
    }
}

internal inline fun <reified T : MessageContent> contentConverter(
    noinline mapper: CommonMessageToContentMapper<T>? = null
): suspend CommonMessage<MessageContent>.() -> T? = mapper ?.let {
    {
        if (content is T) {
            @Suppress("UNCHECKED_CAST")
            val message = (this as CommonMessage<T>)
            safelyWithoutExceptions { mapper(message) }
        } else {
            null
        }
    }
} ?: { content as? T }

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: SimpleFilter<CommonMessage<T>>? = null,
    noinline mapper: CommonMessageToContentMapper<T>? = null
) : List<T> = waitCommonMessage<T>(
    count,
    initRequest,
    includeMediaGroups,
    errorFactory,
    filter ?.let {
        {
            it.withContent<T>() ?.let { filter(it) } == true
        }
    },
    contentConverter(mapper)
).toList()

suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: CommonMessageToContentMapper<MessageContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null,
    mapper: CommonMessageToContentMapper<ContactContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null,
    mapper: CommonMessageToContentMapper<DiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null,
    mapper: CommonMessageToContentMapper<GameContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null,
    mapper: CommonMessageToContentMapper<LocationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null,
    mapper: CommonMessageToContentMapper<LiveLocationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null,
    mapper: CommonMessageToContentMapper<StaticLocationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<PollContent>>? = null,
    mapper: CommonMessageToContentMapper<PollContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null,
    mapper: CommonMessageToContentMapper<TextContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null,
    mapper: CommonMessageToContentMapper<VenueContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<AudioMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<DocumentMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null,
    mapper: CommonMessageToContentMapper<MediaContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<MediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<VisualMediaGroupContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null,
    mapper: CommonMessageToContentMapper<TextedMediaContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null,
    mapper: CommonMessageToContentMapper<AnimationContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null,
    mapper: CommonMessageToContentMapper<AudioContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null,
    mapper: CommonMessageToContentMapper<DocumentContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null,
    mapper: CommonMessageToContentMapper<PhotoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null,
    mapper: CommonMessageToContentMapper<StickerContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null,
    mapper: CommonMessageToContentMapper<VideoContent>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null,
    mapper: CommonMessageToContentMapper<VideoNoteContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null,
    mapper: CommonMessageToContentMapper<VoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null,
    mapper: CommonMessageToContentMapper<InvoiceContent>? = null
) = waitContent(count, initRequest, false, errorFactory, filter, mapper)
