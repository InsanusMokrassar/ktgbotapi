package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.EphemeralMessageParameters

/**
 * [ephemeralMessageParameters] selects a user in a group or an ephemeral reply to a callback query.
 * Callback-only replies work outside channel direct messages chats and can omit the destination chat ID.
 */
interface OptionallyEphemeralSendRequest {
    val ephemeralMessageParameters: EphemeralMessageParameters?
}
