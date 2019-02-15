package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.TelegramDate
import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class RestrictedChatMember(
    override val user: User,
    override val untilDate: TelegramDate?,
    val canSendMessages: Boolean,
    val canSendMediaMessages: Boolean,
    val canSendOtherMessages: Boolean,
    val canAddWebpagePreviews: Boolean
) : BannedChatMember