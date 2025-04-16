package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.amountField
import dev.inmo.tgbotapi.types.nanostarAmountField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarAmount(
    @SerialName(amountField)
    val amount: Long,
    @SerialName(nanostarAmountField)
    val nanostarAmount: Long = 0,
)
