package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a location to which a chat is connected.
 *
 * @see dev.inmo.tgbotapi.requests.chat.get.GetChat
 * @see dev.inmo.tgbotapi.types.chat.abstracts.extended.ExtendedSupergroupChat
 */
@Serializable
data class ChatLocation(
    @SerialName(locationField)
    val location: Location,
    @SerialName(addressField)
    val address: String
)
