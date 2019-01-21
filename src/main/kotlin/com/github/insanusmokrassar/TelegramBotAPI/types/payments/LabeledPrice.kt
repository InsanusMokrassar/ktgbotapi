package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.amountField
import com.github.insanusmokrassar.TelegramBotAPI.types.labelField
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.Amounted
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LabeledPrice(
    @SerialName(labelField)
    val label: String,
    @SerialName(amountField)
    override val amount: Long
) : Amounted
