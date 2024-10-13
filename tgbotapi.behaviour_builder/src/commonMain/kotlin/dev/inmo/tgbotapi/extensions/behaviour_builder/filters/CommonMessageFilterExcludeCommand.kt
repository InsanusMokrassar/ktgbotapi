package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.not
import dev.inmo.tgbotapi.extensions.utils.textedContentOrNull
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

/**
 * Use as initialFilter. Will exclude messages with [command] if it is not null, if null - all messages with commands.
 * If [textBeginOnly] set to false, all commands inside of message will be taken in attention.
 *
 * **Command line starts with `/` or `!`**
 *
 * @param command Pass non-null value to search specific command or null to search any command
 * @param textBeginOnly Pass true (default) to check only start of message. Pass false to search in whole text of
 * content
 */
fun CommonMessageFilterExcludeCommand(
    command: String?,
    textBeginOnly: Boolean = true
): CommonMessageFilter<*> {
    return !CommonMessageFilterIncludeText(
        when {
            command == null -> BotCommandTextSource.CommandRegex
            textBeginOnly -> Regex("^[/!]$command(\\s|$)")
            !textBeginOnly -> Regex("[/!]$command(\\s|$)")
            else -> error("Unreachable code has been reached. It is error and must not happen")
        }
    )
}
