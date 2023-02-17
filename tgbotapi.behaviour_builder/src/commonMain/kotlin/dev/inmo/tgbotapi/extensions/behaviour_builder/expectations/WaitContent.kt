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
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = waitContentMessage<O>(initRequest, errorFactory).map { it.content }


@Deprecated(
    includeMediaGroupsDeprecationMessage,
    ReplaceWith("waitAnyContent(initRequest, errorFactory)", "dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitAnyContent")
)
suspend fun BehaviourContext.waitContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<MessageContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAnyContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<MessageContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<ContactContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<DiceContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<GameContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<LocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<LiveLocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<StaticLocationContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<PollContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<TextContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VenueContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<AudioMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<AudioMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<DocumentMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<DocumentMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<MediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<MediaContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<MediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<MediaGroupPartContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<VisualMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<VisualMediaGroupPartContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<TextedMediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<TextedMediaContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<AnimationContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<AudioContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<AudioContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<DocumentContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<DocumentContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
) = waitContent<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<StickerContent>(initRequest, errorFactory)
@Deprecated(includeMediaGroupsDeprecationMessage)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    includeMediaGroups: Boolean
) = waitContent<VideoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VideoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VideoNoteContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<VoiceContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitContent<InvoiceContent>(initRequest, errorFactory)
