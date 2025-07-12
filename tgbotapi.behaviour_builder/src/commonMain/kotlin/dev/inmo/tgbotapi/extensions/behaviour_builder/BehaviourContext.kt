@file:Suppress("NOTHING_TO_INLINE")

package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.kslog.common.KSLog
import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar.TriggersHolder
import dev.inmo.tgbotapi.types.UpdateId
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.*
import dev.inmo.tgbotapi.utils.launchWithBotLogger
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

typealias CustomBehaviourContextReceiver<BC, T> = suspend BC.() -> T
typealias BehaviourContextReceiver<T> = CustomBehaviourContextReceiver<BehaviourContext, T>
typealias CustomBehaviourContextAndTypeReceiver<BC, T, I> = suspend BC.(I) -> T
typealias BehaviourContextAndTypeReceiver<T, I> = CustomBehaviourContextAndTypeReceiver<BehaviourContext, T, I>
typealias CustomBehaviourContextAndTwoTypesReceiver<BC, T, I1, I2> = suspend BC.(I1, I2) -> T
typealias BehaviourContextAndTwoTypesReceiver<T, I1, I2> = CustomBehaviourContextAndTwoTypesReceiver<BehaviourContext, T, I1, I2>
inline fun <T> BehaviourContextReceiver(noinline block: BehaviourContextReceiver<T>) = block
inline fun <BC, T> CustomBehaviourContextReceiver(noinline block: CustomBehaviourContextReceiver<BC, T>) = block
inline fun <T, I> BehaviourContextAndTypeReceiver(noinline block: BehaviourContextAndTypeReceiver<T, I>) = block
inline fun <T, I1, I2> BehaviourContextAndTwoTypesReceiver(noinline block: BehaviourContextAndTwoTypesReceiver<T, I1, I2>) = block
internal inline fun <BC, T, I1, I2> CustomBehaviourContextAndTwoTypesReceiver<BC, T, I1, I2>.toOneType(
    i1: I1,
): CustomBehaviourContextAndTypeReceiver<BC, T, I2> = { invoke(this, i1, it) }

/**
 * This class contains all necessary tools for work with bots and especially [buildBehaviour]
 *
 * @see DefaultBehaviourContext
 */
interface BehaviourContext : FlowsUpdatesFilter, TelegramBot, CoroutineScope {
    val bot: TelegramBot
        get() = this

    /**
     * Will be used for creating of some subscriptions inside of methods, updates listening and different other things
     * in context of working with [CoroutineScope] and coroutines.
     */
    val scope: CoroutineScope
        get() = this

    /**
     * This parameter will be used to subscribe on different types of update
     */
    val flowsUpdatesFilter: FlowsUpdatesFilter
        get() = this

    val triggersHolder: TriggersHolder

    /**
     * Data filled by [subcontextInitialAction] or in any other way
     */
    val data: BehaviourContextData

    /**
     * Will be called on any initial subcontext action before some consumer will be called
     */
    val subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        triggersHolder: TriggersHolder = TriggersHolder(),
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = this.subcontextInitialAction
    ): BehaviourContext
}

class DefaultBehaviourContext(
    override val bot: TelegramBot,
    override val scope: CoroutineScope,
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    private val upstreamUpdatesFlow: Flow<Update>? = null,
    override val triggersHolder: TriggersHolder = TriggersHolder(),
    override val subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {}
) : AbstractFlowsUpdatesFilter(), TelegramBot by bot, CoroutineScope by scope, BehaviourContext {

    private val additionalUpdatesSharedFlow = MutableSharedFlow<Update>(0, broadcastChannelsSize, onBufferOverflow)
    override val allUpdatesFlow: Flow<Update> = (additionalUpdatesSharedFlow.asSharedFlow()).let {
        if (upstreamUpdatesFlow != null) {
            val handledUpdates = mutableSetOf<UpdateId>()
            (it + upstreamUpdatesFlow).filter {
                val passed = handledUpdates.add(it.updateId)
                (passed).also { passed ->
                    val needToDropCount = handledUpdates.size - broadcastChannelsSize
                    if (needToDropCount > 0) {
                        handledUpdates.removeAll(
                            handledUpdates.take(needToDropCount).ifEmpty { return@also }
                        )
                    }
                }
            }
        } else {
            it
        }
    }.accumulatorFlow(WeakScope(scope))
    override val asUpdateReceiver: UpdateReceiver<Update> = additionalUpdatesSharedFlow::emit

    override val data: BehaviourContextData = BehaviourContextData()

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder,
        subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>
    ): DefaultBehaviourContext = DefaultBehaviourContext(
        bot = bot,
        scope = scope,
        broadcastChannelsSize = broadcastChannelsSize,
        onBufferOverflow = onBufferOverflow,
        upstreamUpdatesFlow = upstreamUpdatesFlow,
        triggersHolder = triggersHolder,
        subcontextInitialAction = subcontextInitialAction
    ).apply {
        data.include(this@DefaultBehaviourContext.data)
    }
}

fun BehaviourContext(
    bot: TelegramBot,
    scope: CoroutineScope,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    triggersHolder: TriggersHolder = TriggersHolder(),
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {}
) = DefaultBehaviourContext(bot, scope, upstreamUpdatesFlow = flowsUpdatesFilter.allUpdatesFlow, triggersHolder = triggersHolder, subcontextInitialAction = subcontextInitialAction)

inline fun <T> BehaviourContext(
    bot: TelegramBot,
    scope: CoroutineScope,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    triggersHolder: TriggersHolder = TriggersHolder(),
    noinline subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update> = {},
    crossinline block: BehaviourContext.() -> T
) = DefaultBehaviourContext(bot, scope, upstreamUpdatesFlow = flowsUpdatesFilter.allUpdatesFlow, triggersHolder = triggersHolder, subcontextInitialAction = subcontextInitialAction).run(block)

/**
 * Creates new [BehaviourContext] using its [BehaviourContext.copy] method
 *
 * @param updatesFilter This param will not be used anymore
 */
@Suppress("UNCHECKED_CAST")
fun <BC : BehaviourContext> BC.createSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BC, Unit, Update> = this.subcontextInitialAction,
) = copy(
    scope = scope,
    upstreamUpdatesFlow = updatesUpstreamFlow,
    triggersHolder = triggersHolder,
    subcontextInitialAction = {
        (this as BC).subcontextInitialAction(it)
    }
) as BC

/**
 * Launch [behaviourContextReceiver] in context of [this] as [BehaviourContext] and as [kotlin.coroutines.CoroutineContext]
 *
 * [this] [BehaviourContext] will **NOT** be closed automatically
 */
suspend fun <T, BC : BehaviourContext> BC.doInContext(
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return withContext(coroutineContext) {
        behaviourContextReceiver()
    }
}

/**
 * Launch [behaviourContextReceiver] in context of [this] as [BehaviourContext] and as [kotlin.coroutines.CoroutineContext]
 *
 * [this] [BehaviourContext] will **NOT** be closed automatically
 */
suspend fun <T, BC : BehaviourContext> BC.doInNewSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BC, Unit, Update> = this.subcontextInitialAction,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return createSubContext(
        scope = scope,
        triggersHolder = triggersHolder,
        updatesUpstreamFlow = updatesUpstreamFlow,
        subcontextInitialAction = subcontextInitialAction
    ).run {
        behaviourContextReceiver()
    }
}

/**
 * Launch [behaviourContextReceiver] in context of [this] as [BehaviourContext] and as [kotlin.coroutines.CoroutineContext]
 *
 * [this] [BehaviourContext] will **NOT** be closed automatically
 */
fun <T, BC : BehaviourContext> BC.launchInNewSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    subcontextInitialAction: CustomBehaviourContextAndTypeReceiver<BC, Unit, Update> = this.subcontextInitialAction,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): Job {
    return createSubContext(
        scope = scope,
        triggersHolder = triggersHolder,
        updatesUpstreamFlow = updatesUpstreamFlow,
        subcontextInitialAction = subcontextInitialAction
    ).apply {
        this@apply.launchWithBotLogger {
            behaviourContextReceiver()
        }
    }.coroutineContext.job
}

/**
 * Creates new one [BehaviourContext] using [createSubContext] and launches [behaviourContextReceiver] in a new context
 * using [doInContext].
 *
 * This action will be executed in **synchronous** manner which means that until the context created with
 * [createSubContext] will be done this function will not let execution of code continue
 */
suspend fun <T, BC : BehaviourContext> BC.createSubContextAndDoSynchronouslyWithUpdatesFilter(
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    additionalSubcontextInitialAction: (CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>)? = null,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return supervisorScope {
        createSubContext(
            scope = this@supervisorScope,
            triggersHolder = triggersHolder,
            updatesUpstreamFlow = updatesUpstreamFlow,
            subcontextInitialAction = if (additionalSubcontextInitialAction == null) {
                subcontextInitialAction
            } else {
                { subcontextInitialAction(it); additionalSubcontextInitialAction(it) }
            }
        ).behaviourContextReceiver()
    }
}

/**
 * Uses [createSubContextAndDoSynchronouslyWithUpdatesFilter], but wrapping it in [async]. That means, that
 * execution of this function will be **asynchronous** and **will not** block execution of code by default
 */
suspend fun <T, BC : BehaviourContext> BC.createSubContextAndDoAsynchronouslyWithUpdatesFilter(
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    additionalSubcontextInitialAction: (CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>)? = null,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): Deferred<T> = async(
    context,
    start
) {
    createSubContextAndDoSynchronouslyWithUpdatesFilter(
        triggersHolder = triggersHolder,
        updatesUpstreamFlow = updatesUpstreamFlow,
        additionalSubcontextInitialAction = additionalSubcontextInitialAction,
        behaviourContextReceiver = behaviourContextReceiver
    )
}

/**
 * It is just backward compatibility function which will be removed in next updates.
 *
 * Uses [createSubContextAndDoAsynchronouslyWithUpdatesFilter] under the hood with passing of parameters as is
 */
@Deprecated(
    "Renamed",
    ReplaceWith(
        "createSubContextAndDoAsynchronouslyWithUpdatesFilter(triggersHolder, updatesUpstreamFlow, behaviourContextReceiver = behaviourContextReceiver)",
        "dev.inmo.tgbotapi.extensions.behaviour_builder.createSubContextAndDoAsynchronouslyWithUpdatesFilter"
    )
)
suspend fun <T, BC : BehaviourContext> BC.createSubContextAndDoWithUpdatesFilter(
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    additionalSubcontextInitialAction: (CustomBehaviourContextAndTypeReceiver<BehaviourContext, Unit, Update>)? = null,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): Deferred<T> = createSubContextAndDoAsynchronouslyWithUpdatesFilter(
    triggersHolder = triggersHolder,
    updatesUpstreamFlow = updatesUpstreamFlow,
    additionalSubcontextInitialAction = additionalSubcontextInitialAction,
    behaviourContextReceiver = behaviourContextReceiver
)

/**
 * This method will cancel ALL subsequent contexts, expectations and waiters
 */
fun BehaviourContext.stop() = scope.cancel()
