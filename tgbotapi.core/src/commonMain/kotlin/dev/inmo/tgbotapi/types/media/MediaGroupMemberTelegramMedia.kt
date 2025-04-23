package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.abstracts.TextedOutput
import kotlinx.serialization.Serializable
import kotlinx.serialization.StringFormat

@Serializable(MediaGroupMemberTelegramMediaSerializer::class)
sealed interface MediaGroupMemberTelegramMedia : TelegramMedia, TextedOutput {
    fun serialize(format: StringFormat): String
}

sealed interface AudioMediaGroupMemberTelegramMedia : MediaGroupMemberTelegramMedia

sealed interface DocumentMediaGroupMemberTelegramMedia : MediaGroupMemberTelegramMedia

@Serializable(MediaGroupMemberTelegramMediaSerializer::class)
sealed interface VisualMediaGroupMemberTelegramMedia : MediaGroupMemberTelegramMedia, SpoilerableTelegramMedia, WithCustomizableCaptionTelegramMedia
