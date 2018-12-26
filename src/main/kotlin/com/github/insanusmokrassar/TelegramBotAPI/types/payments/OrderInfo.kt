package com.github.insanusmokrassar.TelegramBotAPI.types.payments

import com.github.insanusmokrassar.TelegramBotAPI.types.*
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
