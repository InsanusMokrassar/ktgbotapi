package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * All the field of this class are nullable due to specific of [OrderInfo](https://core.telegram.org/bots/api#orderinfo)
 * from official bots api
 *
 * @see dev.inmo.tgbotapi.abstracts.CommonSendInvoiceData
 * @see dev.inmo.tgbotapi.requests.send.payments.SendInvoice
 */
@Serializable
data class OrderInfo(
    @SerialName(nameField)
    val name: String?,
    @SerialName(phoneNumberField)
    val phoneNumber: String?,
    @SerialName(emailField)
    val email: String?,
    @SerialName(shippingAddressField)
    val shippingAddress: ShippingAddress?,
)
