package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.utils.internal.fullListOfSubSource

interface Texted {
    val text: String?
}

interface ParsableOutput : Texted {
    val parseMode: ParseMode?
}

interface EntitiesOutput : Texted {
    val entities: List<TextSource>?
}

interface TextedOutput : ParsableOutput, EntitiesOutput

interface TextedInput : Texted {
    /**
     * Here must be full list of entities. This list must contains [TextPart]s with
     * [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource] in case if source text contains parts of
     * regular text
     * @see [CaptionedInput.fullEntitiesList]
     */
    val textEntities: List<TextPart>
}

/**
 * Full list of [TextSource] built from source[TextedInput.textEntities]
 *
 * @see TextedInput.textEntities
 * @see justTextSources
 */
val TextedInput.textSources
    get() = textEntities.justTextSources()

/**
 * Convert its [TextedInput.textEntities] to list of [dev.inmo.tgbotapi.CommonAbstracts.TextSource]
 * with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
 */
internal fun TextedInput.fullEntitiesList(): TextSourcesList = text ?.fullListOfSubSource(textEntities) ?.map { it.source } ?: emptyList()
