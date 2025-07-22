package dev.inmo.tgbotapi.utils.extensions

import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.*

val eachLineRegex = Regex("^[^\n]")

fun TextSourcesList.makeString(
    parseMode: ParseMode? = null
) = when (parseMode) {
    MarkdownParseMode -> makeMarkdownString()
    MarkdownV2ParseMode -> makeMarkdownV2String()
    HTMLParseMode -> makeHtmlString()
    null -> makeSourceString()
}


fun TextSourcesList.makeSourceString() = joinToString("") {
    it.source
}

fun TextSourcesList.makeHtmlString() = joinToString("") {
    it.html
}

fun TextSourcesList.makeMarkdownV2String(eachLineSeparator: String? = null) = joinToString("") {
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

fun TextSourcesList.makeMarkdownString() = joinToString("") {
    it.markdown
}
