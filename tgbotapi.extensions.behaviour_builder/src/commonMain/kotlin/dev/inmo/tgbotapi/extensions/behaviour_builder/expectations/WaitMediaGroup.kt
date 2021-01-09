package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asSentMediaGroupUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.onMediaGroup(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = flowsUpdatesFilter.expectFlow(bot, initRequest, count, errorFactory) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        if (mediaGroup.all { message -> message.content is T } && (filter == null || filter(mediaGroup))) {
            listOf(
                mediaGroup.map { it.content as T }
            )
        } else {
            null
        }
    } ?: emptyList()
}.take(count).toList()

suspend fun BehaviourContext.waitPlaylist(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = onMediaGroup<AudioMediaGroupContent>(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = onMediaGroup<DocumentMediaGroupContent>(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = onMediaGroup<VisualMediaGroupContent>(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = onMediaGroup<PhotoContent>(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null
) = onMediaGroup<VideoContent>(count, initRequest, errorFactory, filter)
