package dev.inmo.tgbotapi.extensions.steps.triggers_handling

import dev.inmo.tgbotapi.CommonAbstracts.textSources
import dev.inmo.tgbotapi.extensions.steps.*
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import kotlinx.coroutines.Job

suspend fun BehaviourContext.command(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ContentMessage<TextContent>>
): Job = onText(
    includeFilterByChatInBehaviourSubContext,
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

suspend inline fun BehaviourContext.onCommand(
    commandRegex: Regex,
    requireOnlyCommandInMessage: Boolean = true,
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ContentMessage<TextContent>>
): Job = command(commandRegex, requireOnlyCommandInMessage, includeFilterByChatInBehaviourSubContext, scenarioReceiver)
