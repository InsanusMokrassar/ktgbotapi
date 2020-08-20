package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

interface SizedMediaFile : TelegramMediaFile {
    val width: Int
    val height: Int
}