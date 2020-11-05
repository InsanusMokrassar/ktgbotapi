package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.ParseMode.*
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.link
import dev.inmo.tgbotapi.utils.extensions.*

const val markdownBoldControl = "*"
const val markdownItalicControl = "_"
const val markdownCodeControl = "`"
const val markdownPreControl = "```"

const val markdownV2ItalicUnderlineDelimiter = "\u0013"
const val markdownV2StrikethroughControl = "~"
const val markdownV2UnderlineControl = "__"
const val markdownV2UnderlineEndControl = "$markdownV2UnderlineControl$markdownV2ItalicUnderlineDelimiter"
const val markdownV2ItalicEndControl = "$markdownItalicControl$markdownV2ItalicUnderlineDelimiter"

const val htmlBoldControl = "b"
const val htmlItalicControl = "i"
const val htmlCodeControl = "code"
const val htmlPreControl = "pre"
const val htmlUnderlineControl = "u"
const val htmlStrikethroughControl = "s"

private fun String.markdownDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "$openControlSymbol${toMarkdown()}$closeControlSymbol"
private fun String.markdownV2Default(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol,
    escapeFun: String.() -> String = String::escapeMarkdownV2Common
) = "$openControlSymbol${escapeFun()}$closeControlSymbol"
private fun String.htmlDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "<$openControlSymbol>${toHtml()}</$closeControlSymbol>"


internal fun String.linkMarkdown(link: String): String = "[${toMarkdown()}](${link.toMarkdown()})"
internal fun String.linkMarkdownV2(link: String): String = "[${escapeMarkdownV2Common()}](${link.escapeMarkdownV2Link()})"
internal fun String.linkHTML(link: String): String = "<a href=\"$link\">${toHtml()}</a>"

internal fun String.boldMarkdown(): String = markdownDefault(markdownBoldControl)

internal fun String.italicMarkdown(): String = markdownDefault(markdownItalicControl)

/**
 * Crutch for support of strikethrough in default markdown. Simply add modifier, but it will not look like correct
 */

internal fun String.strikethroughMarkdown(): String = map { it + "\u0336" }.joinToString("")
internal fun String.strikethroughMarkdownV2(): String = markdownV2Default(markdownV2StrikethroughControl)


/**
 * Crutch for support of underline in default markdown. Simply add modifier, but it will not look like correct
 */

internal fun String.underlineMarkdown(): String = map { it + "\u0347" }.joinToString("")
internal fun String.underlineMarkdownV2(): String = markdownV2Default(markdownV2UnderlineControl, markdownV2UnderlineEndControl)
internal fun String.underlineHTML(): String = htmlDefault(htmlUnderlineControl)

internal fun String.codeMarkdown(): String = markdownDefault(markdownCodeControl)
internal fun String.codeMarkdownV2(): String = markdownV2Default(markdownCodeControl, escapeFun = String::escapeMarkdownV2PreAndCode)
internal fun String.codeHTML(): String = htmlDefault(htmlCodeControl)

internal fun String.preMarkdown(language: String? = null): String = markdownDefault(
    "$markdownPreControl${language ?: ""}\n",
    "\n$markdownPreControl"
)
internal fun String.preMarkdownV2(language: String? = null): String = markdownV2Default(
    "$markdownPreControl${language ?: ""}\n",
    "\n$markdownPreControl",
    String::escapeMarkdownV2PreAndCode
)
internal fun String.preHTML(language: String? = null): String = htmlDefault(
    language ?.let {
        "$htmlPreControl><$htmlCodeControl class=\"language-$language\""
    } ?: htmlPreControl,
    language ?.let {
        "$htmlCodeControl></$htmlPreControl"
    } ?: htmlPreControl
)

internal fun String.emailMarkdown(): String = linkMarkdown("mailto://$${toMarkdown()}")

private inline fun String.mention(adapt: String.() -> String): String = if (startsWith("@")) {
    adapt()
} else {
    "@${adapt()}"
}

private inline fun String.hashTag(adapt: String.() -> String): String = if (startsWith("#")) {
    adapt()
} else {
    "#${adapt()}"
}

internal fun String.textMentionMarkdown(userId: UserId): String = linkMarkdown(userId.link)
internal fun String.textMentionMarkdownV2(userId: UserId): String = linkMarkdownV2(userId.link)

internal fun String.mentionMarkdown(): String = mention(String::toMarkdown)

internal fun String.hashTagMarkdown(): String = hashTag(String::toMarkdown)
internal fun String.hashTagHTML(): String = hashTag(String::toHtml)

internal fun String.phoneMarkdown(): String = toMarkdown()

internal fun String.command(adapt: String.() -> String): String = if (startsWith("/")) {
    adapt()
} else {
    "/${adapt()}"
}

internal fun String.commandMarkdown(): String = command(String::toMarkdown)
internal fun String.commandMarkdownV2(): String = command(String::escapeMarkdownV2Common)
internal fun String.commandHTML(): String = command(String::toHtml)

internal fun String.regularMarkdown(): String = toMarkdown()
internal fun String.regularMarkdownV2(): String = escapeMarkdownV2Common()
internal fun String.regularHtml(): String = toHtml()

internal fun String.cashTagMarkdown(): String = toMarkdown()

internal infix fun String.code(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> codeHTML()
    is Markdown -> codeMarkdown()
    is MarkdownV2 -> codeMarkdownV2()
}

internal infix fun Pair<String, String>.link(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> first.linkHTML(second)
    is Markdown -> first.linkMarkdown(second)
    is MarkdownV2 -> first.linkMarkdownV2(second)
}


internal infix fun String.command(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> commandHTML()
    is Markdown -> commandMarkdown()
    is MarkdownV2 -> commandMarkdownV2()
}


internal infix fun String.underline(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> underlineHTML()
    is Markdown -> underlineMarkdown()
    is MarkdownV2 -> underlineMarkdownV2()
}
