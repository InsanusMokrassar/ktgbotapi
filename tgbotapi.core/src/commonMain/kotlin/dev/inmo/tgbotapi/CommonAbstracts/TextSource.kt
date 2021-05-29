package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.MultilevelTextSource
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource
import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.captionLength
import dev.inmo.tgbotapi.types.textLength
import dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForCaption
import dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForMessage
import dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForText
import dev.inmo.tgbotapi.types.MessageEntity.textsources.makeString

const val DirectInvocationOfTextSourceConstructor =
    "It is strongly not recommended to use constructors directly instead of factory methods"

@Deprecated(
    "Replaced",
    ReplaceWith("TextSourcesList", "dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSourcesList")
)
typealias TextSourcesList = TextSourcesList

@Deprecated("Replaced", ReplaceWith("TextSource", "dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource"))
typealias TextSource = dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.MessageEntity.textsources.plus"))
inline operator fun dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource.plus(other: dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource) =
    listOf(this, other)

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.MessageEntity.textsources.plus"))
inline operator fun dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource.plus(other: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>) =
    listOf(this) + other

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.MessageEntity.textsources.plus"))
inline operator fun dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource.plus(text: String) =
    listOf(this, regular(text))

@Suppress("NOTHING_TO_INLINE")
@Deprecated("Replaced", ReplaceWith("plus", "dev.inmo.tgbotapi.types.MessageEntity.textsources.plus"))
inline operator fun List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>.plus(text: String) = this + regular(text)

@Deprecated(
    "Replaced",
    ReplaceWith("MultilevelTextSource", "dev.inmo.tgbotapi.types.MessageEntity.textsources.MultilevelTextSource")
)
typealias MultilevelTextSource = MultilevelTextSource

@Deprecated("Replaced", ReplaceWith("makeString", "dev.inmo.tgbotapi.types.MessageEntity.textsources.makeString"))
fun List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>.makeString() = makeString()

@Deprecated(
    "Replaced",
    ReplaceWith("separateForMessage", "dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForMessage")
)
fun List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>.separateForMessage(limit: IntRange, numberOfParts: Int? = null) =
    separateForMessage(limit, numberOfParts)

/**
 * This method will prepare [TextSource]s list for messages. Remember, that first part will be separated with
 * [captionLength] and all others with
 */
@Deprecated(
    "Replaced",
    ReplaceWith("separateForCaption", "dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForCaption")
)
fun List<TextSource>.separateForCaption() = separateForCaption()

/**
 * This method will prepare [TextSource]s list for messages with [textLength]
 */
@Suppress("NOTHING_TO_INLINE")
@Deprecated(
    "Replaced",
    ReplaceWith("separateForText", "dev.inmo.tgbotapi.types.MessageEntity.textsources.separateForText")
)
inline fun List<TextSource>.separateForText() = separateForText()
