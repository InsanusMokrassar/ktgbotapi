package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.sentMediaGroupUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified T : MediaGroupPartContent> BehaviourContext.buildMediaGroupMessagesWaiter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<List<MediaGroupMessage<T>>> = flowsUpdatesFilter.expectFlow(bot, initRequest, errorFactory) { update ->
    update.sentMediaGroupUpdateOrNull() ?.data ?.let { mediaGroup ->
        val mapped = mediaGroup.mapNotNull { it.withContent<T>() }
        listOf(
            mapped
        )
    } ?: emptyList()
}

suspend fun BehaviourContext.waitMediaGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<MediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPlaylistMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<AudioMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentsGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<DocumentMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VisualMediaGroupPartContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhotoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VideoContent>(initRequest, errorFactory)
