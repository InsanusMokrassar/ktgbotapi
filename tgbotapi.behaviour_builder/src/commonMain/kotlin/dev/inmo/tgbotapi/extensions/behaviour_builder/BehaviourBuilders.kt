package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.micro_utils.coroutines.ContextSafelyExceptionHandler
import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.*

/**
 * This function is used in [buildBehaviour] extensions to provide default [CoroutineScope] and allow to avoid all
 * unnecessary parameters except of block
 */
expect var defaultCoroutineScopeProvider: () -> CoroutineScope

/**
 * Use this method in case you wish to make some additional actions with [flowUpdatesFilter].
 *
 * **WARNING** This method WILL NOT launch any listening of updates. Use something like
 * [startGettingOfUpdatesByLongPolling] or tools for work with webhooks
 *
 * @see [BehaviourContext]
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun TelegramBot.buildBehaviour(
    flowUpdatesFilter: FlowsUpdatesFilter = FlowsUpdatesFilter(),
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    block: BehaviourContextReceiver<Unit>
): BehaviourContext = BehaviourContext(
    this,
    scope.let {
          if (defaultExceptionsHandler == null) {
              it
          } else {
              it + ContextSafelyExceptionHandler(defaultExceptionsHandler)
          }
    },
    flowUpdatesFilter
).apply {
    block()
}

/**
 * Use this method to build bot behaviour and run it via long polling. In case you wish to get [FlowsUpdatesFilter] for
 * additional manipulations, you must provide external [FlowsUpdatesFilter] in other [buildBehaviour] function.
 *
 * @see buildBehaviour
 * @see BehaviourContext
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun TelegramBot.buildBehaviourWithLongPolling(
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    block: BehaviourContextReceiver<Unit>
): Job {
    val behaviourContext = buildBehaviour(
        scope = scope,
        defaultExceptionsHandler = defaultExceptionsHandler,
        block = block
    )
    return longPolling(
        behaviourContext,
        scope = behaviourContext
    )
}
