@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList

typealias CommonMessageToCommonMessageMapper<T> = suspend CommonMessage<T>.() -> CommonMessage<T>?

internal suspend fun <O : MessageContent> BehaviourContext.waitCommonMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: suspend CommonMessage<MessageContent>.() -> CommonMessage<O>?
): Flow<CommonMessage<O>> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val messages = when (it) {
        is SentMediaGroupUpdate -> {
            if (includeMediaGroups) {
                it.data
            } else {
                emptyList()
            }
        }
        is BaseSentMessageUpdate -> listOf(it.data)
        else -> return@expectFlow emptyList()
    }
    messages.mapNotNull { message ->
        @Suppress("UNCHECKED_CAST")
        val asCommonMessage = message as? CommonMessage<MessageContent> ?: return@mapNotNull null
        if (filter == null || filter(asCommonMessage)) {
            asCommonMessage.mapper()
        } else {
            null
        }
    }
}

internal inline fun <reified T : MessageContent> contentMessageConverter(
    noinline mapper: CommonMessageToCommonMessageMapper<T>? = null
): suspend CommonMessage<MessageContent>.() -> CommonMessage<T>? = mapper ?.let {
    {
        if (content is T) {
            @Suppress("UNCHECKED_CAST")
            val message = (this as CommonMessage<T>)
            safelyWithoutExceptions { mapper(message) }
        } else {
            null
        }
    }
} ?: {
    @Suppress("UNCHECKED_CAST")
    if (content is T) this as CommonMessage<T> else null
}

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitContentMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<T>>? = null,
    noinline mapper: CommonMessageToCommonMessageMapper<T>? = null
) : List<CommonMessage<T>> = waitCommonMessage<T>(
    count,
    initRequest,
    includeMediaGroups,
    errorFactory,
    filter ?.let {
        {
            it.withContent<T>() ?.let { filter(it) } == true
        }
    },
    contentMessageConverter(mapper)
).toList()

suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<MessageContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<ContactContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<DiceContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<GameContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<LocationContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<LiveLocationContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<StaticLocationContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<PollContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<PollContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<TextContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<VenueContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<AudioMediaGroupContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<DocumentMediaGroupContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<MediaContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<MediaGroupContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<VisualMediaGroupContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<TextedMediaContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<AnimationContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<AudioContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<DocumentContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<PhotoContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<StickerContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<VideoContent>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<VideoNoteContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<VoiceContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null,
    mapper: CommonMessageToCommonMessageMapper<InvoiceContent>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter, mapper)
