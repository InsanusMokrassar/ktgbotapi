package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asSentMediaGroupUpdate
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

@RiskFeature(lowLevelRiskFeatureMessage)
suspend inline fun <reified T : MediaGroupContent> BehaviourContext.buildMediaGroupMessagesWaiter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<List<MediaGroupMessage<T>>> = flowsUpdatesFilter.expectFlow(bot, initRequest, errorFactory) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        val mapped = mediaGroup.mapNotNull { it.withContent<T>() }
        listOf(
            mapped
        )
    } ?: emptyList()
}

suspend fun BehaviourContext.waitMediaGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<MediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPlaylistMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<AudioMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitDocumentsGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<DocumentMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVisualGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VisualMediaGroupContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitPhotoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<PhotoContent>(initRequest, errorFactory)
suspend fun BehaviourContext.waitVideoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VideoContent>(initRequest, errorFactory)
