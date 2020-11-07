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
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     * @see [CaptionedInput.fullEntitiesList]
     */
    val textEntities: List<TextPart>
}

/**
 * @see TextedInput.textEntities
 * @see justTextSources
 */
val TextedInput.textSources
    get() = textEntities.justTextSources()

/**
 * Convert its [TextedInput.textEntities] to list of [dev.inmo.tgbotapi.CommonAbstracts.TextSource]
 * with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
 */
@Deprecated("Currently list of entities already full. This method is redundant")
fun TextedInput.fullEntitiesList(): TextSourcesList = text ?.fullListOfSubSource(textEntities) ?.map { it.source } ?: emptyList()
