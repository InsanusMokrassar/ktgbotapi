package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.captionLength
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList
import com.github.insanusmokrassar.TelegramBotAPI.types.textLength

fun createFormattedText(
    entities: List<TextSource>,
    partLength: Int = 4096,
    mode: ParseMode = MarkdownParseMode
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = when (mode) {
            is MarkdownParseMode -> entity.asMarkdownSource
            is MarkdownV2ParseMode -> entity.asMarkdownV2Source
            is HTMLParseMode -> entity.asHtmlSource
        }
        if (textBuilder.length + string.length > partLength) {
            if (textBuilder.isNotEmpty()) {
                texts.add(textBuilder.toString())
                textBuilder.clear()
            }
            val chunked = string.chunked(partLength)
            val last = chunked.last()
            textBuilder.append(last)
            val listToAdd = if (chunked.size > 1) {
                chunked.subList(0, chunked.size - 1)
            } else {
                emptyList()
            }
            listToAdd.forEach {
                texts.add(it)
            }
        } else {
            textBuilder.append(string)
        }
    }
    if (textBuilder.isNotEmpty()) {
        texts.add(textBuilder.toString())
        textBuilder.clear()
    }
    return texts
}


fun createMarkdownText(
    entities: List<TextSource>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

fun CaptionedInput.toMarkdownCaptions(): List<String> = createMarkdownText(
    fullEntitiesList(),
    captionLength.last + 1
)

fun TextContent.toMarkdownTexts(): List<String> = createMarkdownText(
    fullEntitiesList(),
    textLength.last + 1
)


fun createMarkdownV2Text(
    entities: List<TextSource>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, MarkdownV2ParseMode)

fun CaptionedInput.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    fullEntitiesList(),
    captionLength.last + 1
)

fun TextContent.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    fullEntitiesList(),
    textLength.last + 1
)


fun createHtmlText(
    entities: List<TextSource>,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

fun CaptionedInput.toHtmlCaptions(): List<String> = createHtmlText(
    fullEntitiesList(),
    captionLength.last + 1
)

fun TextContent.toHtmlTexts(): List<String> = createHtmlText(
    fullEntitiesList(),
    textLength.last + 1
)


