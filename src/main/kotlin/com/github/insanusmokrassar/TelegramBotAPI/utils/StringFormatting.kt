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

private infix fun String.markdownDefault(controlSymbol: String) = "$controlSymbol$this$controlSymbol"
private infix fun String.htmlDefault(controlSymbol: String) = "<$controlSymbol>$this</$controlSymbol>"

infix fun String.linkMarkdown(link: String): String = "[$this]($link)"
infix fun String.linkHTML(link: String): String = "<a href=\"$link\">$this</a>"


fun String.boldMarkdown(): String = markdownDefault(markdownBoldControl)
fun String.boldHTML(): String = htmlDefault(htmlBoldControl)


fun String.italicMarkdown(): String = markdownDefault(markdownItalicControl)
fun String.italicHTML(): String =htmlDefault(htmlItalicControl)


fun String.codeMarkdown(): String = markdownDefault(markdownCodeControl)
fun String.codeHTML(): String = htmlDefault(htmlCodeControl)


fun String.preMarkdown(): String = markdownDefault(markdownPreControl)
fun String.preHTML(): String = htmlDefault(htmlPreControl)


fun String.emailMarkdown(): String = linkMarkdown("mailto://$this")
fun String.emailHTML(): String = linkHTML("mailto://$this")


private inline infix fun String.mention(adapt: String.() -> String): String = if (startsWith("@")) {
    this
} else {
    "@${adapt()}"
}


private inline infix fun String.hashTag(adapt: String.() -> String): String = if (startsWith("#")) {
    this
} else {
    "#${adapt()}"
}


infix fun String.mentionMarkdown(userId: UserId): String = linkMarkdown(userId.link)
infix fun String.mentionHTML(userId: UserId): String = linkHTML(userId.link)


fun String.mentionMarkdown(): String = mention(String::toMarkdown)
fun String.mentionHTML(): String = mention(String::toHtml)


fun String.hashTagMarkdown(): String = hashTag(String::toMarkdown)
fun String.hashTagHTML(): String = hashTag(String::toHtml)


fun String.phoneMarkdown(): String = toMarkdown()
fun String.phoneHTML(): String = toHtml()


fun String.command(): String = if (startsWith("/")) {
    this
} else {
    "/$this"
}


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

infix fun String.command(parseMode: ParseMode): String = command()
