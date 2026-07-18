package dev.inmo.tgbotapi.abstracts.types

import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.UserId

interface EphemeralMessageAction : ChatRequest {
    val receiverUserId: UserId
    val ephemeralMessageId: EphemeralMessageId
}
