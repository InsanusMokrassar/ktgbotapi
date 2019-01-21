package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class SupergroupChat(
    override val id: ChatId,
    override val title: String? = null,
    val username: String? = null,
    val description: String? = null,
    override val allMembersAreAdmins: Boolean,
    override val inviteLink: String? = null,
    override val chatPhoto: ChatPhoto? = null,
    val pinnedMessage: RawMessage? = null,
    val stickerSetName: String? = null,
    val canSetStickerSet: Boolean
) : GroupChat
