package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode

interface Captioned {
    val caption: String?
}

interface CaptionedOutput : Captioned {
    val parseMode: ParseMode?
}

interface CaptionedInput : Captioned {
    /**
     * Full list of entities. This list WILL contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     */
    val captionEntities: List<TextPart>
}

/**
 * @see CaptionedInput.captionEntities
 * @see justTextSources
 */
val CaptionedInput.textSources
    get() = captionEntities.justTextSources()
