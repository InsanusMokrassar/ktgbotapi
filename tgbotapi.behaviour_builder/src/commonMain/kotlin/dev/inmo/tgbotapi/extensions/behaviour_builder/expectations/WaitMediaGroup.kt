package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.sentMediaGroupUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias MediaGroupFilter<T> = SimpleFilter<List<MediaGroupMessage<T>>>


@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MediaGroupContent> BehaviourContext.buildMediaGroupWaiter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<List<O>> = buildMediaGroupMessagesWaiter<O>(initRequest, errorFactory).map { it.map { it.content } }

suspend fun BehaviourContext.waitMediaGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<MediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPlaylist(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<AudioMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<DocumentMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VisualMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VideoContent>(initRequest, errorFactory)
