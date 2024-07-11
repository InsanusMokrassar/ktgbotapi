package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.InputFile
import kotlinx.serialization.Serializable

@Serializable(TelegramMediaSerializer::class)
sealed interface TelegramPaidMedia : TelegramMedia

@Serializable(TelegramMediaSerializer::class)
sealed interface VisualTelegramPaidMedia : TelegramPaidMedia
