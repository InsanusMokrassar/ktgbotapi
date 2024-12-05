package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.TelegramDate
import dev.inmo.tgbotapi.types.isFirstRecurringField
import dev.inmo.tgbotapi.types.subscriptionExpirationDateField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RecurringInfo(
    @SerialName(subscriptionExpirationDateField)
    val subscriptionExpirationDate: TelegramDate,
    @SerialName(isFirstRecurringField)
    val firstSubscriptionPeriod: Boolean
)
