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

typealias CommonMessageToContentMapper<T> = suspend CommonMessage<T>.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun BehaviourContext.waitContent(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitContentMessage(initRequest, errorFactory).map { it.content }

inline fun <reified T : MessageContent> Flow<MessageContent>.mapContent() = mapNotNull { it as? T }

fun BehaviourContext.waitAnyContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory)
fun BehaviourContext.waitTextedContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<TextedContent>()
fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<ContactContent>()
fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<DiceContent>()
fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GameContent>()
fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<LocationContent>()
fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<LiveLocationContent>()
fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StaticLocationContent>()
fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<PollContent>()
fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<TextContent>()
fun BehaviourContext.waitStory(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StoryContent>()
fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VenueContent>()
fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<AudioMediaGroupPartContent>()
fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<DocumentMediaGroupPartContent>()
fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<MediaContent>()
fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<MediaGroupPartContent>()
fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<TextedMediaContent>()
fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<AnimationContent>()
fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<AudioContent>()
fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<DocumentContent>()
fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(initRequest, errorFactory).mapContent<PhotoContent>()
fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<StickerContent>()
fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VideoContent>()
fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VideoNoteContent>()
fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VoiceContent>()
fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<InvoiceContent>()
fun BehaviourContext.waitVisualContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<MediaContent>()

fun BehaviourContext.waitGiveawayContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GiveawayContent>()

fun BehaviourContext.waitGiveawayPublicResultsContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<GiveawayPublicResultsContent>()

fun BehaviourContext.waitGiveawayWinners(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContent(initRequest, errorFactory)

fun BehaviourContext.waitPaidMediaInfoContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(initRequest, errorFactory).mapContent<PaidMediaInfoContent>()
