package dev.inmo.tgbotapi.types.message.abstracts

import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.chat.PreviewUser

/** Content messages that can carry Telegram's ephemeral recipient and message identifier. */
interface PossiblyEphemeralMessage : Message {
    /** The user who received the ephemeral message. */
    val receiverUser: PreviewUser?
        get() = null

    /** Identifies the ephemeral message in its chat. Telegram uses zero for the ordinary [messageId]. */
    val ephemeralMessageId: EphemeralMessageId?
        get() = null
}
