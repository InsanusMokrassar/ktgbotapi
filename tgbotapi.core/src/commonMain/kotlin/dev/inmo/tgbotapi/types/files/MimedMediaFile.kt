package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.CommonAbstracts.MimeTyped
import dev.inmo.tgbotapi.types.files.TelegramMediaFile

sealed interface MimedMediaFile : TelegramMediaFile, MimeTyped
