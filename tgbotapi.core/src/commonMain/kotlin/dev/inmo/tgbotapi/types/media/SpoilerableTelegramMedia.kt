package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.SpoilerableData

sealed interface SpoilerableTelegramMedia : TelegramMedia, SpoilerableData
