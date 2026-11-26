package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.types.rich.RichText
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmName

/**
 * A quoted selection in a message. [text] may be empty for a rich-message selection.
 * [richTexts] contains the selected rich texts in source order. [position] counts UTF-16 code
 * units in the original message, joining quotable rich texts with line feeds.
 */
@ConsistentCopyVisibility
@Serializable
data class TextQuote private constructor(
    @SerialName(textField)
    override val text: String,
    @SerialName(positionField)
    val position: Int,
    @SerialName(entitiesField)
    private val entities: RawMessageEntities? = null,
    @SerialName(isManualField)
    val isManual: Boolean = false,
    @SerialName(richTextsField)
    val richTexts: List<RichText>? = null
) : TextedInput {
    override val textSources: List<TextSource> by lazy {
        (entities ?: emptyList()).asTextSources(text)
    }

    companion object {
        @JvmName("PublicConstructor")
        operator fun invoke(
            text: String,
            position: Int,
            textSources: List<TextSource> = emptyList(),
            isManual: Boolean = false,
            richTexts: List<RichText>? = null
        )  = TextQuote(
            text, position, textSources.toRawMessageEntities(), isManual, richTexts
        )
    }
}
