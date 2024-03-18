package dev.inmo.tgbotapi.types.webapps.query

import dev.inmo.tgbotapi.types.InlineMessageId
import kotlinx.serialization.Serializable

@Serializable
data class SentWebAppMessage(
    val inlineMessageId: InlineMessageId? = null
)
