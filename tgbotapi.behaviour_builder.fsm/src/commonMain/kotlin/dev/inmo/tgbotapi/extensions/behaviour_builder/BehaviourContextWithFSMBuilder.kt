package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler
import dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow

@Deprecated("Will be removed soon")
typealias BehaviourContextWithFSMBuilder<T> = BehaviourContextWithFSM<T>

/**
 * Creates [BehaviourContextWithFSM] via creating of [DefaultBehaviourContext] with [this] as [TelegramBot],
 * [scope] as target scope for that [DefaultBehaviourContext] and [upstreamUpdatesFlow]. Pass [statesManager]
 * to customize some internal logic of states changes. Pass [presetHandlers] in case you have some list of
 * [BehaviourWithFSMStateHandlerHolder] with presets.
 *
 * !!! WARNING !!! This method WILL NOT call [BehaviourContextWithFSM.start] of result object and WILL NOT
 * start any updates retrieving. See [buildBehaviourWithFSMAndStartLongPolling] or
 * [telegramBotWithBehaviourAndFSMAndStartLongPolling] in case you wish to start [longPolling] automatically
 */
suspend fun <T : State> TelegramBot.buildBehaviourWithFSM(
    upstreamUpdatesFlow: Flow<Update>? = null,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: List<BehaviourWithFSMStateHandlerHolder<*, T>> = listOf(),
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
    DefaultBehaviourContext(
        this,
        defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = upstreamUpdatesFlow
    ),
    presetHandlers,
    statesManager,
    onStateHandlingErrorHandler
).apply { block() }

/**
 * Use [buildBehaviourWithFSM] to create [BehaviourContextWithFSM] and launch getting of updates
 * using [longPolling]. For [longPolling] will be used result [BehaviourContextWithFSM] for both parameters
 * flowsUpdatesFilter and scope
 */
suspend fun <T : State> TelegramBot.buildBehaviourWithFSMAndStartLongPolling(
    upstreamUpdatesFlow: Flow<Update>? = null,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: List<BehaviourWithFSMStateHandlerHolder<*, T>> = listOf(),
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): Pair<DefaultBehaviourContextWithFSM<T>, Job> = buildBehaviourWithFSM(
    upstreamUpdatesFlow,
    scope,
    defaultExceptionsHandler,
    statesManager,
    presetHandlers,
    onStateHandlingErrorHandler,
    block
).run {
    this to scope.launch {
        start()
        longPolling(flowsUpdatesFilter, scope = scope)
    }
}

/**
 * Creates [BehaviourContextWithFSM] via creating of [DefaultBehaviourContext] with [this] as [TelegramBot],
 * [scope] as target scope for that [DefaultBehaviourContext] and [FlowsUpdatesFilter.allUpdatesFlow] of
 * [flowUpdatesFilter] as [DefaultBehaviourContext.upstreamUpdatesFlow]. Pass [statesManager]
 * to customize some internal logic of states changes. Pass [presetHandlers] in case you have some list of
 * [BehaviourWithFSMStateHandlerHolder] with presets.
 * Use this method in case you wish to make some additional actions with [flowUpdatesFilter].
 *
 * !!! WARNING !!! This method WILL NOT call [BehaviourContextWithFSM.start] of result object and WILL NOT
 * start any updates retrieving. See [buildBehaviourWithFSMAndStartLongPolling] or
 * [telegramBotWithBehaviourAndFSMAndStartLongPolling] in case you wish to start [longPolling] automatically
 *
 * @see BehaviourContext
 * @see BehaviourContextWithFSM
 * @see longPolling
 * @see BehaviourContextWithFSM.strictlyOn
 * @see BehaviourContextWithFSM.onStateOrSubstate
 */
suspend fun <T : State> TelegramBot.buildBehaviourWithFSM(
    flowUpdatesFilter: FlowsUpdatesFilter,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: List<BehaviourWithFSMStateHandlerHolder<*, T>> = listOf(),
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
    DefaultBehaviourContext(
        this,
        defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = flowUpdatesFilter.allUpdatesFlow
    ),
    presetHandlers,
    statesManager,
    onStateHandlingErrorHandler
).apply { block() }

/**
 * Use [buildBehaviourWithFSM] to create [BehaviourContextWithFSM] and launch getting of updates
 * using [longPolling]. For [longPolling] will be used result [BehaviourContextWithFSM] for both parameters
 * flowsUpdatesFilter and scope
 *
 * @see buildBehaviourWithFSMAndStartLongPolling
 * @see BehaviourContext
 * @see longPolling
 * @see BehaviourContextWithFSM.strictlyOn
 * @see BehaviourContextWithFSM.onStateOrSubstate
 */
suspend fun <T : State> TelegramBot.buildBehaviourWithFSMAndStartLongPolling(
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: List<BehaviourWithFSMStateHandlerHolder<*, T>> = listOf(),
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
) = FlowsUpdatesFilter().let {
    buildBehaviourWithFSM(
        it,
        scope,
        defaultExceptionsHandler,
        statesManager,
        presetHandlers,
        onStateHandlingErrorHandler,
        block
    ).run {
        start()
        longPolling(
            flowsUpdatesFilter,
            scope = scope
        )
    }
}
