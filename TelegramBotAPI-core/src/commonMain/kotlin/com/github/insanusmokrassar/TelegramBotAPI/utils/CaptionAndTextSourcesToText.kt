package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList

internal fun createFormattedText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last,
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


internal fun createMarkdownText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

internal fun FullTextSourcesList.toMarkdownTexts(): List<String> = createMarkdownText(
    this,
    textLength.last
)
internal fun TextContent.toMarkdownTexts(): List<String> = fullEntitiesList().toMarkdownTexts()

internal fun FullTextSourcesList.toMarkdownExplanations(): List<String> = createMarkdownText(
    this,
    explanationLimit.last
)
internal fun ExplainedInput.toMarkdownExplanations(): List<String> = fullEntitiesList().toMarkdownTexts()


internal fun createMarkdownV2Text(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownV2ParseMode)

internal fun FullTextSourcesList.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    this,
    captionLength.last
)
internal fun CaptionedInput.toMarkdownV2Captions(): List<String> = fullEntitiesList().toMarkdownV2Captions()

internal fun FullTextSourcesList.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    this,
    textLength.last
)
internal fun TextContent.toMarkdownV2Texts(): List<String> = fullEntitiesList().toMarkdownV2Texts()

internal fun FullTextSourcesList.toMarkdownV2Explanations(): List<String> = createMarkdownV2Text(
    this,
    explanationLimit.last
)
internal fun ExplainedInput.toMarkdownV2Explanations(): List<String> = fullEntitiesList().toMarkdownV2Texts()


internal fun createHtmlText(
    entities: FullTextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

internal fun FullTextSourcesList.toHtmlCaptions(): List<String> = createHtmlText(
    this,
    captionLength.last
)
internal fun CaptionedInput.toHtmlCaptions(): List<String> = fullEntitiesList().toHtmlCaptions()

internal fun FullTextSourcesList.toHtmlTexts(): List<String> = createHtmlText(
    this,
    textLength.last
)
internal fun TextContent.toHtmlTexts(): List<String> = fullEntitiesList().toHtmlTexts()


