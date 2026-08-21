package dev.inmo.tgbotapi.types.communities

import dev.inmo.tgbotapi.types.idField
import dev.inmo.tgbotapi.types.nameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents a community (a group of chats)
 */
@Serializable
data class Community(
    @SerialName(idField)
    val id: CommunityId,
    @SerialName(nameField)
    val name: String
)
