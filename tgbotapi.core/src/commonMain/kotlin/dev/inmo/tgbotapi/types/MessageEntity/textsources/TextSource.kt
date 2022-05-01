package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.types.captionLength
import dev.inmo.tgbotapi.types.message.textsources.separateForCaption
import dev.inmo.tgbotapi.types.message.textsources.separateForMessage
import dev.inmo.tgbotapi.types.message.textsources.separateForText
import dev.inmo.tgbotapi.types.textLength

const val DirectInvocationOfTextSourceConstructor = "It is strongly not recommended to use constructors directly instead of factory methods"

@Deprecated("Replaced", ReplaceWith("TextSourcesList", "dev.inmo.tgbotapi.types.message.textsources.TextSourcesList"))
typealias TextSourcesList = dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
@Deprecated("Replaced", ReplaceWith("MutableTextSourcesList", "dev.inmo.tgbotapi.types.message.textsources.MutableTextSourcesList"))
typealias MutableTextSourcesList = dev.inmo.tgbotapi.types.message.textsources.MutableTextSourcesList

@Deprecated("Replaced", ReplaceWith("TextSource", "dev.inmo.tgbotapi.types.message.textsources.TextSource"))
typealias TextSource = dev.inmo.tgbotapi.types.message.textsources.TextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.message.textsources.plus"))
inline operator fun TextSource.plus(other: TextSource) = listOf(this, other)
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.message.textsources.plus"))
inline operator fun TextSource.plus(other: List<TextSource>) = listOf(this) + other
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.message.textsources.plus"))
inline operator fun TextSource.plus(text: String) = listOf(this, regular(text))
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.message.textsources.plus"))
inline operator fun List<TextSource>.plus(text: String) = this + regular(text)

@Deprecated("Replaced", ReplaceWith("MultilevelTextSource", "dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource"))
typealias MultilevelTextSource = dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource

@Deprecated("Replaced", ReplaceWith("separateForMessage", "dev.inmo.tgbotapi.types.message.textsources.separateForMessage"))
inline fun List<TextSource>.separateForMessage(limit: IntRange, numberOfParts: Int? = null) = separateForMessage(limit, numberOfParts)

/**
 * This method will prepare [TextSource]s list for messages. Remember, that first part will be separated with
 * [captionLength] and all others with
 */
@Deprecated("Replaced", ReplaceWith("separateForCaption", "dev.inmo.tgbotapi.types.message.textsources.separateForCaption"))
fun List<TextSource>.separateForCaption() = separateForCaption()

/**
 * This method will prepare [TextSource]s list for messages with [textLength]
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("separateForText", "dev.inmo.tgbotapi.types.message.textsources.separateForText"))
inline fun List<TextSource>.separateForText() = separateForText()
