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
fun BehaviourContext.waitContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<MessageContent>> = expectFlow(
    errorFactory
) {
    if (it !is BaseSentMessageUpdate) {
        return@expectFlow emptyList()
    }
    listOfNotNull((it.data as? CommonMessage<*>))
}

inline fun <reified T : MessageContent> Flow<CommonMessage<MessageContent>>.mapWithContent() = mapNotNull { it.withContentOrNull<T>() }

fun BehaviourContext.waitAnyContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(errorFactory)
fun BehaviourContext.waitTextedContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContentMessage(errorFactory).mapWithContent<TextedContent>()
fun BehaviourContext.waitContactMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<ContactContent>()
fun BehaviourContext.waitDiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<DiceContent>()
fun BehaviourContext.waitGameMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<GameContent>()
fun BehaviourContext.waitLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<LocationContent>()
fun BehaviourContext.waitLiveLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<LiveLocationContent>()
fun BehaviourContext.waitStaticLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<StaticLocationContent>()
fun BehaviourContext.waitPollMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<PollContent>()
fun BehaviourContext.waitTextMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<TextContent>()
fun BehaviourContext.waitStoryMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<StoryContent>()
fun BehaviourContext.waitVenueMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VenueContent>()
fun BehaviourContext.waitAudioMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<AudioMediaGroupPartContent>()
fun BehaviourContext.waitDocumentMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<DocumentMediaGroupPartContent>()
fun BehaviourContext.waitMediaMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<MediaContent>()
fun BehaviourContext.waitAnyMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<MediaGroupPartContent>()
fun BehaviourContext.waitVisualMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitTextedMediaContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<TextedMediaContent>()
fun BehaviourContext.waitAnimationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<AnimationContent>()
fun BehaviourContext.waitAudioMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<AudioContent>()
fun BehaviourContext.waitDocumentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<DocumentContent>()
fun BehaviourContext.waitPhotoMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<PhotoContent>()
fun BehaviourContext.waitStickerMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<StickerContent>()
fun BehaviourContext.waitVideoMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VideoContent>()
fun BehaviourContext.waitVideoNoteMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VideoNoteContent>()
fun BehaviourContext.waitVoiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VoiceContent>()
fun BehaviourContext.waitInvoiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<InvoiceContent>()

fun BehaviourContext.waitVisualContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<VisualMediaGroupPartContent>()

fun BehaviourContext.waitMediaContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<MediaContent>()

fun BehaviourContext.waitGiveawayContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<GiveawayContent>()

fun BehaviourContext.waitGiveawayPublicResultsContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<GiveawayPublicResultsContent>()

fun BehaviourContext.waitGiveawayWinnersMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContentMessage(errorFactory)

fun BehaviourContext.waitPaidMediaInfoContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContentMessage(errorFactory).mapWithContent<PaidMediaInfoContent>()
