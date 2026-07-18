package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.chat.PreviewUser

interface PossiblyEphemeralMessage : Message {
    val receiverUser: PreviewUser?
    val ephemeralMessageId: EphemeralMessageId?
}
