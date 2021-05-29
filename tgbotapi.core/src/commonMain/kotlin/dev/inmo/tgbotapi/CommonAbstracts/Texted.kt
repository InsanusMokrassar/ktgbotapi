package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource
import dev.inmo.tgbotapi.types.ParseMode.ParseMode

interface Texted {
    val text: String?
}
interface TextedWithTextSources : Texted {
    /**
     * Full list of [TextSource] built from source[TextedInput.textEntities]
     */
    val textSources: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>?
}

interface ParsableOutput : Texted {
    val parseMode: ParseMode?
}

interface EntitiesOutput : TextedWithTextSources {
    val entities: List<dev.inmo.tgbotapi.types.MessageEntity.textsources.TextSource>?
        get() = textSources
}

interface TextedOutput : ParsableOutput, EntitiesOutput

interface TextedInput : TextedWithTextSources {
    override val textSources: List<TextSource>
    /**
     * Here must be full list of entities. This list must contains [TextPart]s with
     * [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource] in case if source text contains parts of
     * regular text
     */
    val textEntities: List<TextPart>
        get() = textSources.toTextParts()
}
