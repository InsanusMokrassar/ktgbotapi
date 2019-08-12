package com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.ChatPermissions
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedSupergroupChat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage

data class ExtendedSupergroupChatImpl(
    override val id: ChatId,
    override val title: String,
    override val username: Username? = null,
    override val chatPhoto: ChatPhoto,
    override val description: String,
    override val inviteLink: String?,
    override val permissions: ChatPermissions,
    override val pinnedMessage: RawMessage?,
    override val stickerSetName: StickerSetName?,
    override val canSetStickerSet: Boolean
) : ExtendedSupergroupChat
