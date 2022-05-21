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
        triggersHolder: TriggersHolder = TriggersHolder()
    ): BehaviourContext

    /**
     * @param updatesFilter unused
     */
    @Deprecated("Do not use this method")
    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        broadcastChannelsSize: Int = 100,
        onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
        upstreamUpdatesFlow: Flow<Update>? = null,
        triggersHolder: TriggersHolder = TriggersHolder(),
        updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>? = null
    ): BehaviourContext = copy(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, triggersHolder)
}

class DefaultBehaviourContext(
    override val bot: TelegramBot,
    override val scope: CoroutineScope,
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    private val upstreamUpdatesFlow: Flow<Update>? = null,
    override val triggersHolder: TriggersHolder = TriggersHolder(),
    @Deprecated("This parameter is not used anymore")
    private val updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>? = null
) : AbstractFlowsUpdatesFilter(), TelegramBot by bot, CoroutineScope by scope, BehaviourContext {

    private val additionalUpdatesSharedFlow = MutableSharedFlow<Update>(0, broadcastChannelsSize, onBufferOverflow)
    override val allUpdatesFlow: Flow<Update> = (additionalUpdatesSharedFlow.asSharedFlow()).let {
        if (upstreamUpdatesFlow != null) {
            var lastHandledUpdate = -1L
            (it + upstreamUpdatesFlow).filter {
                (it.updateId > lastHandledUpdate).also { passed -> if (passed) { lastHandledUpdate = it.updateId } }
            }
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
        triggersHolder: TriggersHolder
    ): DefaultBehaviourContext = DefaultBehaviourContext(bot, scope, broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow, triggersHolder)
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

/**
 * Creates new [BehaviourContext] using its [BehaviourContext.copy] method
 *
 * @param updatesFilter This param will not be used anymore
 */
fun <BC : BehaviourContext> BC.createSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
) = copy(
    scope = scope,
    upstreamUpdatesFlow = updatesUpstreamFlow,
    triggersHolder = triggersHolder
) as BC

/**
 * Creates new [BehaviourContext] using its [BehaviourContext.copy] method
 *
 * @param updatesFilter This param will not be used anymore
 */
@Deprecated("It is not recommended to use updates filter anymore")
fun <BC : BehaviourContext> BC.createSubContext(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    updatesFilter: CustomBehaviourContextAndTypeReceiver<BC, Boolean, Update>?,
) = createSubContext(scope, triggersHolder, updatesUpstreamFlow)

/**
 * Launch [behaviourContextReceiver] in context of [this] as [BehaviourContext] and as [kotlin.coroutines.CoroutineContext]
 *
 * @param stopOnCompletion ___FALSE BY DEFAULT___. Will stop [this] in case if passed true
 */
suspend fun <T, BC : BehaviourContext> BC.doInContext(
    stopOnCompletion: Boolean = false,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return withContext(coroutineContext) {
        behaviourContextReceiver().also { if (stopOnCompletion) stop() }
    }
}

/**
 * Creates new one [BehaviourContext] using [createSubContext] and launches [behaviourContextReceiver] in a new context
 * using [doInContext]
 *
 * @param stopOnCompletion ___TRUE BY DEFAULT___
 */
suspend fun <T, BC : BehaviourContext> BC.createSubContextAndDoWithUpdatesFilter(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return createSubContext(
        scope,
        triggersHolder,
        updatesUpstreamFlow
    ).doInContext(
        stopOnCompletion,
        behaviourContextReceiver
    )
}

/**
 * Creates new one [BehaviourContext] using [createSubContext] and launches [behaviourContextReceiver] in a new context
 * using [doInContext]
 *
 * @param stopOnCompletion ___TRUE BY DEFAULT___
 * @param updatesFilter Is not used anymore
 */
@Deprecated("It is not recommended to use updates filter anymore")
suspend fun <T, BC : BehaviourContext> BC.createSubContextAndDoWithUpdatesFilter(
    scope: CoroutineScope = LinkedSupervisorScope(),
    triggersHolder: TriggersHolder = this.triggersHolder,
    updatesUpstreamFlow: Flow<Update> = allUpdatesFlow,
    updatesFilter: CustomBehaviourContextAndTypeReceiver<BC, Boolean, Update>?,
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: CustomBehaviourContextReceiver<BC, T>
): T {
    return createSubContextAndDoWithUpdatesFilter(
        scope, triggersHolder, updatesUpstreamFlow, stopOnCompletion, behaviourContextReceiver
    )
}

/**
 * This method will cancel ALL subsequent contexts, expectations and waiters
 */
fun BehaviourContext.stop() = scope.cancel()
