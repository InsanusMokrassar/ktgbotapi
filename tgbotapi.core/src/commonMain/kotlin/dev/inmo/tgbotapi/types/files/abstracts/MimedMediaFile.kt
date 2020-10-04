package dev.inmo.tgbotapi.types.files.abstracts

import dev.inmo.tgbotapi.CommonAbstracts.MimeTyped

internal const val mimeTypeField = "mime_type"

interface MimedMediaFile : TelegramMediaFile, MimeTyped