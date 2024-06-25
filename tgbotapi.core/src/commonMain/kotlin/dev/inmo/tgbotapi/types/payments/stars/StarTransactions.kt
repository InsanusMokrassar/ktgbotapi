package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.transactionsField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StarTransactions(
    @SerialName(transactionsField)
    val transactions: List<StarTransaction>
)
