package dev.inmo.tgbotapi.utils.internal

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.MultilevelTextSource
import dev.inmo.tgbotapi.utils.extensions.*

internal fun MultilevelTextSource.markdownV2Default(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol,
    eachLineSeparator: String? = null
) = "$openControlSymbol${subsources.makeMarkdownV2String(eachLineSeparator)}$closeControlSymbol"
internal fun MultilevelTextSource.htmlDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "<$openControlSymbol>${subsources.makeHtmlString()}</$closeControlSymbol>"


internal fun MultilevelTextSource.linkMarkdownV2(
    link: String
) = "[${subsources.makeMarkdownV2String()}](${link.escapeMarkdownV2Link()})"
internal fun MultilevelTextSource.linkHTML(
    link: String
) = "<a href=\"${link.toHtml()}\">${subsources.makeHtmlString()}</a>"


internal fun MultilevelTextSource.optionalPrefix(
    mustStartsWith: String,
    controlWord: String = mustStartsWith
) = if (source.startsWith(mustStartsWith)) {
    ""
} else {
    controlWord
}


internal fun MultilevelTextSource.emailMarkdownV2(address: String): String = linkMarkdownV2("mailto://$address")
internal fun MultilevelTextSource.emailHTML(address: String): String = linkHTML("mailto://$address}")


internal fun MultilevelTextSource.boldMarkdownV2(): String = markdownV2Default(markdownBoldControl)
internal fun MultilevelTextSource.boldHTML(): String = htmlDefault(htmlBoldControl)


internal fun MultilevelTextSource.blockquoteMarkdownV2(): String = markdownV2Default("", eachLineSeparator = markdownBlockquoteControl)
internal fun MultilevelTextSource.blockquoteHTML(): String = htmlDefault(htmlBlockquoteControl)


internal fun MultilevelTextSource.expandableBlockquoteMarkdownV2(): String = markdownV2Default(
    openControlSymbol = markdownV2ExpandableBlockquoteOpenControl,
    closeControlSymbol = markdownV2ExpandableBlockquoteCloseControl,
    eachLineSeparator = markdownBlockquoteControl
)
internal fun MultilevelTextSource.expandableBlockquoteHTML(): String = htmlDefault(
    openControlSymbol = htmlBlockquoteOpenControl,
    closeControlSymbol = htmlBlockquoteControl
)


internal fun MultilevelTextSource.cashTagMarkdownV2(): String = subsources.makeMarkdownV2String()
internal fun MultilevelTextSource.cashTagHTML(): String = subsources.makeHtmlString()


internal fun MultilevelTextSource.italicMarkdownV2(): String = markdownV2Default(markdownItalicControl)
internal fun MultilevelTextSource.italicHTML(): String = htmlDefault(htmlItalicControl)


internal fun MultilevelTextSource.spoilerMarkdownV2(): String = markdownV2Default(markdownSpoilerControl)
internal fun MultilevelTextSource.spoilerHTML(): String = htmlDefault(htmlSpoilerControl, htmlSpoilerClosingControl)


internal fun MultilevelTextSource.strikethroughMarkdownV2(): String = markdownV2Default(markdownV2StrikethroughControl)
internal fun MultilevelTextSource.strikethroughHTML(): String = htmlDefault(htmlStrikethroughControl)


internal fun MultilevelTextSource.underlineMarkdownV2(): String = markdownV2Default(markdownV2UnderlineControl)
internal fun MultilevelTextSource.underlineHTML(): String = htmlDefault(htmlUnderlineControl)


internal fun MultilevelTextSource.textMentionMarkdownV2(userId: UserId): String = linkMarkdownV2(userId.userLink)
internal fun MultilevelTextSource.textMentionHTML(userId: UserId): String = linkHTML(userId.userLink)

internal fun MultilevelTextSource.mentionMarkdownV2(): String = optionalPrefix("@") + subsources.makeMarkdownV2String()
internal fun MultilevelTextSource.mentionHTML(): String = optionalPrefix("@") + subsources.makeHtmlString()


internal fun MultilevelTextSource.hashTagMarkdownV2(): String = when {
    source.startsWith("\\#") || source.startsWith("#") -> ""
    else -> "\\#"
} + subsources.makeMarkdownV2String()
internal fun MultilevelTextSource.hashTagHTML(): String = optionalPrefix("#") + subsources.makeHtmlString()


internal fun MultilevelTextSource.phoneMarkdownV2(): String = subsources.makeMarkdownV2String()
internal fun MultilevelTextSource.phoneHTML(): String = subsources.makeHtmlString()
