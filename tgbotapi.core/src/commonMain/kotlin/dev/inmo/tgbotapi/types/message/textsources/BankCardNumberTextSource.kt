package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.*
import dev.inmo.tgbotapi.utils.internal.regularMarkdown
import kotlinx.serialization.Serializable

/** A Telegram `bank_card_number` message entity, including formatting nested inside the detected text. */
@Serializable
data class BankCardNumberTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor(
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    override val markdown: String get() = source.regularMarkdown()
    override val markdownV2: String get() = subsources.makeMarkdownV2String()
    override val html: String get() = subsources.makeHtmlString()
}

fun bankCardNumberTextSource(parts: TextSourcesList) = BankCardNumberTextSource(parts.makeString(), parts)
fun bankCardNumberTextSource(vararg parts: TextSource) = bankCardNumberTextSource(parts.toList())
fun bankCardNumberTextSource(text: String) = bankCardNumberTextSource(regularTextSource(text))
