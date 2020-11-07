package dev.inmo.tgbotapi.types.message.ChatEvents

import dev.inmo.tgbotapi.types.Meters
import dev.inmo.tgbotapi.types.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.Serializable

/**
 * This object represents the content of a service message, sent whenever a user in the chat triggers a proximity alert
 * set by another user.
 */
@Serializable
data class ProximityAlertTriggered(
    val traveler: User,
    val watcher: User,
    val distance: Meters
) : CommonEvent
