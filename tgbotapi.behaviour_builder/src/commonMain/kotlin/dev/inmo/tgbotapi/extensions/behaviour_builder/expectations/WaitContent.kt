@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias CommonMessageToContentMapper<T> = suspend CommonMessage<T>.() -> T?

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MessageContent> BehaviourContext.waitContent(
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = waitContentMessage<O>(initRequest, includeMediaGroups, errorFactory).map { it.content }


suspend fun BehaviourContext.waitContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<MessageContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<ContactContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<DiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<GameContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<LocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<LiveLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<StaticLocationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<PollContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<TextContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VenueContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<AudioMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<DocumentMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContent<MediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<MediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<VisualMediaGroupPartContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = true
) = waitContent<TextedMediaContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<AnimationContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContent<AudioContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContent<DocumentContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContent<PhotoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<StickerContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean = false
) = waitContent<VideoContent>(initRequest, includeMediaGroups, errorFactory)
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VideoNoteContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VoiceContent>(initRequest, false, errorFactory)
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<InvoiceContent>(initRequest, false, errorFactory)
