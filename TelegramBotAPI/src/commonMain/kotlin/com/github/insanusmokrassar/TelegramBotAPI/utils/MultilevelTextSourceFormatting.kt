package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MultilevelTextSource
import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextPart
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource
import com.github.insanusmokrassar.TelegramBotAPI.types.UserId
import com.github.insanusmokrassar.TelegramBotAPI.types.link
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.escapeMarkdownV2Link
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.toHtml

internal fun String.fullListOfSubSource(sourceList: List<TextPart>): List<TextPart> {
    val sortedSourceList = sourceList.sortedBy { it.range.first }.toMutableList()

    var previousLastIndex = 0

    val newSubSources = mutableListOf<TextPart>()

    while (sortedSourceList.isNotEmpty()) {
        val topSource = sortedSourceList.removeAt(0)
        if (topSource.range.first - previousLastIndex > 0) {
            val range = previousLastIndex until topSource.range.first
            newSubSources.add(
                TextPart(
                    range,
                    RegularTextSource(
                        substring(range)
                    )
                )
            )
        }
        newSubSources.add(topSource)
        previousLastIndex = topSource.range.last + 1
    }

    if (length > previousLastIndex) {
        val range = previousLastIndex until length
        newSubSources.add(
            TextPart(
                range,
                RegularTextSource(
                    substring(range)
                )
            )
        )
    }

    return newSubSources
}

internal fun List<TextPart>.shiftSourcesToTheLeft(shiftCount: Int = 1): List<TextPart> {
    return mapNotNull {
        val first = (it.range.first - shiftCount).let { firstCalculated ->
            if (firstCalculated < 0) {
                0
            } else {
                firstCalculated
            }
        }
        val last = (it.range.last - shiftCount).let { lastCalculated ->
            if (lastCalculated < 0) {
                0
            } else {
                lastCalculated
            }
        }
        it.copy(range = first .. last).let { newSubSource ->
            if (newSubSource.range.isEmpty()) {
                null
            } else {
                newSubSource
            }
        }
    }
}

private fun List<TextPart>.joinSubSourcesMarkdownV2() = joinToString("") {
    it.source.asMarkdownV2Source
}

private fun List<TextPart>.joinSubSourcesHtml() = joinToString("") {
    it.source.asHtmlSource
}

internal fun MultilevelTextSource.markdownV2Default(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "$openControlSymbol${textParts.joinSubSourcesMarkdownV2()}$closeControlSymbol"
internal fun MultilevelTextSource.htmlDefault(
    openControlSymbol: String,
    closeControlSymbol: String = openControlSymbol
) = "<$openControlSymbol>${textParts.joinSubSourcesHtml()}</$closeControlSymbol>"


internal fun MultilevelTextSource.linkMarkdownV2(
    link: String
) = "[${textParts.joinSubSourcesMarkdownV2()}](${link.escapeMarkdownV2Link()})"
internal fun MultilevelTextSource.linkHTML(
    link: String
) = "<a href=\"${link.toHtml()}\">${textParts.joinSubSourcesHtml()}</a>"


internal fun MultilevelTextSource.emailMarkdownV2(address: String): String = linkMarkdownV2("mailto://$address")
internal fun MultilevelTextSource.emailHTML(address: String): String = linkHTML("mailto://$address}")


internal fun MultilevelTextSource.boldMarkdownV2(): String = markdownV2Default(markdownBoldControl)
internal fun MultilevelTextSource.boldHTML(): String = htmlDefault(htmlBoldControl)


internal fun MultilevelTextSource.cashTagMarkdownV2(): String = textParts.joinSubSourcesMarkdownV2()
internal fun MultilevelTextSource.cashTagHTML(): String = textParts.joinSubSourcesHtml()


internal fun MultilevelTextSource.italicMarkdownV2(): String = markdownV2Default(markdownItalicControl)
internal fun MultilevelTextSource.italicHTML(): String = htmlDefault(htmlItalicControl)


internal fun MultilevelTextSource.strikethroughMarkdownV2(): String = markdownV2Default(markdownV2StrikethroughControl)
internal fun MultilevelTextSource.strikethroughHTML(): String = htmlDefault(htmlStrikethroughControl)


internal fun MultilevelTextSource.underlineMarkdownV2(): String = markdownV2Default(markdownV2UnderlineControl)
internal fun MultilevelTextSource.underlineHTML(): String = htmlDefault(htmlUnderlineControl)


internal fun MultilevelTextSource.textMentionMarkdownV2(userId: UserId): String = linkMarkdownV2(userId.link)
internal fun MultilevelTextSource.textMentionHTML(userId: UserId): String = linkHTML(userId.link)

internal fun MultilevelTextSource.mentionMarkdownV2(): String = "@${textParts.joinSubSourcesMarkdownV2()}"
internal fun MultilevelTextSource.mentionHTML(): String = "@${textParts.joinSubSourcesHtml()}"


internal fun MultilevelTextSource.hashTagMarkdownV2(): String = "\\#${textParts.joinSubSourcesMarkdownV2()}"
internal fun MultilevelTextSource.hashTagHTML(): String = "#${textParts.joinSubSourcesHtml()}"


internal fun MultilevelTextSource.phoneMarkdownV2(): String = textParts.joinSubSourcesMarkdownV2()
internal fun MultilevelTextSource.phoneHTML(): String = textParts.joinSubSourcesHtml()


internal fun MultilevelTextSource.commandMarkdownV2(): String = "/${textParts.joinSubSourcesMarkdownV2()}"
internal fun MultilevelTextSource.commandHTML(): String = "/${textParts.joinSubSourcesHtml()}"

