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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<CommonMessage<O>> = expectFlow(
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
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<MessageContent>(errorFactory)

fun BehaviourContext.waitEditedContactMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<ContactContent>(errorFactory)
fun BehaviourContext.waitEditedDiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<DiceContent>(errorFactory)
fun BehaviourContext.waitEditedGameMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GameContent>(errorFactory)
fun BehaviourContext.waitEditedLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<LocationContent>(errorFactory)
fun BehaviourContext.waitEditedLiveLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<LiveLocationContent>(errorFactory)
fun BehaviourContext.waitEditedStaticLocationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<StaticLocationContent>(errorFactory)
fun BehaviourContext.waitEditedTextMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<TextContent>(errorFactory)
fun BehaviourContext.waitEditedVenueMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VenueContent>(errorFactory)
fun BehaviourContext.waitEditedAudioMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<AudioMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedDocumentMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<DocumentMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedMediaMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<MediaContent>(errorFactory)
fun BehaviourContext.waitEditedAnyMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<MediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedVisualMediaGroupContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VisualMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitEditedTextedMediaContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<TextedMediaContent>(errorFactory)
fun BehaviourContext.waitEditedAnimationMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<AnimationContent>(errorFactory)
fun BehaviourContext.waitEditedAudioMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<AudioContent>(errorFactory)
fun BehaviourContext.waitEditedDocumentMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<DocumentContent>(errorFactory)
fun BehaviourContext.waitEditedPhotoMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<PhotoContent>(errorFactory)
fun BehaviourContext.waitEditedStickerMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<StickerContent>(errorFactory)
fun BehaviourContext.waitEditedVideoMessage(
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitEditedContentMessage<VideoContent>(errorFactory)
fun BehaviourContext.waitEditedVideoNoteMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VideoNoteContent>(errorFactory)
fun BehaviourContext.waitEditedVoiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<VoiceContent>(errorFactory)
fun BehaviourContext.waitEditedInvoiceMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<InvoiceContent>(errorFactory)

fun BehaviourContext.waitEditedGiveawayContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GiveawayContent>(errorFactory)

fun BehaviourContext.waitEditedGiveawayPublicResultsContentMessage(
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitEditedContentMessage<GiveawayPublicResultsContent>(errorFactory)

