package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode

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
