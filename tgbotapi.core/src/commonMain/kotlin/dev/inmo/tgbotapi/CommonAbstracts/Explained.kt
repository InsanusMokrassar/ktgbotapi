package dev.inmo.tgbotapi.CommonAbstracts

@Deprecated("Will be removed soon")
interface Explained : Texted {
    val explanation: String?
        get() = text
}

@Deprecated("Will be removed soon")
interface ParsableExplainedOutput : Explained, TextedOutput

@Deprecated("Will be removed soon")
interface EntitiesExplainedOutput : Explained, EntitiesOutput

@Deprecated("Will be removed soon")
interface ExplainedOutput : ParsableExplainedOutput, EntitiesExplainedOutput

@Deprecated("Will be removed soon")
interface ExplainedInput : Explained {
    val textSources: TextSourcesList
    /**
     * Full list of entities. This list WILL contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     */
    val explanationEntities: List<TextPart>
        get() = textSources.toTextParts()
}
