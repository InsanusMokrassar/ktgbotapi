package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling


import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.optionallyWrapWithLaunch
import dev.inmo.tgbotapi.extensions.utils.asCallbackQueryUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.CallbackQuery.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

internal suspend inline fun <reified T : CallbackQuery> BehaviourContext.onCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (T) -> Boolean)? = null,
    performInParallel: Boolean = true,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asCallbackQueryUpdate() ?.data ?.let { query ->
        if (query is T) {
            if (additionalFilter == null || additionalFilter(query)) query else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptions(
    scope,
    optionallyWrapWithLaunch(performInParallel) { triggerQuery ->
        doInSubContextWithUpdatesFilter(
            updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
                { it.sourceChat() ?.id ?.chatId == triggerQuery.user.id.chatId }
            } else {
                null
            },
            stopOnCompletion = false
        ) {
            scenarioReceiver(triggerQuery)
        }
    }
)


suspend fun BehaviourContext.onDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (DataCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, DataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)

suspend fun BehaviourContext.onGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (GameShortNameCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, GameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdDataCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdGameShortNameCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMessageCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMessageDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageDataCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMessageGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageGameShortNameCallbackQuery) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onUnknownCallbackQueryType(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (UnknownCallbackQueryType) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, UnknownCallbackQueryType>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, performInParallel, scenarioReceiver)
