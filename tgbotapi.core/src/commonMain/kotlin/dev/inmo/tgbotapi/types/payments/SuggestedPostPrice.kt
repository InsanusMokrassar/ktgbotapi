package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.amountField
import dev.inmo.tgbotapi.types.currencyField
import dev.inmo.tgbotapi.types.payments.abstracts.Amounted
import dev.inmo.tgbotapi.types.payments.abstracts.Currencied
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SuggestedPostPrice(
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(amountField)
    override val amount: Long
) : Amounted, Currencied
