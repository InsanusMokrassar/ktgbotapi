package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.utils.fullListOfSubSource

interface Captioned {
    val caption: String?
}

interface CaptionedOutput : Captioned {
    val parseMode: ParseMode?
}

interface CaptionedInput : Captioned {
    /**
     * Not full list of entities. This list WILL NOT contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     * @see [CaptionedInput.fullEntitiesList]
     */
    val captionEntities: List<TextPart>
}

/**
 * Convert its [CaptionedInput.captionEntities] to list of [dev.inmo.tgbotapi.CommonAbstracts.TextSource]
 * with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
 */
fun CaptionedInput.fullEntitiesList(): TextSourcesList = caption ?.fullListOfSubSource(captionEntities) ?.map { it.source } ?: emptyList()
