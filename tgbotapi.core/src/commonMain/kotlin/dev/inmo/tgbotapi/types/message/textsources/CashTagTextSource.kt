package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see cashTagTextSource
 */
@Serializable
data class CashTagTextSource @RiskFeature(DirectInvocationOfTextSourceConstructor) constructor (
    override val source: String,
    override val subsources: TextSourcesList
) : MultilevelTextSource {
    val username: Username? by lazy {
        val potentialUsername = source.dropWhile { it != '@' }
        if (potentialUsername.isEmpty()) return@lazy null

        Username.prepare(potentialUsername)
    }
    override val markdown: String by lazy { source.cashTagMarkdown() }
    override val markdownV2: String by lazy { cashTagMarkdownV2() }
    override val html: String by lazy { cashTagHTML() }
}

inline fun cashTagTextSource(parts: TextSourcesList) = CashTagTextSource(parts.makeString(), parts)
inline fun cashTagTextSource(vararg parts: TextSource) = cashTagTextSource(parts.toList())
inline fun cashTagTextSource(tag: String) = cashTagTextSource(regularTextSource(tag))
