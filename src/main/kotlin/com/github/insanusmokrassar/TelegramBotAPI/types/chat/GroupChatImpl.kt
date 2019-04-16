package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.GroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class GroupChatImpl(
    override val id: ChatId,
    override val title: String? = null,
    override val allMembersAreAdmins: Boolean,
    override val inviteLink: String? = null,
    override val chatPhoto: ChatPhoto? = null,
    override val pinnedMessage: RawMessage? = null
) : GroupChat
