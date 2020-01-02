package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toHtml
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toMarkdown

const val markdownBoldControl = "*"
const val markdownItalicControl = "_"
const val markdownCodeControl = "`"
const val markdownPreControl = "```"

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
private fun String.htmlDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "<$openControlSymbol>${toHtml()}</$closeControlSymbol>"

fun String.linkMarkdown(link: String): String = "[${toMarkdown()}]($link)"
fun String.linkHTML(link: String): String = "<a href=\"$link\">${toHtml()}</a>"


fun String.boldMarkdown(): String = markdownDefault(markdownBoldControl)
fun String.boldHTML(): String = htmlDefault(htmlBoldControl)


fun String.italicMarkdown(): String = markdownDefault(markdownItalicControl)
fun String.italicHTML(): String =htmlDefault(htmlItalicControl)


fun String.codeMarkdown(): String = markdownDefault(markdownCodeControl)
fun String.codeHTML(): String = htmlDefault(htmlCodeControl)


fun String.preMarkdown(language: String? = null): String = markdownDefault(
    "$markdownPreControl${language ?.let { "$it\n" } ?: "\n"}",
    "\n$markdownPreControl"
)
fun String.preHTML(language: String? = null): String = htmlDefault(
    language ?.let { _ ->
        "$htmlPreControl><$htmlCodeControl class=\"language-$language\""
    } ?: htmlPreControl,
    language ?.let { _ ->
        "$htmlCodeControl></$htmlPreControl"
    } ?: htmlPreControl
)


fun String.emailMarkdown(): String = linkMarkdown("mailto://$${toMarkdown()}")
fun String.emailHTML(): String = linkHTML("mailto://$${toHtml()}")

/**
 * Crutch for support of strikethrough in default markdown. Simply add modifier, but it will not look like correct
 */
fun String.strikethroughMarkdown(): String = map { it + "\u0336" }.joinToString("")
fun String.strikethroughHTML(): String = htmlDefault(htmlStrikethroughControl)


/**
 * Crutch for support of underline in default markdown. Simply add modifier, but it will not look like correct
 */
fun String.underlineMarkdown(): String = map { it + "\u0347" }.joinToString("")
fun String.underlineHTML(): String = htmlDefault(htmlUnderlineControl)


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


fun String.mentionMarkdown(userId: UserId): String = linkMarkdown(userId.link)
fun String.mentionHTML(userId: UserId): String = linkHTML(userId.link)


fun String.mentionMarkdown(): String = mention(String::toMarkdown)
fun String.mentionHTML(): String = mention(String::toHtml)


fun String.hashTagMarkdown(): String = hashTag(String::toMarkdown)
fun String.hashTagHTML(): String = hashTag(String::toHtml)


fun String.phoneMarkdown(): String = toMarkdown()
fun String.phoneHTML(): String = toHtml()


fun String.command(adapt: String.() -> String): String = if (startsWith("/")) {
    adapt()
} else {
    "/${adapt()}"
}

fun String.commandMarkdown(): String = command(String::toMarkdown)
fun String.commandHTML(): String = command(String::toHtml)


fun String.regularMarkdown(): String = toMarkdown()
fun String.regularHtml(): String = toHtml()


infix fun String.bold(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> boldHTML()
    is Markdown -> boldMarkdown()
}


infix fun String.italic(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> italicHTML()
    is Markdown -> italicMarkdown()
}

infix fun String.hashTag(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> hashTagHTML()
    is Markdown -> hashTagMarkdown()
}

infix fun String.code(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> codeHTML()
    is Markdown -> codeMarkdown()
}

infix fun String.pre(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> preHTML()
    is Markdown -> preMarkdown()
}

fun String.pre(parseMode: ParseMode, language: String? = null): String = when (parseMode) {
    is HTML -> preHTML(language)
    is Markdown -> preMarkdown(language)
}

infix fun String.email(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> emailHTML()
    is Markdown -> emailMarkdown()
}

infix fun Pair<String, String>.link(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> first.linkHTML(second)
    is Markdown -> first.linkMarkdown(second)
}

infix fun String.mention(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> mentionHTML()
    is Markdown -> mentionMarkdown()
}

infix fun Pair<String, ChatId>.mention(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> first.mentionHTML(second)
    is Markdown -> first.mentionMarkdown(second)
}

infix fun String.phone(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> phoneHTML()
    is Markdown -> phoneMarkdown()
}

infix fun String.command(parseMode: ParseMode): String = when (parseMode) {
    is HTML -> commandHTML()
    is Markdown -> commandMarkdown()
}
