package dev.inmo.tgbotapi.types.payments.ton

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.CommonEvent
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** TON transfer reported as a service message. Outgoing amounts are negative. */
@Serializable
data class TonWalletTransfer(
    /** May be empty while the transfer is pending. */
    @SerialName(transactionIdField) val transactionId: String,
    @SerialName(peerAddressField) val peerAddress: TonAddress,
    @SerialName(amountField) val amount: String,
    @SerialName(commentField) val comment: String? = null,
) : CommonEvent
