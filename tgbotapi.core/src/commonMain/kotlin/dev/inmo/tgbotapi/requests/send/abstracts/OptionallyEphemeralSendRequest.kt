package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.UserId

/**
 * Inheritors of this interface may be sent as ephemeral messages (visible only to [receiverUserId] and the bot)
 * by passing a non-null [receiverUserId]. Available for groups/supergroups only.
 */
interface OptionallyEphemeralSendRequest {
    val receiverUserId: UserId?
    val callbackQueryId: CallbackQueryId?
}
