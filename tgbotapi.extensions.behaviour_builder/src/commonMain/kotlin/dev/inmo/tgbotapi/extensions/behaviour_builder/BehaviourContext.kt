package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.filter

typealias BehaviourContextReceiver<T> = suspend BehaviourContext.() -> T
typealias BehaviourContextAndTypeReceiver<T, I> = suspend BehaviourContext.(I) -> T

/**
 * This class contains all necessary tools for work with bots and especially for [buildBehaviour]
 *
 * @param scope This param will be used for creating of some subscriptions inside of methods, updates listening and
 * different other things in context of working with [CoroutineScope] and coroutines.
 * @param flowsUpdatesFilter This parameter will be used to subscribe on different types of update
 */
data class BehaviourContext(
    val bot: TelegramBot,
    val scope: CoroutineScope,
    val flowsUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter()
) : FlowsUpdatesFilter by flowsUpdatesFilter, TelegramBot by bot, CoroutineScope by scope

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [newFlowsUpdatesFilterSetUp] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]
 *
 * @param newFlowsUpdatesFilterSetUp As a parameter receives [FlowsUpdatesFilter] from old [this] [BehaviourContext.flowsUpdatesFilter]
 */
suspend fun <T> BehaviourContext.doInSubContextWithFlowsUpdatesFilterSetup(
    newFlowsUpdatesFilterSetUp: BehaviourContextAndTypeReceiver<Unit, FlowsUpdatesFilter>?,
    behaviourContextReceiver: BehaviourContextReceiver<T>
) = copy(
    flowsUpdatesFilter = FlowsUpdatesFilter(),
    scope = CoroutineScope(scope.coroutineContext + SupervisorJob())
).run {
    newFlowsUpdatesFilterSetUp ?.let {
        it.apply { invoke(this@run, this@doInSubContextWithFlowsUpdatesFilterSetup.flowsUpdatesFilter) }
    }
    behaviourContextReceiver().also { stop() }
}

/**
 * Creates new one [BehaviourContext], adding subsequent [FlowsUpdatesFilter] in case [updatesFilter] is provided and
 * [CoroutineScope] as new [BehaviourContext.scope]
 */
suspend fun <T> BehaviourContext.doInSubContextWithUpdatesFilter(
    updatesFilter: BehaviourContextAndTypeReceiver<Boolean, Update>?,
    behaviourContextReceiver: BehaviourContextReceiver<T>
) = doInSubContextWithFlowsUpdatesFilterSetup(
    newFlowsUpdatesFilterSetUp = updatesFilter ?.let {
        { oldOne ->
            oldOne.allUpdatesFlow.filter { updatesFilter(it) }.subscribeSafelyWithoutExceptions(scope, asUpdateReceiver)
        }
    },
    behaviourContextReceiver
)

suspend fun <T> BehaviourContext.doInSubContext(
    behaviourContextReceiver: BehaviourContextReceiver<T>
) = doInSubContextWithFlowsUpdatesFilterSetup(newFlowsUpdatesFilterSetUp = null, behaviourContextReceiver)

/**
 * This method will cancel ALL subsequent contexts, expectations and waiters
 */
fun BehaviourContext.stop() = scope.cancel()
