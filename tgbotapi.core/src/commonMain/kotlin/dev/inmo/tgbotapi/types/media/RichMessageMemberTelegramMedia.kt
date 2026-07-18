package dev.inmo.tgbotapi.types.media

import kotlinx.serialization.Serializable

@Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
@Serializable(TelegramMediaSerializer::class)
sealed interface RichMessageMemberTelegramMedia : TelegramMedia
