@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.giveaway.GiveawayPublicResults
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun BehaviourContext.waitContent(
    initRequest: Request<*>,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitContentMessage(initRequest, errorFactory).map { it.content }

suspend fun BehaviourContext.waitAnyContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextedContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<TextedContent>()
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<ContactContent>()
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<DiceContent>()
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GameContent>()
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<LocationContent>()
suspend fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<LiveLocationContent>()
suspend fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StaticLocationContent>()
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<PollContent>()
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<TextContent>()
suspend fun BehaviourContext.waitStory(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StoryContent>()
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VenueContent>()
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<AudioMediaGroupPartContent>()
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<DocumentMediaGroupPartContent>()
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<MediaContent>()
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<MediaGroupPartContent>()
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<VisualMediaGroupPartContent>()
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<TextedMediaContent>()
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<AnimationContent>()
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<AudioContent>()
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<DocumentContent>()
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<PhotoContent>()
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StickerContent>()
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VideoContent>()
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VideoNoteContent>()
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VoiceContent>()
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<InvoiceContent>()
suspend fun BehaviourContext.waitVisualContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VisualMediaGroupPartContent>()
suspend fun BehaviourContext.waitMediaContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<MediaContent>()

suspend fun BehaviourContext.waitGiveawayContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GiveawayContent>()

suspend fun BehaviourContext.waitGiveawayPublicResultsContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GiveawayPublicResultsContent>()

suspend fun BehaviourContext.waitGiveawayWinners(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContent(initRequest, errorFactory)

suspend fun BehaviourContext.waitPaidMediaInfoContent(
    initRequest: Request<*>,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<PaidMediaInfoContent>()
