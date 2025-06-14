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
inline fun <reified O : MessageContent> BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<O>> = expectFlow(
    initRequest,
    errorFactory
) {
    val messages = when (it) {
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

fun BehaviourContext.waitEditedMessageContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<MessageContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedContactMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<ContactContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<DiceContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedGameMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GameContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<LocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedLiveLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<LiveLocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedStaticLocationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<StaticLocationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedTextMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<TextContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVenueMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VenueContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAudioMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<AudioMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDocumentMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<DocumentMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedMediaMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<MediaContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAnyMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<MediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVisualMediaGroupContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VisualMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedTextedMediaContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<TextedMediaContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAnimationMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<AnimationContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedAudioMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<AudioContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedDocumentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<DocumentContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedPhotoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<PhotoContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedStickerMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<StickerContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVideoMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VideoContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVideoNoteMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VideoNoteContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedVoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VoiceContent>(initRequest, errorFactory)
fun BehaviourContext.waitEditedInvoiceMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<InvoiceContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedGiveawayContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GiveawayContent>(initRequest, errorFactory)

fun BehaviourContext.waitEditedGiveawayPublicResultsContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GiveawayPublicResultsContent>(initRequest, errorFactory)

