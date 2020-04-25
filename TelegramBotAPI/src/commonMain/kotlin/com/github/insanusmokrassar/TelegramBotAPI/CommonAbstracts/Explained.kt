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
    val explanationEntities: List<TextPart>
}

fun ExplainedInput.fullEntitiesList(): FullTextSourcesList = explanation ?.fullListOfSubSource(explanationEntities) ?.map { it.source } ?: emptyList()
