package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.abstracts.MimeTyped

sealed interface MimedMediaFile : TelegramMediaFile, MimeTyped
