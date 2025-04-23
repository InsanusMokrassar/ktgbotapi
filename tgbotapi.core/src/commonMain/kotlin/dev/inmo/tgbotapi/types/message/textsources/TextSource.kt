package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.captionLength
import dev.inmo.tgbotapi.types.textLength
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.Serializable

const val DirectInvocationOfTextSourceConstructor = "It is strongly not recommended to use constructors directly instead of factory methods"

typealias TextSourcesList = List<TextSource>
typealias MutableTextSourcesList = MutableList<TextSource>

@Serializable(TextSourceSerializer::class)
@ClassCastsIncluded
sealed interface TextSource {
    val markdown: String
    val markdownV2: String
    val html: String
    val source: String

    val asText: String
        get() = source
}

operator fun TextSource.plus(other: TextSource) = when {
    this is RegularTextSource && other is RegularTextSource -> listOf(RegularTextSource(source + other.source))
    else -> listOf(this, other)
}

operator fun TextSource.plus(text: String) = this + regularTextSource(text)

operator fun List<TextSource>.plus(text: String): List<TextSource> {
    val newList = mutableListOf<TextSource>()

    for (i in 0 until size - 1) {
        newList.add(get(i))
    }

    val sublist = lastOrNull() ?.let {
        it + text
    } ?: listOf(regularTextSource(text))

    newList.addAll(sublist)

    return newList
}

operator fun TextSource.plus(other: List<TextSource>) = other.fold(listOf(this)) { acc, textSource ->
    val newList = mutableListOf<TextSource>()

    for (i in 0 until acc.size - 1) {
        newList.add(acc.get(i))
    }

    newList.addAll(acc.last() + textSource)

    newList
}

@Serializable(TextSourceSerializer::class)
sealed interface MultilevelTextSource : TextSource {
    val subsources: List<TextSource>
}

fun List<TextSource>.splitForMessage(
    limit: IntRange,
    numberOfParts: Int? = null,
): List<List<TextSource>> {
    if (isEmpty()) {
        return emptyList()
    }

    val resultList = mutableListOf<MutableList<TextSource>>(mutableListOf())
    var currentPartLength = 0
    val maxSize = limit.last + 1

    for (current in this) {
        if (current.source.length > maxSize) {
            error("Currently unsupported parts with size more than target one-message parts (${current.source.length} > $maxSize)")
        }

        if (currentPartLength + current.source.length > maxSize) {
            if (numberOfParts == null || numberOfParts < resultList.size) {
                resultList.add(mutableListOf())
                currentPartLength = 0
            } else {
                break
            }
        }

        resultList.last().add(current)
        currentPartLength += current.source.length
    }

    return resultList
}

/**
 * This method will prepare [TextSource]s list for messages. Remember, that first part will be separated with
 * [captionLength] and all others with
 */
fun List<TextSource>.splitForCaption(): List<List<TextSource>> {
    val captionPart = splitForMessage(captionLength, 1).first()
    return listOf(captionPart) + minus(captionPart).splitForMessage(textLength)
}

/**
 * This method will prepare [TextSource]s list for messages with [textLength]
 */
inline fun List<TextSource>.splitForText(): List<List<TextSource>> = splitForMessage(textLength)

fun List<TextSource>.separateForMessage(
    limit: IntRange,
    numberOfParts: Int? = null,
): List<List<TextSource>> = splitForMessage(
    limit,
    numberOfParts,
)

/**
 * This method will prepare [TextSource]s list for messages. Remember, that first part will be separated with
 * [captionLength] and all others with
 */
fun List<TextSource>.separateForCaption(): List<List<TextSource>> = splitForCaption()

/**
 * This method will prepare [TextSource]s list for messages with [textLength]
 */
inline fun List<TextSource>.separateForText(): List<List<TextSource>> = splitForText()
