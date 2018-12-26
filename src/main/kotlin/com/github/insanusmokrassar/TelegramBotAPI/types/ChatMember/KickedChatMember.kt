package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember

import com.github.insanusmokrassar.TelegramBotAPI.types.TelegramDate
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import org.joda.time.DateTime

data class KickedChatMember(
    override val user: User,
    override val untilDate: TelegramDate?
) : BannedChatMember