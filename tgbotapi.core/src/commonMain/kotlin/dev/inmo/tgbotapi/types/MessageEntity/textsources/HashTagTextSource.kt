package dev.inmo.tgbotapi.types.MessageEntity.textsources

import dev.inmo.tgbotapi.CommonAbstracts.*
import dev.inmo.tgbotapi.utils.*

private val String.withoutSharp
    get() = if (startsWith("#")){
        substring(1)
    } else {
        this
    }

class HashTagTextSource(
    override val source: String,
    override val textSources: List<TextSource>
) : MultilevelTextSource {
    override val asMarkdownSource: String by lazy { source.hashTagMarkdown() }
    override val asMarkdownV2Source: String by lazy { hashTagMarkdownV2() }
    override val asHtmlSource: String by lazy { hashTagHTML() }
}
