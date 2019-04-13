package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RawChat(
    override val id: ChatId,
    private val type: String,
    private val title: String? = null,
    private val username: Username? = null,
    private val first_name: String? = null,
    private val last_name: String? = null,
    private val all_members_are_administrators: Boolean? = null,
    private val description: String? = null,
    private val invite_link: String? = null,
    private val pinned_message: RawMessage? = null,
    private val sticker_set_name: String? = null,
    private val can_set_sticker_set: Boolean? = null,
    @SerialName("photo")
    override val chatPhoto: ChatPhoto? = null
) : Chat {
    fun extractChat(): Chat {
        return when (type) {
            "private" -> PrivateChat(id, username, first_name, last_name, chatPhoto)
            "group" -> GroupChatImpl(
                id,
                title,
                all_members_are_administrators ?: false,
                invite_link,
                chatPhoto
            )
            "supergroup" -> SupergroupChat(
                id,
                title,
                username,
                description,
                all_members_are_administrators ?: false,
                invite_link,
                chatPhoto,
                pinned_message,
                sticker_set_name,
                can_set_sticker_set ?: false
            )
            "channel" -> ChannelChat(
                id,
                title,
                username,
                description,
                invite_link,
                chatPhoto,
                pinned_message
            )
            else -> throw IllegalArgumentException("Unknown type of chat")
        }
    }
}
