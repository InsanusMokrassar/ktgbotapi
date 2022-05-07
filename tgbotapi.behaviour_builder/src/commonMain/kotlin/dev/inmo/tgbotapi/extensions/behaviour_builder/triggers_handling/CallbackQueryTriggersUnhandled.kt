@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CallbackQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserCallbackQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

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
suspend fun <BC : BehaviourContext> BC.onUnhandledDataCallbackQuery(
    initialFilter: SimpleFilter<DataCallbackQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, DataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in DataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, DataCallbackQuery>
) = onCallbackQuery (
    initialFilter * !SimpleFilter<MessageDataCallbackQuery> { triggersHolder.handleableCallbackQueriesDataHolder.isHandled(it) },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onUnhandledInlineMessageIdDataCallbackQuery(
    initialFilter: SimpleFilter<InlineMessageIdDataCallbackQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, InlineMessageIdDataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineMessageIdDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery (
    initialFilter * !SimpleFilter<MessageDataCallbackQuery> { triggersHolder.handleableCallbackQueriesDataHolder.isHandled(it) },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onUnhandledMessageDataCallbackQuery(
    initialFilter: SimpleFilter<MessageDataCallbackQuery>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MessageDataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in MessageDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MessageDataCallbackQuery>
) = onCallbackQuery(
    initialFilter * !SimpleFilter<MessageDataCallbackQuery> { triggersHolder.handleableCallbackQueriesDataHolder.isHandled(it) },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
