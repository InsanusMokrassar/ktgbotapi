package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.BannedChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts.SpecialRightsChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.TelegramDate
import com.github.insanusmokrassar.TelegramBotAPI.types.User

data class RestrictedChatMember(
    override val user: User,
    override val untilDate: TelegramDate?,
    val isMember: Boolean,
    val canSendMessages: Boolean,
    val canSendMediaMessages: Boolean,
    val canSendPolls: Boolean,
    val canSendOtherMessages: Boolean,
    val canAddWebpagePreviews: Boolean,
    override val canChangeInfo: Boolean,
    override val canInviteUsers: Boolean,
    override val canPinMessages: Boolean
) : BannedChatMember, SpecialRightsChatMember