package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.*

val eachLineRegex = Regex("^[^\n]")

inline fun TextSourcesList.makeString(
    parseMode: ParseMode? = null
) = when (parseMode) {
    MarkdownParseMode -> makeMarkdownString()
    MarkdownV2ParseMode -> makeMarkdownV2String()
    HTMLParseMode -> makeHtmlString()
    null -> makeSourceString()
}


inline fun TextSourcesList.makeSourceString() = joinToString("") {
    it.source
}

inline fun TextSourcesList.makeHtmlString() = joinToString("") {
    it.html
}

inline fun TextSourcesList.makeMarkdownV2String(eachLineSeparator: String? = null) = joinToString("") {
    it.markdownV2
}.let {
    if (eachLineSeparator == null) {
        it
    } else {
        it.let {
            if (it.startsWith("\n")) {
                it
            } else {
                "$eachLineSeparator$it"
            }
        }.replace(
            "\n",
            "\n$eachLineSeparator"
        )
    }
}

inline fun TextSourcesList.makeMarkdownString() = joinToString("") {
    it.markdown
}
