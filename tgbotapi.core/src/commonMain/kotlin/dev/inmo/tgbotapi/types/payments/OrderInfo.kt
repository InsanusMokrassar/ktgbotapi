package dev.inmo.tgbotapi.types.payments

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderInfo(
    @SerialName(nameField)
    val name: String,
    @SerialName(phoneNumberField)
    val phoneNumber: String,
    @SerialName(emailField)
    val email: String,
    @SerialName(shippingAddressField)
    val shippingAddress: ShippingAddress
)
