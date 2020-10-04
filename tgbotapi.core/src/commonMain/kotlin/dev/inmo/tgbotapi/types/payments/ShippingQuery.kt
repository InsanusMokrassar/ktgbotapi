package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShippingQuery(
    @SerialName(idField)
    val id: ShippingQueryIdentifier,
    @SerialName(fromField)
    val user: User,
    @SerialName(invoicePayloadField)
    val invoicePayload: InvoicePayload,
    @SerialName(shippingAddressField)
    val shippingAddress: ShippingAddress
)
