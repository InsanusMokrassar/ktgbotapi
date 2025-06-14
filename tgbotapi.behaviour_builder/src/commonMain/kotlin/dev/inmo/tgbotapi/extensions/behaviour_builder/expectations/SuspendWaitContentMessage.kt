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

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun BehaviourContext.waitContentMessage(
    initRequest: Request<*>,
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

suspend fun BehaviourContext.waitAnyContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextedContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedContent>()
suspend fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<ContactContent>()
suspend fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DiceContent>()
suspend fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GameContent>()
suspend fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LocationContent>()
suspend fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LiveLocationContent>()
suspend fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StaticLocationContent>()
suspend fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PollContent>()
suspend fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextContent>()
suspend fun BehaviourContext.waitStoryMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StoryContent>()
suspend fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VenueContent>()
suspend fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioMediaGroupPartContent>()
suspend fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentMediaGroupPartContent>()
suspend fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()
suspend fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaGroupPartContent>()
suspend fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()
suspend fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedMediaContent>()
suspend fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AnimationContent>()
suspend fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioContent>()
suspend fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentContent>()
suspend fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PhotoContent>()
suspend fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StickerContent>()
suspend fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoContent>()
suspend fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoNoteContent>()
suspend fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VoiceContent>()
suspend fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<InvoiceContent>()

suspend fun BehaviourContext.waitVisualContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()

suspend fun BehaviourContext.waitMediaContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()

suspend fun BehaviourContext.waitGiveawayContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayContent>()

suspend fun BehaviourContext.waitGiveawayPublicResultsContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayPublicResultsContent>()

suspend fun BehaviourContext.waitGiveawayWinnersMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContentMessage(initRequest, errorFactory)

suspend fun BehaviourContext.waitPaidMediaInfoContentMessage(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PaidMediaInfoContent>()
