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
    val captionEntities: List<TextPart>
}

fun CaptionedInput.fullEntitiesList(): FullTextSourcesList = caption ?.fullListOfSubSource(captionEntities) ?.map { it.source } ?: emptyList()
