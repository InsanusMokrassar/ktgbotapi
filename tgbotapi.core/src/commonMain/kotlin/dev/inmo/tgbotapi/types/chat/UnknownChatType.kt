package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.ChatId
import kotlinx.serialization.json.JsonObject

data class UnknownChatType(
    override val id: ChatId,
    val raw: String,
    val rawJson: JsonObject
) : Chat
