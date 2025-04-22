package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.textsources.TextSource

interface Texted {
    val text: String?
}

interface TextedWithTextSources : Texted {
    /**
     * Full list of [TextSource]s
     */
    val textSources: List<TextSource>?
}

interface ParsableOutput : Texted {
    val parseMode: ParseMode?
}

interface EntitiesOutput : TextedWithTextSources {
    val entities: List<TextSource>?
        get() = textSources
}

interface TextedOutput : ParsableOutput, EntitiesOutput

interface TextedInput : TextedWithTextSources {
    override val textSources: List<TextSource>
}
