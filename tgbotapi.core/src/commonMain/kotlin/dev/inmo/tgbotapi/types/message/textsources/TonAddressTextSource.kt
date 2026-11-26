package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.*
import dev.inmo.tgbotapi.utils.internal.regularMarkdown
import kotlinx.serialization.Serializable

/** A Telegram `ton_address` message entity, including formatting nested inside the detected text. */
@Serializable
data class TonAddressTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor(
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String get() = source.regularMarkdown()
    override val markdownV2: String get() = subsources.makeMarkdownV2String()
    override val html: String get() = subsources.makeHtmlString()
}

fun tonAddressTextSource(parts: TextSourcesList) = TonAddressTextSource(parts.makeString(), parts)
fun tonAddressTextSource(vararg parts: TextSource) = tonAddressTextSource(parts.toList())
fun tonAddressTextSource(text: String) = tonAddressTextSource(regularTextSource(text))
