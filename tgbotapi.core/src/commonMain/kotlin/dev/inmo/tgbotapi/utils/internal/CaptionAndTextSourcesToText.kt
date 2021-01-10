package dev.inmo.tgbotapi.utils.internal

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.ParseMode.*
import dev.inmo.tgbotapi.types.message.content.TextContent

internal fun createFormattedText(
    entities: TextSourcesList,
    partLength: Int = textLength.last,
    mode: ParseMode = MarkdownParseMode
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = when (mode) {
            is MarkdownParseMode -> entity.markdown
            is MarkdownV2ParseMode -> entity.markdownV2
            is HTMLParseMode -> entity.html
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
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownParseMode)

internal fun TextSourcesList.toMarkdownTexts(): List<String> = createMarkdownText(
    this,
    textLength.last
)
internal fun TextContent.toMarkdownTexts(): List<String> = textSources.toMarkdownTexts()


internal fun createMarkdownV2Text(
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownV2ParseMode)

internal fun TextSourcesList.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    this,
    captionLength.last
)
internal fun CaptionedInput.toMarkdownV2Captions(): List<String> = textSources.toMarkdownV2Captions()

internal fun TextSourcesList.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    this,
    textLength.last
)
internal fun TextContent.toMarkdownV2Texts(): List<String> = textSources.toMarkdownV2Texts()


internal fun createHtmlText(
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, HTMLParseMode)

internal fun TextSourcesList.toHtmlCaptions(): List<String> = createHtmlText(
    this,
    captionLength.last
)
internal fun CaptionedInput.toHtmlCaptions(): List<String> = textSources.toHtmlCaptions()

internal fun TextSourcesList.toHtmlTexts(): List<String> = createHtmlText(
    this,
    textLength.last
)
internal fun TextContent.toHtmlTexts(): List<String> = textSources.toHtmlTexts()


