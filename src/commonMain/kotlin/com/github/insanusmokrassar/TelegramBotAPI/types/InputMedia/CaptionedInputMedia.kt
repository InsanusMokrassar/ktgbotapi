package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode

interface CaptionedInputMedia: InputMedia {
    val caption: String?
    val parseMode: ParseMode?
}