package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.utils.internal.fullListOfSubSource

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
 * @see CaptionedInput.captionEntities
 * @see justTextSources
 */
val CaptionedInput.textSources
    get() = captionEntities.justTextSources()

/**
 * Convert its [CaptionedInput.captionEntities] to list of [dev.inmo.tgbotapi.CommonAbstracts.TextSource]
 * with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
 */
@Deprecated("Currently list of entities already full. This method is redundant")
fun CaptionedInput.fullEntitiesList(): TextSourcesList = caption ?.fullListOfSubSource(captionEntities) ?.map { it.source } ?: emptyList()
