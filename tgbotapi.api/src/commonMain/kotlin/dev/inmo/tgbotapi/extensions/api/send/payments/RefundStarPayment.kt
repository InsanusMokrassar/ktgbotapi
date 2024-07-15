package dev.inmo.tgbotapi.extensions.api.send.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.payments.RefundStarPayment
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId

public suspend fun TelegramBot.refundStarPayment(
    userId: UserId,
    telegramPaymentChargeId: TelegramPaymentChargeId
): Boolean = execute(RefundStarPayment(userId, telegramPaymentChargeId))

public suspend fun TelegramBot.refundStarPayment(
    userId: UserId,
    successfulPayment: SuccessfulPayment
): Boolean = refundStarPayment(userId, successfulPayment.telegramPaymentChargeId)