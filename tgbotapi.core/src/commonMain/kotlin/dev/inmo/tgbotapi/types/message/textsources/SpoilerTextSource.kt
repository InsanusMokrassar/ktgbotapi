package dev.inmo.tgbotapi.types.message.textsources

import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.internal.*
import kotlinx.serialization.Serializable

/**
 * @see italicTextSource
 */
@Serializable
data class SpoilerTextSource
    @RiskFeature(DirectInvocationOfTextSourceConstructor)
    constructor(
        override val source: String,
        override val subsources: TextSourcesList,
    ) : MultilevelTextSource {
        override val markdown: String by lazy { source.spoilerMarkdown() }
        override val markdownV2: String by lazy { spoilerMarkdownV2() }
        override val html: String by lazy { spoilerHTML() }
    }

inline fun spoilerTextSource(parts: TextSourcesList) = SpoilerTextSource(parts.makeString(), parts)

inline fun spoilerTextSource(vararg parts: TextSource) = spoilerTextSource(parts.toList())

inline fun spoilerTextSource(text: String) = spoilerTextSource(regularTextSource(text))
