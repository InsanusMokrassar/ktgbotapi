package dev.inmo.tgbotapi.types.message.ChatEvents.forum

import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent
import dev.inmo.tgbotapi.types.webAppNameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WriteAccessAllowed(
    @SerialName(webAppNameField)
    val webAppName: String? = null
) : ForumEvent
