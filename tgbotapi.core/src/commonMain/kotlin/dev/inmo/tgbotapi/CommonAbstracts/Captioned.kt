package dev.inmo.tgbotapi.CommonAbstracts

import dev.inmo.tgbotapi.types.ParseMode.ParseMode

const val CaptionDeprecation = "Captioned interface and others will be removed soon and not recommended to use"

@Deprecated(CaptionDeprecation)
interface Captioned : Texted {
    @Deprecated(CaptionDeprecation)
    val caption: String?
        get() = text
}

@Deprecated(CaptionDeprecation)
interface CaptionedInput : Captioned, TextedInput {
    /**
     * Full list of entities. This list WILL contain [TextPart]s with [dev.inmo.tgbotapi.types.MessageEntity.textsources.RegularTextSource]
     */
    @Deprecated(CaptionDeprecation)
    val captionEntities: List<TextPart>
        get() = textEntities
}
