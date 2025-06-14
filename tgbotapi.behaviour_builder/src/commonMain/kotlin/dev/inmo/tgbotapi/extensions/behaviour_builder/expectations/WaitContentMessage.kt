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
inline fun BehaviourContext.waitContentMessage(
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

fun BehaviourContext.waitAnyContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory)
fun BehaviourContext.waitTextedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedContent>()
fun BehaviourContext.waitContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<ContactContent>()
fun BehaviourContext.waitDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DiceContent>()
fun BehaviourContext.waitGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GameContent>()
fun BehaviourContext.waitLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LocationContent>()
fun BehaviourContext.waitLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<LiveLocationContent>()
fun BehaviourContext.waitStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StaticLocationContent>()
fun BehaviourContext.waitPollMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PollContent>()
fun BehaviourContext.waitTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextContent>()
fun BehaviourContext.waitStoryMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StoryContent>()
fun BehaviourContext.waitVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VenueContent>()
fun BehaviourContext.waitAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioMediaGroupPartContent>()
fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentMediaGroupPartContent>()
fun BehaviourContext.waitMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()
fun BehaviourContext.waitAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaGroupPartContent>()
fun BehaviourContext.waitVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<TextedMediaContent>()
fun BehaviourContext.waitAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AnimationContent>()
fun BehaviourContext.waitAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<AudioContent>()
fun BehaviourContext.waitDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<DocumentContent>()
fun BehaviourContext.waitPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PhotoContent>()
fun BehaviourContext.waitStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<StickerContent>()
fun BehaviourContext.waitVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoContent>()
fun BehaviourContext.waitVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VideoNoteContent>()
fun BehaviourContext.waitVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VoiceContent>()
fun BehaviourContext.waitInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<InvoiceContent>()

fun BehaviourContext.waitVisualContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<VisualMediaGroupPartContent>()

fun BehaviourContext.waitMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<MediaContent>()

fun BehaviourContext.waitGiveawayContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayContent>()

fun BehaviourContext.waitGiveawayPublicResultsContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<GiveawayPublicResultsContent>()

fun BehaviourContext.waitGiveawayWinnersMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContentMessage(initRequest, errorFactory)

fun BehaviourContext.waitPaidMediaInfoContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(initRequest, errorFactory).mapWithContent<PaidMediaInfoContent>()
