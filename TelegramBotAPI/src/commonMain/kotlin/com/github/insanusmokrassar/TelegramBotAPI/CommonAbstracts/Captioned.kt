package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.utils.fullListOfSubSource

interface Captioned {
    val caption: String?
}

interface CaptionedOutput : Captioned {
    val parseMode: ParseMode?
}

interface CaptionedInput : Captioned {
    /**
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
     * @see [CaptionedInput.fullEntitiesList]
     */
    val captionEntities: List<TextPart>
}

/**
 * Convert its [CaptionedInput.captionEntities] to list of [com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.TextSource]
 * with [com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.textsources.RegularTextSource]
 */
fun CaptionedInput.fullEntitiesList(): FullTextSourcesList = caption ?.fullListOfSubSource(captionEntities) ?.map { it.source } ?: emptyList()
