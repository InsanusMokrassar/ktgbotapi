package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(TelegramMediaSerializer::class)
sealed interface TelegramFreeMedia : TelegramMedia
