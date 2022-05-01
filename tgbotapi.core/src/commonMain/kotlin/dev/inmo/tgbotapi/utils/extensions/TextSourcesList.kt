package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.*

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

inline fun TextSourcesList.makeMarkdownV2String() = joinToString("") {
    it.markdownV2
}

inline fun TextSourcesList.makeMarkdownString() = joinToString("") {
    it.markdown
}
