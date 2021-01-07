package dev.inmo.tgbotapi.extensions.steps.triggers_handling


import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.steps.ScenarioAndTypeReceiver
import dev.inmo.tgbotapi.extensions.steps.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.CallbackQuery.*
import dev.inmo.tgbotapi.types.message.ChatEvents.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.*
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.flow.filter

internal suspend inline fun <reified T : CallbackQuery> Scenario.onCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    noinline additionalFilter: (suspend (T) -> Boolean)? = null,
    noinline scenarioReceiver: ScenarioAndTypeReceiver<Unit, T>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asCallbackQueryUpdate() ?.data ?.let { query ->
        if (query is T) {
            if (additionalFilter == null || additionalFilter(query)) query else null
        } else {
            null
        }
    }
}.subscribeSafelyWithoutExceptions(scope) { triggerQuery ->
    val (jobToCancel, scenario) = if (includeFilterByChatInSubScenario) {
        val subFilter = FlowsUpdatesFilter()
        val subScenario = copy(flowsUpdatesFilter = subFilter)

        flowsUpdatesFilter.allUpdatesFlow.filter {
            val chat = it.sourceChat() ?: return@filter false
            chat.id.chatId == triggerQuery.user.id.chatId
        }.subscribeSafelyWithoutExceptions(scope, subFilter.asUpdateReceiver) to subScenario
    } else {
        null to this
    }
    safelyWithoutExceptions { scenario.scenarioReceiver(triggerQuery) }
    jobToCancel ?.cancel()
}


suspend fun Scenario.onDataCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (DataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, DataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)

suspend fun Scenario.onGameShortNameCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (GameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, GameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onInlineMessageIdCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, InlineMessageIdCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onInlineMessageIdDataCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdDataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, InlineMessageIdDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onInlineMessageIdGameShortNameCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (InlineMessageIdGameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, InlineMessageIdGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMessageCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (MessageCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, MessageCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMessageDataCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (MessageDataCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, MessageDataCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMessageGameShortNameCallbackQuery(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (MessageGameShortNameCallbackQuery) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, MessageGameShortNameCallbackQuery>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onUnknownCallbackQueryType(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (UnknownCallbackQueryType) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, UnknownCallbackQueryType>
) = onCallbackQuery(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
