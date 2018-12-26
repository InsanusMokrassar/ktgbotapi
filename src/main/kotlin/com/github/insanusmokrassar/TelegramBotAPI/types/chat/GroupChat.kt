package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*

interface GroupChat : PublicChat {
    val allMembersAreAdmins: Boolean
}

data class GroupChatImpl(
    override val id: ChatId,
    override val title: String? = null,
    override val allMembersAreAdmins: Boolean,
    override val inviteLink: String? = null,
    override val chatPhoto: ChatPhoto? = null) : GroupChat
