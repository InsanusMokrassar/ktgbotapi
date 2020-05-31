package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.TextContent
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.fullEntitiesList

fun createFormattedText(
    entities: FullTextSourcesList,
    partLength: Int = maxTextLength,
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
    entities: FullTextSourcesList,
    partLength: Int = maxTextLength
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

fun FullTextSourcesList.toMarkdownCaptions(): List<String> = createMarkdownText(
    this,
    captionLength.last + 1
)
fun CaptionedInput.toMarkdownCaptions(): List<String> = fullEntitiesList().toMarkdownCaptions()

fun FullTextSourcesList.toMarkdownTexts(): List<String> = createMarkdownText(
    this,
    maxTextLength
)
fun TextContent.toMarkdownTexts(): List<String> = fullEntitiesList().toMarkdownTexts()

fun FullTextSourcesList.toMarkdownExplanations(): List<String> = createMarkdownText(
    this,
    explanationLimit.last + 1
)
fun ExplainedInput.toMarkdownExplanations(): List<String> = fullEntitiesList().toMarkdownTexts()


fun createMarkdownV2Text(
    entities: FullTextSourcesList,
    partLength: Int = maxTextLength
): List<String> = createFormattedText(entities, partLength, MarkdownV2ParseMode)

fun FullTextSourcesList.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    this,
    captionLength.last + 1
)
fun CaptionedInput.toMarkdownV2Captions(): List<String> = fullEntitiesList().toMarkdownV2Captions()

fun FullTextSourcesList.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    this,
    maxTextLength
)
fun TextContent.toMarkdownV2Texts(): List<String> = fullEntitiesList().toMarkdownV2Texts()

fun FullTextSourcesList.toMarkdownV2Explanations(): List<String> = createMarkdownV2Text(
    this,
    explanationLimit.last + 1
)
fun ExplainedInput.toMarkdownV2Explanations(): List<String> = fullEntitiesList().toMarkdownV2Texts()


fun createHtmlText(
    entities: FullTextSourcesList,
    partLength: Int = 4096
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

fun FullTextSourcesList.toHtmlCaptions(): List<String> = createHtmlText(
    this,
    captionLength.last + 1
)
fun CaptionedInput.toHtmlCaptions(): List<String> = fullEntitiesList().toHtmlCaptions()

fun FullTextSourcesList.toHtmlTexts(): List<String> = createHtmlText(
    this,
    maxTextLength
)
fun TextContent.toHtmlTexts(): List<String> = fullEntitiesList().toHtmlTexts()

fun FullTextSourcesList.toHtmlExplanations(): List<String> = createHtmlText(
    this,
    explanationLimit.last + 1
)
fun ExplainedInput.toHtmlExplanations(): List<String> = fullEntitiesList().toHtmlTexts()


