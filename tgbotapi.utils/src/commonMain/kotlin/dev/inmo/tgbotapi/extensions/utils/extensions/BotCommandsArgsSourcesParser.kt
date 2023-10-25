package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.TextedWithTextSources
import dev.inmo.tgbotapi.extensions.utils.botCommandTextSourceOrNull
import dev.inmo.tgbotapi.extensions.utils.ifBotCommandTextSource
import dev.inmo.tgbotapi.extensions.utils.whenBotCommandTextSource
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.textsources.BotCommandTextSource
import dev.inmo.tgbotapi.types.message.textsources.TextSource

// Sources

/**
 * Parse text sources to find commands with their arguments. This method will skip all the text sources __before__
 * first command and all following text sources until the next command will be guessed as an args of last found command
 */
fun List<TextSource>.parseCommandsWithArgsSources(): Map<BotCommandTextSource, Array<TextSource>> {
    var currentCommandTextSource: BotCommandTextSource? = null
    val currentArgs = mutableListOf<TextSource>()
    val result = mutableMapOf<BotCommandTextSource, Array<TextSource>>()

    fun addCurrentCommandToResult() {
        currentCommandTextSource ?.let {
            result[it] = currentArgs.toTypedArray()
            currentArgs.clear()
        }
    }

    forEach {
        it.whenBotCommandTextSource {
            addCurrentCommandToResult()
            currentCommandTextSource = it
            return@forEach
        }
        currentArgs.add(it)
    }
    addCurrentCommandToResult()

    return result
}

/**
 * Parse text sources to find commands with their arguments. This method will skip all the text sources __before__
 * first command and all following text sources until the next command will be guessed as an args of last found command
 */
fun TextedWithTextSources.parseCommandsWithArgsSources() = textSources?.parseCommandsWithArgsSources() ?: emptyMap()

/**
 * Parse text sources to find commands with their arguments. This method will skip all the text sources __before__
 * first command and all following text sources until the next command will be guessed as an args of last found command
 */
fun ContentMessage<TextContent>.parseCommandsWithArgsSources() = content.parseCommandsWithArgsSources()