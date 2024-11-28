@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.baseSentMessageUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.commonMessageOrNull
import dev.inmo.tgbotapi.extensions.utils.withContentOrNull
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
internal suspend inline fun <BC : BehaviourContext, reified T : MediaGroupPartContent> BC.buildMediaGroupMessagesTrigger(
    initialFilter: SimpleFilter<MediaGroupMessage<T>>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<T>, Any>? = ByChatMessageMarkerFactory,
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<T>>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, additionalSubcontextInitialAction, scenarioReceiver) {
    it.baseSentMessageUpdateOrNull() ?.data ?.commonMessageOrNull() ?.withContentOrNull<MediaGroupContent<*>>() ?.let {
        if (it.content.group.all { it.content is T }) {
            listOf(it as MediaGroupMessage<T>)
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
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onMediaGroupMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<MediaGroupPartContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<MediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<MediaGroupPartContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onPlaylistMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<AudioMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<AudioMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<AudioMediaGroupPartContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<AudioMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<AudioMediaGroupPartContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onDocumentsGroupMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<DocumentMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<DocumentMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<DocumentMediaGroupPartContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<DocumentMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<DocumentMediaGroupPartContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVisualGalleryMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<VisualMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<VisualMediaGroupPartContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<VisualMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<VisualMediaGroupPartContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVisualMediaGroupMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<VisualMediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<VisualMediaGroupPartContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<VisualMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<VisualMediaGroupPartContent>>
) = onVisualGalleryMessages(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onPhotoGalleryMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<PhotoContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<PhotoContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<PhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<PhotoContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun <BC : BehaviourContext> BC.onVideoGalleryMessages(
    initialFilter: SimpleFilter<MediaGroupMessage<VideoContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaGroupMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in MediaGroupMessage<VideoContent>, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, MediaGroupMessage<VideoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaGroupMessage<VideoContent>>
) = buildMediaGroupMessagesTrigger(initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)
