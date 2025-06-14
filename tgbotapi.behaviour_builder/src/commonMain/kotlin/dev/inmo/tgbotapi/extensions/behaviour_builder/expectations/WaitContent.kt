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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MessageContent> = waitContentMessage(errorFactory).map { it.content }

inline fun <reified T : MessageContent> Flow<MessageContent>.mapContent() = mapNotNull { it as? T }

fun BehaviourContext.waitAnyContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory)
fun BehaviourContext.waitTextedContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<TextedContent>()
fun BehaviourContext.waitContact(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<ContactContent>()
fun BehaviourContext.waitDice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<DiceContent>()
fun BehaviourContext.waitGame(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<GameContent>()
fun BehaviourContext.waitLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<LocationContent>()
fun BehaviourContext.waitLiveLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<LiveLocationContent>()
fun BehaviourContext.waitStaticLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<StaticLocationContent>()
fun BehaviourContext.waitPoll(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<PollContent>()
fun BehaviourContext.waitText(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<TextContent>()
fun BehaviourContext.waitStory(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<StoryContent>()
fun BehaviourContext.waitVenue(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<VenueContent>()
fun BehaviourContext.waitAudioMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<AudioMediaGroupPartContent>()
fun BehaviourContext.waitDocumentMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<DocumentMediaGroupPartContent>()
fun BehaviourContext.waitMedia(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<MediaContent>()
fun BehaviourContext.waitAnyMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<MediaGroupPartContent>()
fun BehaviourContext.waitVisualMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitTextedMediaContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<TextedMediaContent>()
fun BehaviourContext.waitAnimation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<AnimationContent>()
fun BehaviourContext.waitAudio(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<AudioContent>()
fun BehaviourContext.waitDocument(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<DocumentContent>()
fun BehaviourContext.waitPhoto(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent(errorFactory).mapContent<PhotoContent>()
fun BehaviourContext.waitSticker(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<StickerContent>()
fun BehaviourContext.waitVideo(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<VideoContent>()
fun BehaviourContext.waitVideoNote(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<VideoNoteContent>()
fun BehaviourContext.waitVoice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<VoiceContent>()
fun BehaviourContext.waitInvoice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<InvoiceContent>()
fun BehaviourContext.waitVisualContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<VisualMediaGroupPartContent>()
fun BehaviourContext.waitMediaContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<MediaContent>()

fun BehaviourContext.waitGiveawayContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<GiveawayContent>()

fun BehaviourContext.waitGiveawayPublicResultsContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<GiveawayPublicResultsContent>()

fun BehaviourContext.waitGiveawayWinners(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitGiveawayPublicResultsContent(errorFactory)

fun BehaviourContext.waitPaidMediaInfoContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent(errorFactory).mapContent<PaidMediaInfoContent>()
