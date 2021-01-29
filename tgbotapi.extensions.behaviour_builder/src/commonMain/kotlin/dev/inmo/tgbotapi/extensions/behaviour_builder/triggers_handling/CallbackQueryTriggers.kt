package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling


import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.CallbackQuery.*

internal suspend inline fun <reified T : CallbackQuery> BehaviourContext.onCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (T) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, T>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asCallbackQueryUpdate() ?.data ?.let { query ->
        if (query is T) {
            if (additionalFilter == null || additionalFilter(query)) query else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptions(scope) { triggerQuery ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerQuery.user.id.chatId }
        } else {
            null
        }
    ) {
        scenarioReceiver(triggerQuery)
    }
}


suspend fun BehaviourContext.onDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (DataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, DataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)

suspend fun BehaviourContext.onGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (GameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, GameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdDataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onInlineMessageIdGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdGameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, InlineMessageIdGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onMessageCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onMessageDataCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageDataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onMessageGameShortNameCallbackQuery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (MessageGameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, MessageGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onUnknownCallbackQueryType(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (UnknownCallbackQueryType) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, UnknownCallbackQueryType>
) = onCallbackQuery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
