@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.commonMessageOrNull
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MessageContent> BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
): Flow<CommonMessage<O>> =
    expectFlow(
        initRequest,
        errorFactory,
    ) {
        val messages =
            when (it) {
                is BaseEditMessageUpdate -> {
                    val commonMessage = it.data.commonMessageOrNull() ?: return@expectFlow emptyList()
                    listOf(commonMessage)
                }
                else -> return@expectFlow emptyList()
            }
        messages.mapNotNull { message ->
            (message as CommonMessage<*>).withContent<O>()
        }
    }

suspend fun BehaviourContext.waitEditedMessageContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<MessageContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<ContactContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<DiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<GameContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<LocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<LiveLocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<StaticLocationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<TextContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VenueContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<AudioMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<DocumentMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<MediaContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<MediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VisualMediaGroupPartContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<TextedMediaContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<AnimationContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<AudioContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<DocumentContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<PhotoContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<StickerContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VideoContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VideoNoteContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VoiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<InvoiceContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGiveawayContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<GiveawayContent>(initRequest, errorFactory)

suspend fun BehaviourContext.waitEditedGiveawayPublicResultsContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<GiveawayPublicResultsContent>(initRequest, errorFactory)
