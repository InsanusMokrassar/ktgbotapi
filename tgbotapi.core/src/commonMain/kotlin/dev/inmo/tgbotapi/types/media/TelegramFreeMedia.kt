package dev.inmo.tgbotapi.types.media

import kotlinx.serialization.Serializable

@Serializable(TelegramMediaSerializer::class)
sealed interface TelegramFreeMedia : TelegramMedia
