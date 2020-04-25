package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

typealias FullTextSourcesList = List<TextSource>
typealias FullTextPartsList = List<TextPart>

interface TextSource {
    val asMarkdownSource: String
    val asMarkdownV2Source: String
    val asHtmlSource: String
    val source: String
}


interface MultilevelTextSource : TextSource {
    val textParts: List<TextPart>
}

data class TextPart(
    val range: IntRange,
    val source: TextSource
)

fun List<TextPart>.justTextSources() = map { it.source }
