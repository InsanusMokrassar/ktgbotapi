package dev.inmo.tgbotapi.types.communities

import dev.inmo.tgbotapi.types.communityField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Service message about a chat being added to a community
 */
@Serializable
data class CommunityChatAdded(
    @SerialName(communityField)
    val community: Community
) : CommonEvent
