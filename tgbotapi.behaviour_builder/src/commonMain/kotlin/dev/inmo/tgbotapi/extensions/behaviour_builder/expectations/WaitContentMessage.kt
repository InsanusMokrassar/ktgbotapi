@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.withContentOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapNotNull

typealias CommonMessageToCommonMessageMapper<T> = suspend CommonMessage<T>.() -> CommonMessage<T>?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<MessageContent>> = expectFlow(
    initRequest,
    errorFactory
) {
    if (it !is BaseSentMessageUpdate) {
        return@expectFlow emptyList()
    }
    listOfNotNull((it.data as? CommonMessage<*>))
}

inline fun <reified T : MessageContent> Flow<CommonMessage<MessageContent>>.mapWithContent() = mapNotNull { it.withContentOrNull<T>() }

suspend fun BehaviourContext.waitAnyContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedContent>()
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<ContactContent>()
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DiceContent>()
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GameContent>()
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LocationContent>()
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LiveLocationContent>()
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StaticLocationContent>()
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PollContent>()
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextContent>()
suspend fun BehaviourContext.waitStoryMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StoryContent>()
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VenueContent>()
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioMediaGroupPartContent>()
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentMediaGroupPartContent>()
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaGroupPartContent>()
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedMediaContent>()
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AnimationContent>()
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioContent>()
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentContent>()
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PhotoContent>()
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StickerContent>()
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoContent>()
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoNoteContent>()
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VoiceContent>()
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<InvoiceContent>()

suspend fun BehaviourContext.waitVisualContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()

suspend fun BehaviourContext.waitMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()

suspend fun BehaviourContext.waitGiveawayContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayContent>()

suspend fun BehaviourContext.waitGiveawayPublicResultsContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayPublicResultsContent>()
