package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.*
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filter

typealias BehaviourContextReceiver<T> = suspend BehaviourContext.() -> T
typealias BehaviourContextAndTypeReceiver<T, I> = suspend BehaviourContext.(I) -> T
typealias BehaviourContextAndTwoTypesReceiver<T, I1, I2> = suspend BehaviourContext.(I1, I2) -> T
internal inline fun <T, I1, I2> BehaviourContextAndTwoTypesReceiver<T, I1, I2>.toOneType(
    i1: I1,
): BehaviourContextAndTypeReceiver<T, I2> = { invoke(this, i1, it) }

/**
 * This class contains all necessary tools for work with bots and especially for [buildBehaviour]
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

    fun copy(
        bot: TelegramBot = this.bot,
        scope: CoroutineScope = this.scope,
        flowsUpdatesFilter: FlowsUpdatesFilter = this.flowsUpdatesFilter
    ): BehaviourContext
}

class DefaultBehaviourContext(
    override val bot: TelegramBot,
    override val scope: CoroutineScope,
    override val flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
) : FlowsUpdatesFilter by flowsUpdatesFilter, TelegramBot by bot, CoroutineScope by scope, BehaviourContext {
    override fun copy(
        bot: TelegramBot,
        scope: CoroutineScope,
        flowsUpdatesFilter: FlowsUpdatesFilter
    ): DefaultBehaviourContext = DefaultBehaviourContext(bot, scope, flowsUpdatesFilter)
}

fun BehaviourContext(
    bot: TelegramBot,
    scope: CoroutineScope,
    flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
) = DefaultBehaviourContext(bot, scope, flowsUpdatesFilter)

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [newFlowsUpdatesFilterSetUp] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]. You must do all subscription/running of longPolling manually.
 *
 * @param newFlowsUpdatesFilterSetUp As a parameter receives [FlowsUpdatesFilter] from old [this] [BehaviourContext.flowsUpdatesFilter]
 */
@RiskFeature("It is recommended to use doInSubContextWithUpdatesFilter instead. " +
    "This method is low level and should not be used in case you are not pretty sure you need it.")
suspend fun <T> BehaviourContext.doInSubContextWithFlowsUpdatesFilterSetup(
    newFlowsUpdatesFilterSetUp: BehaviourContextAndTypeReceiver<Unit, FlowsUpdatesFilter>?,
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: BehaviourContextReceiver<T>
): T {
    return copy(
        flowsUpdatesFilter = FlowsUpdatesFilter(),
        scope = LinkedSupervisorScope()
    ).run {
        newFlowsUpdatesFilterSetUp ?.let {
            it.apply { invoke(this@run, this@doInSubContextWithFlowsUpdatesFilterSetup.flowsUpdatesFilter) }
        }
        withContext(coroutineContext) {
            behaviourContextReceiver().also { if (stopOnCompletion) stop() }
        }
    }
}

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [updatesFilter] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]
 */
suspend fun <T> BehaviourContext.doInSubContextWithUpdatesFilter(
    updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?,
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: BehaviourContextReceiver<T>
): T = doInSubContextWithFlowsUpdatesFilterSetup(
    newFlowsUpdatesFilterSetUp = updatesFilter ?.let {
        { oldOne ->
            weakLaunch {
                oldOne.allUpdatesFlow.filter { updatesFilter(it) }.subscribeSafelyWithoutExceptions(this, block = asUpdateReceiver)
            }
        }
    } ?: { oldOne ->
        weakLaunch {
            oldOne.allUpdatesFlow.subscribeSafelyWithoutExceptions(this, block = asUpdateReceiver)
        }
    },
    stopOnCompletion,
    behaviourContextReceiver
)

suspend fun <T> BehaviourContext.doInSubContext(
    stopOnCompletion: Boolean = true,
    behaviourContextReceiver: BehaviourContextReceiver<T>
) = doInSubContextWithUpdatesFilter(updatesFilter = null, stopOnCompletion, behaviourContextReceiver)

/**
 * This method will cancel ALL subsequent contexts, expectations and waiters
 */
fun BehaviourContext.stop() = scope.cancel()
