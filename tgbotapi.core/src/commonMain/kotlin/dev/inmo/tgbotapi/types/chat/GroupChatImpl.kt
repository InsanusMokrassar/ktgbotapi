package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.GroupChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String
) : GroupChat
