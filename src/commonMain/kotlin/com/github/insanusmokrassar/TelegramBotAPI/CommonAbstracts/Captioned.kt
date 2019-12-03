package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.MessageEntity
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode

interface Captioned {
    val caption: String?
}

interface CaptionedOutput : Captioned {
    val parseMode: ParseMode?
}

interface CaptionedInput : Captioned {
    val captionEntities: List<MessageEntity>
}
