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
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList

typealias MediaGroupFilter<T> = SimpleFilter<List<MediaGroupMessage<T>>>

internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.buildMediaGroupWaiter(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<List<MediaGroupMessage<T>>>? = null
) = flowsUpdatesFilter.expectFlow(bot, initRequest, count, errorFactory) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        val mapped = mediaGroup.mapNotNull { it.withContent<T>() }
        if (mediaGroup.all { message -> message.content is T } && (filter == null || filter(mapped))) {
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
    filter: SimpleFilter<List<MediaGroupMessage<MediaGroupContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPlaylist(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<List<MediaGroupMessage<AudioMediaGroupContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentsGroup(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<List<MediaGroupMessage<DocumentMediaGroupContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVisualGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<List<MediaGroupMessage<VisualMediaGroupContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitPhotoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<List<MediaGroupMessage<PhotoContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
suspend fun BehaviourContext.waitVideoGallery(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<List<MediaGroupMessage<VideoContent>>>? = null
) = buildMediaGroupWaiter(count, initRequest, errorFactory, filter)
