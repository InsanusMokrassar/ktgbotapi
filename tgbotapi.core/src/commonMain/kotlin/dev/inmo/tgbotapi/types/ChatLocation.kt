package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.location.StaticLocation
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a location to which a chat is connected.
 *
 * @see dev.inmo.tgbotapi.requests.chat.get.GetChat
 * @see dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat
 */
@Serializable
data class ChatLocation(
    @SerialName(locationField)
    val location: StaticLocation,
    @SerialName(addressField)
    val address: String
)
