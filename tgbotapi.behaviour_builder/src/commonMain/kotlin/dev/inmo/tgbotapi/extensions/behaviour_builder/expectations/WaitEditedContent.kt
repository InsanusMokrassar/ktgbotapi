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
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = waitEditedContentMessage<O>(initRequest, errorFactory).map { it.content }

fun BehaviourContext.waitEditedMessageContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<ContactContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<DiceContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GameContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LiveLocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StaticLocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<TextContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VenueContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VisualMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<TextedMediaContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<AnimationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<PhotoContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StickerContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VideoContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VideoNoteContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VoiceContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<InvoiceContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedGiveawayContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GiveawayContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedGiveawayPublicResultsContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GiveawayPublicResultsContent>(initRequest, errorFactory)

