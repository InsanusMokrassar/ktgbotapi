package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.types.message.RawMessageEntities
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmName

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
    val isManual: Boolean = false
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
            isManual: Boolean = false
        )  = TextQuote(
            text, position, textSources.toRawMessageEntities(position), isManual
        )
    }
}