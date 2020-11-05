package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.utils.fullListOfSubSource

interface Explained {
    val explanation: String?
}

interface ParsableExplainedOutput : Explained {
    val parseMode: ParseMode?
}

interface EntitiesExplainedOutput : Explained {
    val entities: List<TextSource>?
}

interface ExplainedOutput : ParsableExplainedOutput, EntitiesExplainedOutput

interface ExplainedInput : Explained {
    /**
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     * @see [ExplainedInput.fullEntitiesList]
     */
    val explanationEntities: List<TextPart>
}

/**
 * Convert its [ExplainedInput.explanationEntities] to list of [dev.inmo.tgbotapi.CommonAbstracts.TextSource]
 * with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
 */
fun ExplainedInput.fullEntitiesList(): FullTextSourcesList = explanation ?.fullListOfSubSource(explanationEntities) ?.map { it.source } ?: emptyList()
