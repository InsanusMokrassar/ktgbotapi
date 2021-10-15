package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ContextSafelyExceptionHandler
import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.micro_utils.fsm.common.*
import dev.inmo.micro_utils.fsm.common.managers.DefaultStatesManager
import dev.inmo.micro_utils.fsm.common.managers.InMemoryDefaultStatesManagerRepo
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

class BehaviourContextWithFSMBuilder internal constructor(
    private val resultBehaviourContext: BehaviourContextWithFSM,
    private val handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>>
) : BehaviourContextWithFSM by resultBehaviourContext {
    internal constructor(
        baseBehaviourContext: BehaviourContext,
        statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
        handlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf()
    ) : this(DefaultBehaviourContextWithFSM(baseBehaviourContext, statesManager, handlers), handlers)

    /**
     * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
     * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
     *
     * @see BehaviourWithFSMStateHandlerHolder
     * @see onStateOrSubstate
     */
    fun <I : State> add(kClass: KClass<I>, handler: BehaviourWithFSMStateHandler<I>) {
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
    fun <I : State> addStrict(kClass: KClass<I>, handler: BehaviourWithFSMStateHandler<I>) {
        handlers.add(BehaviourWithFSMStateHandlerHolder(kClass, true, handler))
    }

    /**
     * Returns completed [resultBehaviourContext], [handlers] and [statesManager]
     */
    internal fun build() = resultBehaviourContext
}

/**
 * Add NON STRICT [handler] to list of available in future [BehaviourContextWithFSM]. Non strict means that
 * for input [State] will be used [KClass.isInstance] and any inheritor of [kClass] will pass this requirement
 *
 * @see BehaviourWithFSMStateHandlerHolder
 * @see BehaviourContextWithFSMBuilder.add
 */
inline fun <reified I : State> BehaviourContextWithFSMBuilder.onStateOrSubstate(handler: BehaviourWithFSMStateHandler<I>) {
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
inline fun <reified I : State> BehaviourContextWithFSMBuilder.strictlyOn(handler: BehaviourWithFSMStateHandler<I>) {
    addStrict(I::class, handler)
}

/**
 * Use this factory to create and organize behaviour of your bot with attention to FSM logic. This factory WILL NOT
 * start any incoming updates handling of FSM handling, you must start it by yourself
 *
 * @param upstreamUpdatesFlow Will be used in [BehaviourContextWithFSMBuilder.build] to put it in new [BehaviourContextWithFSM]
 * @param scope This [CoroutineScope] will be used in [BehaviourContextWithFSMBuilder.build] to put it in new [BehaviourContextWithFSM]
 */
suspend fun TelegramBot.buildBehaviourWithFSM(
    upstreamUpdatesFlow: Flow<Update>? = null,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    handlersPreset: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder, Unit>
) = BehaviourContextWithFSMBuilder(
    DefaultBehaviourContext(this, scope, upstreamUpdatesFlow = upstreamUpdatesFlow),
    statesManager,
    handlersPreset
).apply { block() }.build()

/**
 * Use this factory to create and organize behaviour of your bot with attention to FSM logic. This factory will start
 * listening of updates by [longPolling]
 *
 * @param upstreamUpdatesFlow Will be used in [BehaviourContextWithFSMBuilder.build] to put it in new [BehaviourContextWithFSM]
 * @param scope This [CoroutineScope] will be used in [BehaviourContextWithFSMBuilder.build] to put it in new [BehaviourContextWithFSM]
 */
suspend fun TelegramBot.buildBehaviourWithFSMAndLongPolling(
    upstreamUpdatesFlow: Flow<Update>? = null,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder, Unit>
) = buildBehaviourWithFSM(upstreamUpdatesFlow, scope, statesManager, presetHandlers, block).run {
    this to scope.launch {
        start()
        longPolling(flowsUpdatesFilter, scope = scope)
    }
}

/**
 * Use this method in case you wish to make some additional actions with [flowUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] (or just [longPolling]) or tools for work with webhooks
 *
 * @see BehaviourContext
 * @see BehaviourContextWithFSM
 * @see longPolling
 * @see strictlyOn
 * @see onStateOrSubstate
 */
@PreviewFeature
suspend fun TelegramBot.buildBehaviourWithFSM(
    flowUpdatesFilter: FlowsUpdatesFilter,
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder, Unit>
): BehaviourContextWithFSM = BehaviourContextWithFSMBuilder(
    BehaviourContext(
        this,
        scope.let {
            if (defaultExceptionsHandler == null) {
                it
            } else {
                it + ContextSafelyExceptionHandler(defaultExceptionsHandler)
            }
        },
        flowUpdatesFilter
    ),
    statesManager,
    presetHandlers
).apply { block() }.build()

/**
 * Use this method to build bot behaviour with FSM and run it via long polling. In case you wish to use
 * [FlowsUpdatesFilter] of result [BehaviourContextWithFSM] for additional manipulations, you must provide external
 * [FlowsUpdatesFilter] in other [buildBehaviourWithFSM] function.
 *
 * @see buildBehaviourWithFSM
 * @see BehaviourContext
 * @see longPolling
 * @see strictlyOn
 * @see onStateOrSubstate
 */
@PreviewFeature
suspend fun TelegramBot.buildBehaviourWithFSM(
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    statesManager: StatesManager = DefaultStatesManager(InMemoryDefaultStatesManagerRepo()),
    presetHandlers: MutableList<BehaviourWithFSMStateHandlerHolder<*>> = mutableListOf(),
    block: CustomBehaviourContextReceiver<BehaviourContextWithFSMBuilder, Unit>
) = FlowsUpdatesFilter().let {
    buildBehaviourWithFSM(
        it,
        scope,
        defaultExceptionsHandler,
        statesManager,
        presetHandlers,
        block
    ).run {
        start()
        longPolling(
            flowsUpdatesFilter,
            scope = scope
        )
    }
}
