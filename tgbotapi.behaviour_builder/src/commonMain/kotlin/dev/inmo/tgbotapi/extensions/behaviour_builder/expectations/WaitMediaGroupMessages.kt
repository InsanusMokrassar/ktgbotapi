package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.baseSentMessageUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.commonMessageOrNull
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.extensions.utils.withContentOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified T : MediaGroupPartContent> BehaviourContext.buildMediaGroupMessagesWaiter(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MediaGroupMessage<T>> = flowsUpdatesFilter.expectFlow(bot, initRequest, errorFactory) { update ->
    update.baseSentMessageUpdateOrNull() ?.data ?.commonMessageOrNull() ?.withContentOrNull<MediaGroupContent<*>>() ?.let { message ->
        if (message.content.group.all { it.content is T }) {
            @Suppress("UNCHECKED_CAST")
            listOf(message as MediaGroupMessage<T>)
        } else {
            null
        }
    } ?: emptyList()
}

fun BehaviourContext.waitMediaGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<MediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitPlaylistMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<AudioMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitDocumentsGroupMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<DocumentMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitVisualGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VisualMediaGroupPartContent>(initRequest, errorFactory)
fun BehaviourContext.waitPhotoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<PhotoContent>(initRequest, errorFactory)
fun BehaviourContext.waitVideoGalleryMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupMessagesWaiter<VideoContent>(initRequest, errorFactory)
