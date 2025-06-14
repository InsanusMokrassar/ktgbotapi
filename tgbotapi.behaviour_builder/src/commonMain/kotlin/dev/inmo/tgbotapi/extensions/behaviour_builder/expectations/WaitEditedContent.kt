@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O : MessageContent> BehaviourContext.waitEditedContent(
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = waitEditedContentMessage<O>(errorFactory).map { it.content }

fun BehaviourContext.waitEditedMessageContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(errorFactory)

fun BehaviourContext.waitEditedContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(errorFactory)
fun BehaviourContext.waitEditedContact(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<ContactContent>(errorFactory)
fun BehaviourContext.waitEditedDice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<DiceContent>(errorFactory)
fun BehaviourContext.waitEditedGame(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GameContent>(errorFactory)
fun BehaviourContext.waitEditedLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LocationContent>(errorFactory)
fun BehaviourContext.waitEditedLiveLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LiveLocationContent>(errorFactory)
fun BehaviourContext.waitEditedStaticLocation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StaticLocationContent>(errorFactory)
fun BehaviourContext.waitEditedText(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<TextContent>(errorFactory)
fun BehaviourContext.waitEditedVenue(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VenueContent>(errorFactory)
fun BehaviourContext.waitEditedAudioMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedMedia(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaContent>(errorFactory)
fun BehaviourContext.waitEditedAnyMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedVisualMediaGroupContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VisualMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedTextedMediaContent(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<TextedMediaContent>(errorFactory)
fun BehaviourContext.waitEditedAnimation(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<AnimationContent>(errorFactory)
fun BehaviourContext.waitEditedAudio(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioContent>(errorFactory)
fun BehaviourContext.waitEditedDocument(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentContent>(errorFactory)
fun BehaviourContext.waitEditedPhoto(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<PhotoContent>(errorFactory)
fun BehaviourContext.waitEditedSticker(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StickerContent>(errorFactory)
fun BehaviourContext.waitEditedVideo(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VideoContent>(errorFactory)
fun BehaviourContext.waitEditedVideoNote(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VideoNoteContent>(errorFactory)
fun BehaviourContext.waitEditedVoice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VoiceContent>(errorFactory)
fun BehaviourContext.waitEditedInvoice(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<InvoiceContent>(errorFactory)

fun BehaviourContext.waitEditedGiveawayContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GiveawayContent>(errorFactory)

fun BehaviourContext.waitEditedGiveawayPublicResultsContent(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GiveawayPublicResultsContent>(errorFactory)

