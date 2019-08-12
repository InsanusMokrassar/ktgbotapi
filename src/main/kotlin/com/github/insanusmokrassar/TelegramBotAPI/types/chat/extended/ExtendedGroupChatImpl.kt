package com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatId
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatPhoto
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedGroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class ExtendedGroupChatImpl(
    override val id: ChatId,
    override val title: String,
    override val chatPhoto: ChatPhoto,
    override val description: String,
    override val inviteLink: String?,
    override val permissions: ChatPermissions,
    override val pinnedMessage: RawMessage?
) : ExtendedGroupChat
