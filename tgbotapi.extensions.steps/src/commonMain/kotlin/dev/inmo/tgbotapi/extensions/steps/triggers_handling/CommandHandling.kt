package dev.inmo.tgbotapi.extensions.steps.triggers_handling

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.CommonAbstracts.textSources
import dev.inmo.tgbotapi.extensions.steps.*
import dev.inmo.tgbotapi.extensions.steps.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.filter

suspend fun Scenario.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInSubScenario: Boolean = true,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<TextContent>>
): Job = onText(
    includeFilterByChatInSubScenario,
    { message ->
        val content = message.content
        val textSources = content.textSources
        val sizeRequirement = if (requireOnlyCommandInMessage) {
            textSources.size == 1
        } else {
            true
        }
        sizeRequirement && textSources.any { commandRegex.matches(it.asBotCommandTextSource() ?.command ?: return@any false) }
    },
    scenarioReceiver
)


