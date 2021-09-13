@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CallbackQueryFilterByUser
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByUserCallbackQueryMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T : CallbackQuery> BehaviourContext.onCallbackQuery(
    noinline initialFilter: SimpleFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, T, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in T, Any> = ByUserCallbackQueryMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asCallbackQueryUpdate() ?.data as? T) ?.let(::listOfNotNull)
}


suspend fun BehaviourContext.onDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<DataCallbackQuery>? = null,
    markerFactory: MarkerFactory<in DataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, DataCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<GameShortNameCallbackQuery>? = null,
    markerFactory: MarkerFactory<in GameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, GameShortNameCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<InlineMessageIdCallbackQuery>? = null,
    markerFactory: MarkerFactory<in InlineMessageIdCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<InlineMessageIdDataCallbackQuery>? = null,
    markerFactory: MarkerFactory<in InlineMessageIdDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<InlineMessageIdGameShortNameCallbackQuery>? = null,
    markerFactory: MarkerFactory<in InlineMessageIdGameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdGameShortNameCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<MessageCallbackQuery>? = null,
    markerFactory: MarkerFactory<in MessageCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<MessageDataCallbackQuery>? = null,
    markerFactory: MarkerFactory<in MessageDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageDataCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<MessageGameShortNameCallbackQuery>? = null,
    markerFactory: MarkerFactory<in MessageGameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageGameShortNameCallbackQuery>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onUnknownCallbackQueryType(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<UnknownCallbackQueryType>? = null,
    markerFactory: MarkerFactory<in UnknownCallbackQueryType, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, UnknownCallbackQueryType>
) = onCallbackQuery(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) CallbackQueryFilterByUser else null,
    markerFactory,
    scenarioReceiver
)


suspend fun BehaviourContext.onDataCallbackQuery(
    initialFilter: SimpleFilter<DataCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, DataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in DataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, DataCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onGameShortNameCallbackQuery(
    initialFilter: SimpleFilter<GameShortNameCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, GameShortNameCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in GameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, GameShortNameCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdCallbackQuery(
    initialFilter: SimpleFilter<InlineMessageIdCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, InlineMessageIdCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineMessageIdCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdDataCallbackQuery(
    initialFilter: SimpleFilter<InlineMessageIdDataCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, InlineMessageIdDataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineMessageIdDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInlineMessageIdGameShortNameCallbackQuery(
    initialFilter: SimpleFilter<InlineMessageIdGameShortNameCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, InlineMessageIdGameShortNameCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in InlineMessageIdGameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdGameShortNameCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageCallbackQuery(
    initialFilter: SimpleFilter<MessageCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, MessageCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in MessageCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageDataCallbackQuery(
    initialFilter: SimpleFilter<MessageDataCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, MessageDataCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in MessageDataCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageDataCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMessageGameShortNameCallbackQuery(
    initialFilter: SimpleFilter<MessageGameShortNameCallbackQuery>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, MessageGameShortNameCallbackQuery, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in MessageGameShortNameCallbackQuery, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageGameShortNameCallbackQuery>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onUnknownCallbackQueryType(
    initialFilter: SimpleFilter<UnknownCallbackQueryType>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, UnknownCallbackQueryType, Update>? = CallbackQueryFilterByUser,
    markerFactory: MarkerFactory<in UnknownCallbackQueryType, Any> = ByUserCallbackQueryMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, UnknownCallbackQueryType>
) = onCallbackQuery(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
