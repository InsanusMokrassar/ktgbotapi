package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
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
