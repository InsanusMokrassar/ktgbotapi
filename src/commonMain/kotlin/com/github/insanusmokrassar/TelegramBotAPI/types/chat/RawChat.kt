package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class RawChat(
    override val id: ChatId,
    private val type: String,
    @Optional private val title: String? = null,
    @Optional private val username: String? = null,
    @Optional private val first_name: String? = null,
    @Optional private val last_name: String? = null,
    @Optional private val all_members_are_administrators: Boolean? = null,
    @Optional private val description: String? = null,
    @Optional private val invite_link: String? = null,
    @Optional private val pinned_message: RawMessage? = null,
    @Optional private val sticker_set_name: String? = null,
    @Optional private val can_set_sticker_set: Boolean? = null,
    @SerialName("photo")
    @Optional override val chatPhoto: ChatPhoto? = null
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
