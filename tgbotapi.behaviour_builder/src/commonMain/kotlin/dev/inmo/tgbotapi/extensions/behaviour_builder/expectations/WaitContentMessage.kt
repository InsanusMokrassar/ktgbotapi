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

typealias CommonMessageToCommonMessageMapper<T> = suspend CommonMessage<T>.() -> CommonMessage<T>?

internal suspend inline fun <reified O : MessageContent> BehaviourContext.waitContentMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<O>>? = null
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
        val asCommonMessage = (message as? CommonMessage<*>) ?.withContent<O>() ?: return@mapNotNull null
        if (filter == null || filter(asCommonMessage)) {
            asCommonMessage
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

suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<PollContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null
) = waitContentMessage(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null
) = waitContentMessage(count, initRequest, false, errorFactory, filter)
