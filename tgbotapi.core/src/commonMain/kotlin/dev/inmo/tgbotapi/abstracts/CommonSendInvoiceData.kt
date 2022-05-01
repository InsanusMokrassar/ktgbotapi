package dev.inmo.tgbotapi.abstracts

import dev.inmo.tgbotapi.types.payments.abstracts.Currencied
import dev.inmo.tgbotapi.types.payments.abstracts.Priced

interface CommonSendInvoiceData : Titled, Currencied, Priced {
    val description: String
    val payload: String
    val providerToken: String
    val maxTipAmount: Int?
    val suggestedTipAmounts: List<Int>?
    val providerData: String?
    val requireName: Boolean
    val requirePhoneNumber: Boolean
    val requireEmail: Boolean
    val requireShippingAddress: Boolean
    val shouldSendPhoneNumberToProvider: Boolean
    val shouldSendEmailToProvider: Boolean
    val priceDependOnShipAddress: Boolean

    val photoUrl: String?
    val photoSize: Long?
    val photoWidth: Int?
    val photoHeight: Int?



    fun setPhoto(
        photoUrl: String,
        photoSize: Long? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null
    )

    fun unsetPhoto()
}
