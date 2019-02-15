package com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.MimeTyped

internal const val mimeTypeField = "mime_type"

interface MimedMediaFile : TelegramMediaFile, MimeTyped