package dev.inmo.tgbotapi.extensions.steps.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.CommonAbstracts.textSources
import dev.inmo.tgbotapi.extensions.steps.*
import dev.inmo.tgbotapi.extensions.steps.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent

suspend fun Scenario.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<TextContent>>
) {
    flowsUpdatesFilter.expectFlow(bot) {
        it.asMessageUpdate() ?.data ?.asContentMessage() ?.let { message ->
            message.content.asTextContent() ?.let {
                val textSources = it.textSources
                val sizeRequirement = if (requireOnlyCommandInMessage) {
                    textSources.size == 1
                } else {
                    true
                }
                if (sizeRequirement && textSources.any { commandRegex.matches(it.asBotCommandTextSource() ?.command ?: return@any false) }) {
                    message as ContentMessage<TextContent>
                } else {
                    null
                }
            }
        }
    }.subscribeSafelyWithoutExceptions(scope) {
        scenarioReceiver(it)
    }
}


