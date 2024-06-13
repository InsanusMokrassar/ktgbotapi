@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.AnyMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.baseSentMessageUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.commonMessageOrNull
import dev.inmo.tgbotapi.extensions.utils.withContentOrNull
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
internal suspend inline fun <BC : BehaviourContext, reified T : MediaGroupPartContent> BC.buildMediaGroupTrigger(
    initialFilter: SimpleFilter<MediaGroupContent<T>>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<T>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<T>, Any>? = AnyMarkerFactory(),
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    it.baseSentMessageUpdateOrNull() ?.data ?.commonMessageOrNull() ?.withContentOrNull<MediaGroupContent<*>>() ?.let {
        if (it.content.group.all { it.content is T }) {
            listOf(it.content as MediaGroupContent<T>)
        } else {
            null
        }
    } ?: emptyList()
}

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onMediaGroup(
    initialFilter: SimpleFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<MediaGroupPartContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onPlaylist(
    initialFilter: SimpleFilter<MediaGroupContent<AudioMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<AudioMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<AudioMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<AudioMediaGroupPartContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onDocumentsGroup(
    initialFilter: SimpleFilter<MediaGroupContent<DocumentMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<DocumentMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<DocumentMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<DocumentMediaGroupPartContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVisualGallery(
    initialFilter: SimpleFilter<MediaGroupContent<VisualMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<VisualMediaGroupPartContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVisualMediaGroup(
    initialFilter: SimpleFilter<MediaGroupContent<VisualMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<VisualMediaGroupPartContent>>
) = onVisualGallery(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onPhotoGallery(
    initialFilter: SimpleFilter<MediaGroupContent<PhotoContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<PhotoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<PhotoContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVideoGallery(
    initialFilter: SimpleFilter<MediaGroupContent<VideoContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupContent<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupContent<VideoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupContent<VideoContent>>
) = buildMediaGroupTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)
