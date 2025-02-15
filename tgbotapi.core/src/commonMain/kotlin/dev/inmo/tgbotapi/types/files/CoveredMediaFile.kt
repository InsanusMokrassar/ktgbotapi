package dev.inmo.tgbotapi.types.files

import dev.inmo.tgbotapi.abstracts.CoverableData

sealed interface CoveredMediaFile : TelegramMediaFile, CoverableData
