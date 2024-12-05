package dev.inmo.tgbotapi.types.InlineQueries.prepared

import dev.inmo.tgbotapi.types.PreparedMessageId
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.expirationDateField
import dev.inmo.tgbotapi.types.idField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreparedInlineMessage(
    @SerialName(idField)
    val id: PreparedMessageId,
    @SerialName(expirationDateField)
    val expirationDate: TelegramDate,
)
