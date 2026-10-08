package dev.inmo.tgbotapi.requests.send.abstracts

import dev.inmo.tgbotapi.types.EphemeralMessageParameters

/**
 * Inheritors of this interface may be sent as ephemeral messages by passing [ephemeralMessageParameters].
 * Ephemeral messages are available for groups/supergroups only.
 */
interface OptionallyEphemeralSendRequest {
    val ephemeralMessageParameters: EphemeralMessageParameters?
}
