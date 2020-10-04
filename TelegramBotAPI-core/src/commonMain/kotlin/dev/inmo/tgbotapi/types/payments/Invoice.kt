package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Invoice(
    @SerialName(titleField)
    val title: String,
    @SerialName(descriptionField)
    val description: String,
    @SerialName(startParameterField)
    val startParameter: StartParameter,
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(totalAmountField)
    override val amount: Long
) : Amounted, Currencied
