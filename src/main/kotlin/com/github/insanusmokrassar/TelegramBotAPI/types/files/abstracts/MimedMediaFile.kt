package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

internal const val mimeTypeField = "mime_type"

interface MimedMediaFile : TelegramMediaFile {
    val mimeType: String? // TODO::replace by something like enum or interface
}