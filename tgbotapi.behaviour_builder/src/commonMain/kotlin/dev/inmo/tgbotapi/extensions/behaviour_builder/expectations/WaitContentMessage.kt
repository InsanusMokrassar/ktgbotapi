@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias CommonMessageToCommonMessageMapper<T> = suspend CommonMessage<T>.() -> CommonMessage<T>?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MessageContent> BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<O>> = expectFlow(
    initRequest,
    errorFactory
) {
    if (it !is BaseSentMessageUpdate) {
        return@expectFlow emptyList()
    }
    listOfNotNull((it.data as? CommonMessage<*>) ?.withContent<O>())
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

@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<MessageContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage<MessageContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<ContactContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<DiceContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<GameContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<LocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<LiveLocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<StaticLocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<PollContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<TextContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VenueContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<AudioMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<AudioMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<DocumentMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<DocumentMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<MediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<MediaContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<MediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<MediaGroupPartContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<VisualMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VisualMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<TextedMediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<TextedMediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<AnimationContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<AudioContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<AudioContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<DocumentContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<DocumentContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<StickerContent>(initRequest, errorFactory)
@Deprecated("includeMediaGroups is deprecated and its usage will not lead to any changes")
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContentMessage<VideoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VideoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VideoNoteContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<VoiceContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage<InvoiceContent>(initRequest, errorFactory)
