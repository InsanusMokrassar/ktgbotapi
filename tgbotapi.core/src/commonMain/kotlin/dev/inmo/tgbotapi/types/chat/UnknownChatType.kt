package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.IdChatIdentifier
import kotlinx.serialization.json.JsonObject

data class UnknownChatType(
    override val id: IdChatIdentifier,
    val raw: String,
    val rawJson: JsonObject
) : Chat, PreviewChat
