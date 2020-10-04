package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.ChatMember.abstracts.BannedChatMember
import dev.inmo.tgbotapi.types.ChatMember.abstracts.SpecialRightsChatMember
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.User

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