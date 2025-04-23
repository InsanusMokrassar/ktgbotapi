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
suspend inline fun <reified O : MessageContent> BehaviourContext.waitEditedContent(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
): Flow<O> = waitEditedContentMessage<O>(initRequest, errorFactory).map { it.content }

suspend fun BehaviourContext.waitEditedMessageContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MessageContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<ContactContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<GameContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<LocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<LiveLocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<StaticLocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<TextContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VenueContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<MediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VisualMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<TextedMediaContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AnimationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<AudioContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<DocumentContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<PhotoContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<StickerContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VideoContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VideoNoteContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<VoiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<InvoiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGiveawayContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<GiveawayContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGiveawayPublicResultsContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContent<GiveawayPublicResultsContent>(initRequest, errorFactory)
