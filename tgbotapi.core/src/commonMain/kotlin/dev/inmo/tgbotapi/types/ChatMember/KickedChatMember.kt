package dev.inmo.tgbotapi.types.ChatMember

import dev.inmo.tgbotapi.types.ChatMember.abstracts.BannedChatMember
import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.User

data class KickedChatMember(
    override val user: User,
    override val untilDate: TelegramDate?
) : BannedChatMember