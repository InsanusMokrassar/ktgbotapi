package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.asSentMediaGroupUpdate
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.content.media.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.VisualMediaGroupContent
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

typealias MediaGroupFilter<T> = suspend List<MediaGroupMessage<T>>.() -> Boolean

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.buildMediaGroupWaiter(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: MediaGroupFilter<T>? = null
) = flowsUpdatesFilter.expectFlow(bot, initRequest, count, errorFactory) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        if (mediaGroup.all { message -> message.content is T } && (filter == null || filter(mediaGroup as List<MediaGroupMessage<T>>))) {
            listOf(
                mediaGroup.map { it.content as T }
            )
        } else {
            null
        }
    } ?: emptyList()
}.take(count).toList()

suspend fun BehaviourContext.waitMediaGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<MediaGroupContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPlaylist(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<AudioMediaGroupContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<DocumentMediaGroupContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<VisualMediaGroupContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<PhotoContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<VideoContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitTextedMediaGroupMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: MediaGroupFilter<TextedMediaGroupMediaContent>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
