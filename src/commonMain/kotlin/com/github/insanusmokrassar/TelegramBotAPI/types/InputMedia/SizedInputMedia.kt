package com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia

interface SizedInputMedia : InputMedia {
    val width: Int?
    val height: Int?
}