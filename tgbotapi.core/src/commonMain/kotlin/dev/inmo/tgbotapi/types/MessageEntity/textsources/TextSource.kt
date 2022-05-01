package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.types.captionLength
import dev.inmo.tgbotapi.types.textLength
import kotlinx.serialization.Serializable

const val DirectInvocationOfTextSourceConstructor = "It is strongly not recommended to use constructors directly instead of factory methods"

typealias TextSourcesList = List<TextSource>
typealias MutableTextSourcesList = MutableList<TextSource>

@Serializable(TextSourceSerializer::class)
sealed interface TextSource {
    val markdown: String
    val markdownV2: String
    val html: String
    val source: String

    val asText: String
        get() = source
}

@Suppress("NOTHING_TO_INLINE")
inline operator fun TextSource.plus(other: TextSource) = listOf(this, other)
@Suppress("NOTHING_TO_INLINE")
inline operator fun TextSource.plus(other: List<TextSource>) = listOf(this) + other
@Suppress("NOTHING_TO_INLINE")
inline operator fun TextSource.plus(text: String) = listOf(this, regular(text))
@Suppress("NOTHING_TO_INLINE")
inline operator fun List<TextSource>.plus(text: String) = this + regular(text)

@Serializable(TextSourceSerializer::class)
sealed interface MultilevelTextSource : TextSource {
    val subsources: List<TextSource>
}

fun List<TextSource>.separateForMessage(limit: IntRange, numberOfParts: Int? = null): List<List<TextSource>> {
    if (isEmpty()) {
        return emptyList()
    }

    val resultList = mutableListOf<MutableList<TextSource>>(mutableListOf())
    var currentPartLength = 0
    val maxSize = limit.last + 1

    for (current in this) {
        if (current.source.length > maxSize) {
            error("Currently unsupported parts with size more than target one-message parts (${current.source.length} > ${maxSize})")
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
fun List<TextSource>.separateForCaption(): List<List<TextSource>> {
    val captionPart = separateForMessage(captionLength, 1).first()
    return listOf(captionPart) + minus(captionPart).separateForMessage(textLength)
}

/**
 * This method will prepare [TextSource]s list for messages with [textLength]
 */
@Suppress("NOTHING_TO_INLINE")
inline fun List<TextSource>.separateForText(): List<List<TextSource>> = separateForMessage(textLength)
