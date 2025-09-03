package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.abstracts.OptionallyWithUser
import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.topicIdField
import dev.inmo.tgbotapi.types.userField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DirectMessagesTopic(
    @SerialName(topicIdField)
    val threadId: DirectMessageThreadId,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(userField)
    override val user: PreviewUser? = null,
) : OptionallyWithUser
