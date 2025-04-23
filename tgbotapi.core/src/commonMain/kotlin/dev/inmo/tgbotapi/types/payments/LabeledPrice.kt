package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.amountField
import dev.inmo.tgbotapi.types.labelField
import dev.inmo.tgbotapi.types.payments.abstracts.Amounted
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LabeledPrice(
    @SerialName(labelField)
    val label: String,
    @SerialName(amountField)
    override val amount: Long,
) : Amounted
