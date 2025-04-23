package dev.inmo.tgbotapi.utils

import dev.inmo.tgbotapi.types.RawChatId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.User

val TelegramFakeUser = CommonUser(
    id = UserId(RawChatId.FakeUserId),
    firstName = "Telegram",
)

fun User.isFakeTelegramUser() = id == TelegramFakeUser.id && firstName == TelegramFakeUser.firstName
