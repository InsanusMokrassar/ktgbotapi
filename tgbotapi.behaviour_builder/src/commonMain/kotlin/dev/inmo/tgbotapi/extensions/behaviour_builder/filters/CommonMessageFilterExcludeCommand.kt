package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.not
import dev.inmo.tgbotapi.extensions.utils.textedContentOrNull
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource

/**
 * Use as initialFilter. Will exclude messages with [excludedCommand] if it is not null, if null - all messages with commands.
 * If [textBeginOnly] set to false, all commands inside of message will be taken in attention.
 *
 * **It is supposed, that you will pass command name without `/` or `!`**
 *
 * @param excludedCommand Pass non-null value to search specific command or null (default) to search any command
 * @param textBeginOnly Pass true (default) to check only start of message. Pass false to search in whole text of
 * content
 */
fun CommonMessageFilterExcludeCommand(
    excludedCommand: String? = null,
    textBeginOnly: Boolean = true
): CommonMessageFilter<*> {
    return !CommonMessageFilterIncludeText(
        when {
            excludedCommand == null -> BotCommandTextSource.CommandRegex
            textBeginOnly -> Regex("^[/!]$excludedCommand(\\s|$)")
            !textBeginOnly -> Regex("[/!]$excludedCommand(\\s|$)")
            else -> error("Unreachable code has been reached. It is error and must not happen")
        }
    )
}
