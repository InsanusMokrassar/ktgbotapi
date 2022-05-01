package dev.inmo.tgbotapi.extensions.utils.formatting

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.content.TextContent

fun createFormattedText(
    entities: TextSourcesList,
    partLength: Int = textLength.last,
    mode: ParseMode = defaultParseMode
): List<String> {
    val texts = mutableListOf<String>()
    val textBuilder = StringBuilder(partLength)
    for (entity in entities) {
        val string = when (mode) {
            is Markdown -> entity.markdown
            is MarkdownV2 -> entity.markdownV2
            is HTML -> entity.html
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
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, Markdown)

fun TextSourcesList.toMarkdownCaptions(): List<String> = createMarkdownText(
    this,
    captionLength.last
)

fun TextSourcesList.toMarkdownTexts(): List<String> = createMarkdownText(
    this,
    textLength.last
)

fun TextContent.toMarkdownTexts(): List<String> = textSources.toMarkdownTexts()

fun TextSourcesList.toMarkdownExplanations(): List<String> = createMarkdownText(
    this,
    explanationLimit.last
)


fun createMarkdownV2Text(
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, MarkdownV2)

fun TextSourcesList.toMarkdownV2Captions(): List<String> = createMarkdownV2Text(
    this,
    captionLength.last
)

fun TextSourcesList.toMarkdownV2Texts(): List<String> = createMarkdownV2Text(
    this,
    textLength.last
)

fun TextContent.toMarkdownV2Texts(): List<String> = textSources.toMarkdownV2Texts()

fun TextSourcesList.toMarkdownV2Explanations(): List<String> = createMarkdownV2Text(
    this,
    explanationLimit.last
)


fun createHtmlText(
    entities: TextSourcesList,
    partLength: Int = textLength.last
): List<String> = createFormattedText(entities, partLength, HTML)

fun TextSourcesList.toHtmlCaptions(): List<String> = createHtmlText(
    this,
    captionLength.last
)

fun TextSourcesList.toHtmlTexts(): List<String> = createHtmlText(
    this,
    textLength.last
)

fun TextContent.toHtmlTexts(): List<String> = textSources.toHtmlTexts()

fun TextSourcesList.toHtmlExplanations(): List<String> = createHtmlText(
    this,
    explanationLimit.last
)


