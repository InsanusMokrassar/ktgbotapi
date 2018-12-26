package com.github.insanusmokrassar.TelegramBotAPI.types.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode

interface Captioned {
    val caption: String?
    val parseMode: ParseMode?
}