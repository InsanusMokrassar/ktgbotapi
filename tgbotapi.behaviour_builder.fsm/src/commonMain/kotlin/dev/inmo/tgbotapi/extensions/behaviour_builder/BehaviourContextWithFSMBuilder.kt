package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.DefaultTelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers.TelegramHandlersRegistrar
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

class BehaviourContextWithFSMBuilder<T : State> internal constructor(
    private val resultBehaviourContext: BehaviourContextWithFSM<T>,
    private val handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>>
) : BehaviourContextWithFSM<T> by resultBehaviourContext {
    internal constructor(
        baseBehaviourContext: BehaviourContext,
        statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
        handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf()
    ) : this(DefaultBehaviourContextWithFSM(baseBehaviourContext, statesManager, handlers), handlers)

    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see onStateOrSubstate
     */
    fun <I : T> add(kClass: KClass<I>, handler: BehaviourWithFSMStateHandler<I, T>) {
        handlers.add(BehaviourWithFSMStateHandlerHolder(kClass, false, handler))
    }

    /**
     * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
     * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
     * requirements
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see strictlyOn
     */
    fun <I : T> addStrict(kClass: KClass<I>, handler: BehaviourWithFSMStateHandler<I, T>) {
        handlers.add(BehaviourWithFSMStateHandlerHolder(kClass, true, handler))
    }


    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see BehaviourContextWithFSMBuilder.add
     */
    @Suppress("MemberVisibilityCanBePrivate")
    inline fun <reified I : T> onStateOrSubstate(handler: BehaviourWithFSMStateHandler<I, T>) {
        add(I::class, handler)
    }

    /**
     * Add STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Strict means that
     * for input [State] will be used [State]::class == [kClass] and any [State] with exactly the same type will pass
     * requirements
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see BehaviourContextWithFSMBuilder.addStrict
     */
    @Suppress("MemberVisibilityCanBePrivate")
    inline fun <reified I : T> strictlyOn(handler: BehaviourWithFSMStateHandler<I, T>) {
        addStrict(I::class, handler)
    }

    /**
     * Returns completed [resultBehaviourContext], [handlers] and [statesManager]
     */
    internal fun build() = resultBehaviourContext
}

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
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
): BehaviourContextWithFSM<T> = BehaviourContextWithFSMBuilder(
    DefaultBehaviourContext(
        this,
        defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = upstreamUpdatesFlow,
        telegramHandlersRegistrar = telegramHandlersRegistrar
    ),
    statesManager,
    presetHandlers
).apply { block() }.build()

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
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
): Pair<BehaviourContextWithFSM<T>, Job> = buildBehaviourWithFSM(
    upstreamUpdatesFlow,
    scope,
    defaultExceptionsHandler,
    statesManager,
    presetHandlers,
    telegramHandlersRegistrar,
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
 * @see BehaviourContextWithFSMBuilder.strictlyOn
 * @see BehaviourContextWithFSMBuilder.onStateOrSubstate
 */
@PreviewFeature
suspend fun <T : State> TelegramBot.buildBehaviourWithFSM(
    flowUpdatesFilter: FlowsUpdatesFilter,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
): BehaviourContextWithFSM<T> = BehaviourContextWithFSMBuilder(
    DefaultBehaviourContext(
        this,
        defaultExceptionsHandler ?.let { scope + ContextSafelyExceptionHandler(it) } ?: scope,
        upstreamUpdatesFlow = flowUpdatesFilter.allUpdatesFlow,
        telegramHandlersRegistrar = telegramHandlersRegistrar
    ),
    statesManager,
    presetHandlers
).apply { block() }.build()

/**
 * Use [buildBehaviourWithFSM] to create [BehaviourContextWithFSM] and launch getting of updates
 * using [longPolling]. For [longPolling] will be used result [BehaviourContextWithFSM] for both parameters
 * flowsUpdatesFilter and scope
 *
 * @see buildBehaviourWithFSMAndStartLongPolling
 * @see BehaviourContext
 * @see longPolling
 * @see BehaviourContextWithFSMBuilder.strictlyOn
 * @see BehaviourContextWithFSMBuilder.onStateOrSubstate
 */
@PreviewFeature
suspend fun <T : State> TelegramBot.buildBehaviourWithFSMAndStartLongPolling(
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager<T> = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*, T>> = mutableListOf(),
    telegramHandlersRegistrar: TelegramHandlersRegistrar = DefaultTelegramHandlersRegistrar(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder<T>, Unit>
) = FlowsUpdatesFilter().let {
    buildBehaviourWithFSM(
        it,
        scope,
        defaultExceptionsHandler,
        statesManager,
        presetHandlers,
        telegramHandlersRegistrar,
        block
    ).run {
        start()
        longPolling(
            flowsUpdatesFilter,
            scope = scope
        )
    }
}
