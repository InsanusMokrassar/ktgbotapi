@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessagesFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMediaGroupMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asSentMediaGroupUpdate
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.buildMediaGroupTrigger(
    noinline initialFilter: SimpleFilter<List<MediaGroupMessage<T>>>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<T>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<T>>, Any> = ByChatMediaGroupMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<T>>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asSentMediaGroupUpdate() ?.data ?.takeIf { it.all { it is T } } as? List<MediaGroupMessage<T>>) ?.let(::listOfNotNull)
}

suspend fun BehaviourContext.onMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<MediaGroupContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<MediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<MediaGroupContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onPlaylist(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<AudioMediaGroupContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<AudioMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<AudioMediaGroupContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onDocumentsGroup(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<DocumentMediaGroupContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<DocumentMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<DocumentMediaGroupContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVisualGallery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<VisualMediaGroupContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VisualMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVisualMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<VisualMediaGroupContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VisualMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = onVisualGallery(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onPhotoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<PhotoContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<PhotoContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<PhotoContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVideoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<List<MediaGroupMessage<VideoContent>>>? = null,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VideoContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VideoContent>>>
) = buildMediaGroupTrigger(additionalFilter, if (includeFilterByChatInBehaviourSubContext) MessagesFilterByChat else null, markerFactory, scenarioReceiver)



/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onMediaGroup(
    initialFilter: SimpleFilter<List<MediaGroupMessage<MediaGroupContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<MediaGroupContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<MediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<MediaGroupContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPlaylist(
    initialFilter: SimpleFilter<List<MediaGroupMessage<AudioMediaGroupContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<AudioMediaGroupContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<AudioMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<AudioMediaGroupContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onDocumentsGroup(
    initialFilter: SimpleFilter<List<MediaGroupMessage<DocumentMediaGroupContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<DocumentMediaGroupContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<DocumentMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<DocumentMediaGroupContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVisualGallery(
    initialFilter: SimpleFilter<List<MediaGroupMessage<VisualMediaGroupContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<VisualMediaGroupContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VisualMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVisualMediaGroup(
    initialFilter: SimpleFilter<List<MediaGroupMessage<VisualMediaGroupContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<VisualMediaGroupContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VisualMediaGroupContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = onVisualGallery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPhotoGallery(
    initialFilter: SimpleFilter<List<MediaGroupMessage<PhotoContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<PhotoContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<PhotoContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<PhotoContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVideoGallery(
    initialFilter: SimpleFilter<List<MediaGroupMessage<VideoContent>>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, List<MediaGroupMessage<VideoContent>>, Update>? = MessagesFilterByChat,
    markerFactory: MarkerFactory<in List<MediaGroupMessage<VideoContent>>, Any> = ByChatMediaGroupMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VideoContent>>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
