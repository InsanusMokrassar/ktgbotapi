package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.utils.fullListOfSubSource

interface Explained {
    val explanation: String?
}

interface ExplainedOutput : Explained {
    val parseMode: ParseMode?
}

interface ExplainedInput : Explained {
    /**
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
     * @see [ExplainedInput.fullEntitiesList]
     */
    val explanationEntities: List<TextPart>
}

/**
 * Convert its [ExplainedInput.explanationEntities] to list of [com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource]
 * with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
 */
fun ExplainedInput.fullEntitiesList(): FullTextSourcesList = explanation ?.fullListOfSubSource(explanationEntities) ?.map { it.source } ?: emptyList()
