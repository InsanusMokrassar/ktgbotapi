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
inline fun <reified T : MediaGroupPartContent> BehaviourContext.buildMediaGroupWaiter(
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<MediaGroupContent<T>> = flowsUpdatesFilter.expectFlow(bot, errorFactory) { update ->
    update.baseSentMessageUpdateOrNull() ?.data ?.commonMessageOrNull() ?.withContentOrNull<MediaGroupContent<*>>() ?.let { message ->
        if (message.content.group.all { it is T }) {
            listOf(message.content as MediaGroupContent<T>)
        } else {
            null
        }
    } ?: emptyList()
}

fun BehaviourContext.waitMediaGroup(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<MediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitPlaylist(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<AudioMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitDocumentsGroup(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<DocumentMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitVisualGallery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VisualMediaGroupPartContent>(errorFactory)
fun BehaviourContext.waitPhotoGallery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<PhotoContent>(errorFactory)
fun BehaviourContext.waitVideoGallery(
    errorFactory: NullableRequestBuilder<*> = { null }
) = buildMediaGroupWaiter<VideoContent>(errorFactory)
