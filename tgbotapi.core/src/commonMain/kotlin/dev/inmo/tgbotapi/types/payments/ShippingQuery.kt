package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ShippingQuery(
    @SerialName(idField)
    val id: ShippingQueryIdentifier,
    @SerialName(fromField)
    override val from: User,
    @SerialName(invoicePayloadField)
    val invoicePayload: InvoicePayload,
    @SerialName(shippingAddressField)
    val shippingAddress: ShippingAddress
) : FromUser
