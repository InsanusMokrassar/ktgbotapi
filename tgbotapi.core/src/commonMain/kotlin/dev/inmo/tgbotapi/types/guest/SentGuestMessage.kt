package dev.inmo.tgbotapi.types.guest

import dev.inmo.tgbotapi.types.InlineMessageId
import dev.inmo.tgbotapi.types.inlineMessageIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SentGuestMessage(
    @SerialName(inlineMessageIdField)
    val inlineMessageId: InlineMessageId
)
