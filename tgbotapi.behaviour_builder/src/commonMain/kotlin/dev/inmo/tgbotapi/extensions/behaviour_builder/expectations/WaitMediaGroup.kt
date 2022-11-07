package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias MediaGroupFilter<T> = SimpleFilter<List<MediaGroupMessage<T>>>


@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified O : MediaGroupPartContent> BehaviourContext.buildMediaGroupWaiter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<List<O>> = buildMediaGroupMessagesWaiter<O>(initRequest, errorFactory).map { it.map { it.content } }

suspend fun BehaviourContext.waitMediaGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<MediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPlaylist(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<AudioMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<DocumentMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VisualMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VideoContent>(initRequest, errorFactory)
