package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.micro_utils.fsm.common.utils.StateHandlingErrorHandler
import dev.inmo.micro_utils.fsm.common.utils.defaultStateHandlingErrorHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

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
    fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
    useDefaultSubcontextInitialAction: Boolean = true,
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
    behaviourContext = BehaviourContext(
        bot = this,
        scope = defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = upstreamUpdatesFlow ?: emptyFlow(),
        useDefaultSubcontextInitialAction = useDefaultSubcontextInitialAction,
        subcontextInitialAction = subcontextInitialAction
    ),
    handlers = presetHandlers,
    statesManager = statesManager,
    fallbackHandler = fallbackHandler,
    stateInitialAction = stateInitialAction,
    onStateHandlingErrorHandler = onStateHandlingErrorHandler
).apply { block() }

/**
 * Use [buildBehaviourWithFSM] to create [BehaviourContextWithFSM] and launch getting of updates
 * using [longPolling]. For [longPolling] will be used result [BehaviourContextWithFSM] for both parameters
 * flowsUpdatesFilter and scope
 *
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 */
suspend fun <T : State> TelegramBot.buildBehaviourWithFSMAndStartLongPolling(
    upstreamUpdatesFlow: Flow<Update>? = null,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: List<BehaviourWithFSMStateHandlerHolder<*, T>> = listOf(),
    fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    timeoutSeconds: Seconds = 30,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
    useDefaultSubcontextInitialAction: Boolean = true,
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): Pair<DefaultBehaviourContextWithFSM<T>, Job> = buildBehaviourWithFSM(
    upstreamUpdatesFlow = upstreamUpdatesFlow,
    scope = scope,
    defaultExceptionsHandler = defaultExceptionsHandler,
    statesManager = statesManager,
    presetHandlers = presetHandlers,
    fallbackHandler = fallbackHandler,
    onStateHandlingErrorHandler = onStateHandlingErrorHandler,
    subcontextInitialAction = subcontextInitialAction,
    stateInitialAction = stateInitialAction,
    useDefaultSubcontextInitialAction = useDefaultSubcontextInitialAction,
    block = block
).run {
    this to scope.launch {
        start()
        longPolling(flowsUpdatesFilter, timeoutSeconds, scope, autoDisableWebhooks, autoSkipTimeoutExceptions, mediaGroupsDebounceTimeMillis, defaultExceptionsHandler)
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
    fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
    useDefaultSubcontextInitialAction: Boolean = true,
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
): DefaultBehaviourContextWithFSM<T> = BehaviourContextWithFSM(
    BehaviourContext(
        this,
        defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = flowUpdatesFilter.allUpdatesFlow,
        subcontextInitialAction = subcontextInitialAction,
        useDefaultSubcontextInitialAction = useDefaultSubcontextInitialAction
    ),
    presetHandlers,
    statesManager,
    fallbackHandler,
    stateInitialAction,
    onStateHandlingErrorHandler
).apply { block() }

/**
 * Use [buildBehaviourWithFSM] to create [BehaviourContextWithFSM] and launch getting of updates
 * using [longPolling]. For [longPolling] will be used result [BehaviourContextWithFSM] for both parameters
 * flowsUpdatesFilter and scope
 *
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
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
    fallbackHandler: BehaviourWithFSMStateHandlerHolder<T, T>? = null,
    onStateHandlingErrorHandler: StateHandlingErrorHandler<T> = defaultStateHandlingErrorHandler(),
    timeoutSeconds: Seconds = 30,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    stateInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContextWithFSM<T>, Unit, T> = {},
    useDefaultSubcontextInitialAction: Boolean = true,
    block: CustomBehaviourContextReceiver<DefaultBehaviourContextWithFSM<T>, Unit>
) = FlowsUpdatesFilter().let {
    buildBehaviourWithFSM(
        flowUpdatesFilter = it,
        scope = scope,
        defaultExceptionsHandler = defaultExceptionsHandler,
        statesManager = statesManager,
        presetHandlers = presetHandlers,
        fallbackHandler = fallbackHandler,
        onStateHandlingErrorHandler = onStateHandlingErrorHandler,
        subcontextInitialAction = subcontextInitialAction,
        stateInitialAction = stateInitialAction,
        useDefaultSubcontextInitialAction = useDefaultSubcontextInitialAction,
        block = block
    ).run {
        start()
        longPolling(
            updatesFilter = flowsUpdatesFilter,
            timeoutSeconds = timeoutSeconds,
            scope = scope,
            autoDisableWebhooks = autoDisableWebhooks,
            autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
            mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis,
            exceptionsHandler = defaultExceptionsHandler
        )
    }
}
