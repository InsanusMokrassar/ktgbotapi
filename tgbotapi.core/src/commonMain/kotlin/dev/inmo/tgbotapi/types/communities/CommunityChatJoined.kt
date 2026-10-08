package dev.inmo.tgbotapi.types.communities

import dev.inmo.tgbotapi.types.communityField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Service message about a chat being joined by a user from a community.
 */
@Serializable
data class CommunityChatJoined(
    @SerialName(communityField)
    val community: Community
) : CommonEvent
