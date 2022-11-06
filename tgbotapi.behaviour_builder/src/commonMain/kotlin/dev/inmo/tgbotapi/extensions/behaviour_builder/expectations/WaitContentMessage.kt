@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias CommonMessageToCommonMessageMapper<T> = suspend CommonMessage<T>.() -> CommonMessage<T>?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MessageContent> BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<O>> = expectFlow(
    initRequest,
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
        (message as? CommonMessage<*>) ?.withContent<O>()
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
    includeMediaGroups: Boolean = true
) = waitContentMessage<MessageContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<ContactContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<DiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<GameContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<LocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<LiveLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<StaticLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<PollContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<TextContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VenueContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContentMessage<AudioMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContentMessage<DocumentMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContentMessage<MediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContentMessage<MediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContentMessage<VisualMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContentMessage<TextedMediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<AnimationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContentMessage<AudioContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContentMessage<DocumentContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContentMessage<PhotoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<StickerContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContentMessage<VideoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VideoNoteContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VoiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<InvoiceContent>(initRequest, false, errorFactory)
