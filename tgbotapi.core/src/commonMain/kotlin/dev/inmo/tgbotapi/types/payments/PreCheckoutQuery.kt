package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.abstracts.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// TODO:: separate to normal classes hierarchy
@Serializable
data class PreCheckoutQuery(
    @SerialName(idField)
    val id: PreCheckoutQueryId,
    @SerialName(fromField)
    override val user: User,
    @SerialName(currencyField)
    override val currency: Currency,
    @SerialName(totalAmountField)
    override val amount: Long,
    @SerialName(invoicePayloadField)
    val invoicePayload: InvoicePayload,
    @SerialName(shippingOptionIdField)
    val shippingOptionId: ShippingOptionIdentifier? = null,
    @SerialName(orderInfoField)
    val orderInfo: OrderInfo? = null
) : Currencied, Amounted, FromUser
