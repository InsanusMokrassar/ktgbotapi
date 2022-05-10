@file:Suppress("NOTHING_TO_INLINE")

package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.handlers_registrar.TriggersHolder
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*

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

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        triggersHolder: TriggersHolder = TriggersHolder(),
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>? = null
    ): BehaviourContext
}

class DefaultBehaviourContext(
    override val bot: TelegramBot,
    override val scope: CoroutineScope,
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    private val upstreamUpdatesFlow: Flow<Update>? = null,
    override val triggersHolder: TriggersHolder = TriggersHolder(),
    private val updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>? = null
) : AbstractFlowsUpdatesFilter(), TelegramBot by bot, CoroutineScope by scope, BehaviourContext {

    private val additionalUpdatesSharedFlow = MutableSharedFlow<Update>(0, broadcastChannelsSize, onBufferOverflow)
    override val allUpdatesFlow: Flow<Update> = (additionalUpdatesSharedFlow.asSharedFlow()).let {
        if (upstreamUpdatesFlow != null) {
            (it + upstreamUpdatesFlow).distinctUntilChanged { old, new -> old.updateId == new.updateId }
        } else {
            it
        }
    }.let {
        val updatesFilter = updatesFilter
        if (updatesFilter != null) {
            it.filter { updatesFilter(it) }
        } else {
            it
        }
    }.accumulatorFlow(scope)
    override val asUpdateReceiver: UpdateReceiver<Update> = additionalUpdatesSharedFlow::emit

    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        broadcastChannelsSize: Int,
        onBufferOverflow: BufferOverflow,
        upstreamUpdatesFlow: Flow<Update>?,
        triggersHolder: TriggersHolder,
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?
    ): DefaultBehaviourContext = DefaultBehaviourContext(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, triggersHolder, updatesFilter)
}

fun BehaviourContext(
    bot: TelegramBot,
    scope: CoroutineScope,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    triggersHolder: TriggersHolder = TriggersHolder(),
) = DefaultBehaviourContext(bot, scope, upstreamUpdatesFlow = flowsUpdatesFilter.allUpdatesFlow, triggersHolder = triggersHolder)

inline fun <T> BehaviourContext(
    bot: TelegramBot,
    scope: CoroutineScope,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    triggersHolder: TriggersHolder = TriggersHolder(),
    crossinline block: BehaviourContext.() -> T
) = DefaultBehaviourContext(bot, scope, upstreamUpdatesFlow = flowsUpdatesFilter.allUpdatesFlow, triggersHolder = triggersHolder).run(block)

suspend fun <BC : BehaviourContext> BC.createSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow.accumulatorFlow(scope),
    updatesFilter: CustomBehaviourContextAndTypeReceiver<BC, Boolean, Update>? = null,
) = copy(
    scope = scope,
    updatesFilter = updatesFilter ?.let { _ ->
        {
            (this as? BC) ?.run {
                updatesFilter(it)
            } ?: true
        }
    },
    upstreamUpdatesFlow = updatesUpstreamFlow,
    triggersHolder = triggersHolder
) as BC

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [updatesFilter] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]
 */
suspend fun <T, BC : BehaviourContext> BC.doInContext(
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return behaviourContextReceiver().also { if (stopOnCompletion) stop() }
}

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [updatesFilter] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]
 */
suspend fun <T, BC : BehaviourContext> BC.doInSubContextWithUpdatesFilter(
    updatesFilter: CustomBehaviourContextAndTypeReceiver<BC, Boolean, Update>?,
    stopOnCompletion: Boolean = true,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return createSubContext(
        scope,
        triggersHolder,
        updatesUpstreamFlow,
        updatesFilter
    ).doInContext(
        stopOnCompletion,
        behaviourContextReceiver
    )
}

suspend fun <T> BehaviourContext.doInSubContext(
    stopOnCompletion: Boolean = true,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    behaviourContextReceiver: BehaviourContextReceiver<T>
) = doInSubContextWithUpdatesFilter(updatesFilter = null, stopOnCompletion, updatesUpstreamFlow, scope, triggersHolder, behaviourContextReceiver)

/**
 * This method will cancel ALL subsequent contexts, expectations and waiters
 */
fun BehaviourContext.stop() = scope.cancel()
