package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.bot.exceptions.IllegalChatRawObjectException
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended.*
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
    private val description: String? = null,
    private val invite_link: String? = null,
    private val pinned_message: RawMessage? = null,
    private val sticker_set_name: String? = null,
    private val can_set_sticker_set: Boolean? = null,
    @SerialName("photo")
    private val chatPhoto: ChatPhoto? = null,
    private val permissions: ChatPermissions? = null
) : Chat {
    private fun extractExtendedChat(): ExtendedChat {
        return when (type) {
            "private" -> ExtendedPrivateChatImpl(id, username, first_name ?: "", last_name ?: "", chatPhoto!!)
            "group" -> ExtendedGroupChatImpl(
                id,
                title!!,
                chatPhoto!!,
                description ?: "",
                invite_link,
                permissions!!,
                pinned_message
            )
            "supergroup" -> ExtendedSupergroupChatImpl(
                id,
                title!!,
                username,
                chatPhoto!!,
                description ?: "",
                invite_link,
                permissions!!,
                pinned_message,
                sticker_set_name,
                can_set_sticker_set ?: false
            )
            "channel" -> ExtendedChannelChatImpl(
                id,
                title!!,
                username,
                chatPhoto!!,
                description ?: "",
                invite_link,
                pinned_message
            )
            else -> throw IllegalArgumentException("Unknown type of chat")
        }
    }

    private fun extractPreviewChat(): Chat {
        return when (type) {
            "private" -> PrivateChatImpl(id, username, first_name ?: "", last_name ?: "")
            "group" -> GroupChatImpl(
                id,
                title!!
            )
            "supergroup" -> SupergroupChatImpl(
                id,
                title!!,
                username
            )
            "channel" -> ChannelChatImpl(
                id,
                title!!,
                username
            )
            else -> throw IllegalArgumentException("Unknown type of chat")
        }
    }

    fun extractChat(): Chat {
        return try {
            when (chatPhoto) {
                null -> extractPreviewChat()
                else -> extractExtendedChat()
            }
        } catch (e: NullPointerException) {
            throw IllegalChatRawObjectException(this)
        }
    }
}
