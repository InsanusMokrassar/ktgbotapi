package dev.inmo.tgbotapi.extensions.behaviour_builder

import dev.inmo.kslog.common.e
import dev.inmo.micro_utils.coroutines.ContextSafelyExceptionHandler
import dev.inmo.micro_utils.coroutines.ExceptionHandler
import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.extensions.DefaultKTgBotAPIPrivacyCommand
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.longPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.startGettingOfUpdatesByLongPolling
import dev.inmo.tgbotapi.extensions.utils.updates.retrieving.updateHandlerWithMediaGroupsAdaptation
import dev.inmo.tgbotapi.types.Seconds
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.DefaultKTgBotAPIKSLog
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
 * @param mediaGroupsDebounceTimeMillis Will be used for calling of [updateHandlerWithMediaGroupsAdaptation]. Pass null
 * in case you wish to enable classic way of updates handling, but in that mode some media group messages can be
 * retrieved in different updates
 *
 * @see buildBehaviour
 * @see BehaviourContext
 * @see startGettingOfUpdatesByLongPolling
 */
suspend fun TelegramBot.buildBehaviourWithLongPolling(
    scope: CoroutineScope = defaultCoroutineScopeProvider(),
    defaultExceptionsHandler: ExceptionHandler<Unit>? = null,
    timeoutSeconds: Seconds = 30,
    autoDisableWebhooks: Boolean = true,
    autoSkipTimeoutExceptions: Boolean = true,
    mediaGroupsDebounceTimeMillis: Long? = 1000L,
    block: BehaviourContextReceiver<Unit>
): Job {
    val behaviourContext = buildBehaviour(
        scope = scope,
        defaultExceptionsHandler = defaultExceptionsHandler,
        block = block
    )
    return longPolling(
        behaviourContext,
        scope = behaviourContext,
        timeoutSeconds = timeoutSeconds,
        autoDisableWebhooks = autoDisableWebhooks,
        autoSkipTimeoutExceptions = autoSkipTimeoutExceptions,
        mediaGroupsDebounceTimeMillis = mediaGroupsDebounceTimeMillis
    )
}
