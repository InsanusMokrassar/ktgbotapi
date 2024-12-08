package dev.inmo.tgbotapi.extensions.api.set

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.set.SetUserEmojiStatus
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import korlibs.time.DateTime

public suspend fun TelegramBot.setUserEmojiStatus(
    userId: UserId,
    customEmojiId: CustomEmojiId,
    expirationDate: TelegramDate? = null
): Boolean = execute(
    SetUserEmojiStatus(
        userId = userId,
        customEmojiId = customEmojiId,
        expirationDate = expirationDate
    )
)

public suspend fun TelegramBot.setUserEmojiStatus(
    user: User,
    customEmojiId: CustomEmojiId,
    expirationDate: TelegramDate? = null
): Boolean = setUserEmojiStatus(
    user.id,
    customEmojiId,
    expirationDate
)


public suspend fun TelegramBot.setUserEmojiStatus(
    userId: UserId,
    customEmojiId: CustomEmojiId,
    expirationDate: DateTime
): Boolean = setUserEmojiStatus(
    userId,
    customEmojiId,
    expirationDate.toTelegramDate()
)

public suspend fun TelegramBot.setUserEmojiStatus(
    user: User,
    customEmojiId: CustomEmojiId,
    expirationDate: DateTime
): Boolean = setUserEmojiStatus(
    user.id,
    customEmojiId,
    expirationDate
)
