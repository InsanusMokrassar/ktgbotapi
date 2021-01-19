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
    noinline filter: (suspend (List<MediaGroupMessage<T>>) -> Boolean)? = null
) = flowsUpdatesFilter.expectFlow(bot, initRequest, count, errorFactory) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        if (mediaGroup.all { message -> message.content is T } && (filter == null || filter(mediaGroup as List<MediaGroupMessage<T>>))) {
            listOf(
                mediaGroup.map { it.content as T } as List<MediaGroupMessage<T>>
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
    filter: (suspend (List<MediaGroupMessage<AudioMediaGroupContent>>) -> Boolean)? = null
) = onMediaGroup(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage<DocumentMediaGroupContent>>) -> Boolean)? = null
) = onMediaGroup(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage<VisualMediaGroupContent>>) -> Boolean)? = null
) = onMediaGroup(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage<PhotoContent>>) -> Boolean)? = null
) = onMediaGroup(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: (suspend (List<MediaGroupMessage<VideoContent>>) -> Boolean)? = null
) = onMediaGroup(count, initRequest, errorFactory, filter)
