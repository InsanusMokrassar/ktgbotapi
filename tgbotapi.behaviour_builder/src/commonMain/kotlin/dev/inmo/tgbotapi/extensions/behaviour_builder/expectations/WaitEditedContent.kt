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
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = waitEditedContentMessage<O>(initRequest, errorFactory).map { it.content }

suspend fun BehaviourContext.waitEditedMessageContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<MessageContent>(initRequest, includeMediaGroups, errorFactory)

suspend fun BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<MessageContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<ContactContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<DiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GameContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<LiveLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StaticLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<TextContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VenueContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<AudioMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<DocumentMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitEditedContent<MediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<MediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<VisualMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitEditedContent<TextedMediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<AnimationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitEditedContent<AudioContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitEditedContent<DocumentContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitEditedContent<PhotoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<StickerContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitEditedContent<VideoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitEditedVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VideoNoteContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<VoiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitEditedInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<InvoiceContent>(initRequest, false, errorFactory)

suspend fun BehaviourContext.waitEditedScheduledGiveawayContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<ScheduledGiveawayContent>(initRequest, false, errorFactory)

suspend fun BehaviourContext.waitEditedGiveawayPublicResultsContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContent<GiveawayPublicResultsContent>(initRequest, false, errorFactory)

