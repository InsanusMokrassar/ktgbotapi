package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource

object TelegramBotCommandsDefaults {
    const val defaultArgsSeparator = " "
    val defaultArgsSeparatorRegex = Regex(defaultArgsSeparator)
    const val defaultNamesArgsSeparator = "="
    val defaultNamesArgsSeparatorRegex = Regex(defaultNamesArgsSeparator)
}

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun List<TextSource>.parseCommandsWithArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
): MutableMap<String, Array<String>> {
    val result = mutableMapOf<String, Array<String>>()
    var currentBotCommandSource: BotCommandTextSource? = null
    var currentArgs = ""

    fun includeCurrent() = currentBotCommandSource ?.let {
        currentArgs = currentArgs.trim()
        result[it.command] =
            if (currentArgs.isNotEmpty()) {
                currentArgs.split(argsSeparator).toTypedArray()
            } else {
                emptyArray()
            }
        currentArgs = ""
        currentBotCommandSource = null
    }

    for (textSource in this) {
        if (textSource is BotCommandTextSource) {
            includeCurrent()
            currentBotCommandSource = textSource
        } else {
            currentArgs += textSource.source
        }
    }
    includeCurrent()

    return result
}

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun TextedWithTextSources.parseCommandsWithArgs(argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex) = textSources ?.parseCommandsWithArgs(argsSeparator) ?: emptyMap()

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun ContentMessage<TextContent>.parseCommandsWithArgs(argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex) = content.parseCommandsWithArgs(argsSeparator)

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun List<TextSource>.parseCommandsWithArgs(argsSeparator: String): MutableMap<String, Array<String>> = parseCommandsWithArgs(Regex(argsSeparator))

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun TextedWithTextSources.parseCommandsWithArgs(argsSeparator: String) = parseCommandsWithArgs(Regex(argsSeparator))

/**
 * Parse commands and their args. Logic will find command, get all subsequent data as args until new command
 */
fun ContentMessage<TextContent>.parseCommandsWithArgs(argsSeparator: String) = parseCommandsWithArgs(Regex(argsSeparator))

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun List<TextSource>.parseCommandsWithNamedArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
): Map<String, List<Pair<String, String>>> {
    val withArgs = parseCommandsWithArgs(argsSeparator)

    return withArgs.mapValues { (k, v) ->
        v.flatMap {
            it.split(nameArgSeparator, 2).map { v -> it to v }
        }
    }
}

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun TextedWithTextSources.parseCommandsWithNamedArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
) = textSources ?.parseCommandsWithNamedArgs(argsSeparator = argsSeparator, nameArgSeparator = nameArgSeparator) ?: emptyMap()

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun ContentMessage<TextContent>.parseCommandsWithNamedArgs(
    argsSeparator: Regex = TelegramBotCommandsDefaults.defaultArgsSeparatorRegex,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
) = content.parseCommandsWithNamedArgs(argsSeparator = argsSeparator, nameArgSeparator = nameArgSeparator)

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun List<TextSource>.parseCommandsWithNamedArgs(
    argsSeparator: String,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
): Map<String, List<Pair<String, String>>> = parseCommandsWithNamedArgs(Regex(pattern = argsSeparator), nameArgSeparator)

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun TextedWithTextSources.parseCommandsWithNamedArgs(
    argsSeparator: String,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
) = parseCommandsWithNamedArgs(argsSeparator = Regex(pattern = argsSeparator), nameArgSeparator = nameArgSeparator)

/**
 * Uses [parseCommandsWithArgs] to create base [argsSeparator] split args for commands and map their as k-v pairs.
 * Sample:
 *
 * ```bash
 * /command args1=value1 arg2=value2 arg1=value3
 * ```
 *
 * Will produce [Map] with one key `command` and the list of three pairs:
 *
 * 1. `args1` to `value1`
 * 2. `args2` to `value2`
 * 3. `args1` to `value3`
 *
 * @return Array of named arguments
 */
fun ContentMessage<TextContent>.parseCommandsWithNamedArgs(
    argsSeparator: String,
    nameArgSeparator: Regex = TelegramBotCommandsDefaults.defaultNamesArgsSeparatorRegex,
) = parseCommandsWithNamedArgs(argsSeparator = Regex(pattern = argsSeparator), nameArgSeparator = nameArgSeparator)
