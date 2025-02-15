package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.WithCustomStartMediaData
import dev.inmo.tgbotapi.requests.abstracts.FileId

sealed interface WithCustomStartTelegramMedia : TelegramMedia, WithCustomStartMediaData
