package dev.inmo.tgbotapi.types.payments.abstracts

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class TelegramPaymentChargeId(
    val string: String
)