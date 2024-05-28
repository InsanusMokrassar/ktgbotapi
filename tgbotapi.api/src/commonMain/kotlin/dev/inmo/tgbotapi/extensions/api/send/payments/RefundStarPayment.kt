package dev.inmo.tgbotapi.extensions.api.send.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.payments.RefundStarPayment
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.payments.SuccessfulPayment
import dev.inmo.tgbotapi.types.payments.abstracts.TelegramPaymentChargeId

suspend fun TelegramBot.refundStarPayment(
    userId: UserId,
    telegramPaymentChargeId: TelegramPaymentChargeId
) = execute(RefundStarPayment(userId, telegramPaymentChargeId))

suspend fun TelegramBot.refundStarPayment(
    userId: UserId,
    successfulPayment: SuccessfulPayment
) = refundStarPayment(userId, successfulPayment.telegramPaymentChargeId)