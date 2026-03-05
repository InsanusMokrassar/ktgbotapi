package dev.inmo.tgbotapi.extensions.api.edit.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.payments.EditUserStarSubscription
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId

public suspend fun TelegramBot.editUserStarSubscription(
    userId: UserId,
    telegramPaymentChargeId: TelegramPaymentChargeId,
    isCanceled: Boolean
): Unit = execute(
    EditUserStarSubscription(
        userId = userId,
        telegramPaymentChargeId = telegramPaymentChargeId,
        isCanceled = isCanceled
    )
)

public suspend fun TelegramBot.editUserStarSubscription(
    user: User,
    telegramPaymentChargeId: TelegramPaymentChargeId,
    isCanceled: Boolean
): Unit = editUserStarSubscription(
    userId = user.id,
    telegramPaymentChargeId = telegramPaymentChargeId,
    isCanceled = isCanceled
)

public suspend fun TelegramBot.cancelUserStarSubscription(
    userId: UserId,
    telegramPaymentChargeId: TelegramPaymentChargeId,
): Unit = editUserStarSubscription(
    userId = userId,
    telegramPaymentChargeId = telegramPaymentChargeId,
    isCanceled = true
)

public suspend fun TelegramBot.cancelUserStarSubscription(
    user: User,
    telegramPaymentChargeId: TelegramPaymentChargeId,
): Unit = editUserStarSubscription(
    user = user,
    telegramPaymentChargeId = telegramPaymentChargeId,
    isCanceled = true
)

public suspend fun TelegramBot.enableUserStarSubscription(
    userId: UserId,
    telegramPaymentChargeId: TelegramPaymentChargeId,
): Unit = editUserStarSubscription(
    userId = userId,
    telegramPaymentChargeId = telegramPaymentChargeId,
    isCanceled = false
)

public suspend fun TelegramBot.enableUserStarSubscription(
    user: User,
    telegramPaymentChargeId: TelegramPaymentChargeId,
): Unit = editUserStarSubscription(
    user = user,
    telegramPaymentChargeId = telegramPaymentChargeId,
    isCanceled = false
)
