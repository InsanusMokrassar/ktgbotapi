package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.abstracts.CommonSendInvoiceData
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.LabeledPricesSerializer
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.XTR
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class InputInvoiceMessageContent(
    @SerialName(titleField)
    override val title: String,
    @SerialName(descriptionField)
    override val description: String,
    @SerialName(payloadField)
    override val payload: String,
    @SerialName(providerTokenField)
    override val providerToken: String?,
    @SerialName(currencyField)
    override val currency: Currency,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>,
    @SerialName(maxTipAmountField)
    override val maxTipAmount: Int? = null,
    @SerialName(suggestedTipAmountsField)
    override val suggestedTipAmounts: List<Int>? = null,
    @SerialName(providerDataField)
    override val providerData: String? = null,
    @SerialName(requireNameField)
    override val requireName: Boolean = false,
    @SerialName(requirePhoneNumberField)
    override val requirePhoneNumber: Boolean = false,
    @SerialName(requireEmailField)
    override val requireEmail: Boolean = false,
    @SerialName(requireShippingAddressField)
    override val requireShippingAddress: Boolean = false,
    @SerialName(shouldSendPhoneNumberToProviderField)
    override val shouldSendPhoneNumberToProvider: Boolean = false,
    @SerialName(shouldSendEmailToProviderField)
    override val shouldSendEmailToProvider: Boolean = false,
    @SerialName(priceDependOnShipAddressField)
    override val priceDependOnShipAddress: Boolean = false,
) : InputMessageContent, CommonSendInvoiceData {
    @SerialName(photoUrlField)
    override var photoUrl: String? = null
        private set

    @SerialName(photoSizeField)
    override var photoSize: Long? = null
        private set

    @SerialName(photoWidthField)
    override var photoWidth: Int? = null
        private set

    @SerialName(photoHeightField)
    override var photoHeight: Int? = null
        private set

    constructor(
        title: String,
        description: String,
        payload: String,
        prices: List<LabeledPrice>,
        maxTipAmount: Int? = null,
        suggestedTipAmounts: List<Int>? = null,
        providerData: String? = null,
        requireName: Boolean = false,
        requirePhoneNumber: Boolean = false,
        requireEmail: Boolean = false,
        requireShippingAddress: Boolean = false,
        shouldSendPhoneNumberToProvider: Boolean = false,
        shouldSendEmailToProvider: Boolean = false,
        priceDependOnShipAddress: Boolean = false,
    ) : this(
        title = title,
        description = description,
        payload = payload,
        providerToken = null,
        currency = Currency.XTR,
        prices = prices,
        maxTipAmount = maxTipAmount,
        suggestedTipAmounts = suggestedTipAmounts,
        providerData = providerData,
        requireName = requireName,
        requirePhoneNumber = requirePhoneNumber,
        requireEmail = requireEmail,
        requireShippingAddress = requireShippingAddress,
        shouldSendPhoneNumberToProvider = shouldSendPhoneNumberToProvider,
        shouldSendEmailToProvider = shouldSendEmailToProvider,
        priceDependOnShipAddress = priceDependOnShipAddress,
    )

    override fun setPhoto(
        photoUrl: String,
        photoSize: Long?,
        photoWidth: Int?,
        photoHeight: Int?,
    ) {
        this.photoUrl = photoUrl
        this.photoSize = photoSize
        this.photoWidth = photoWidth
        this.photoHeight = photoHeight
    }

    override fun unsetPhoto() {
        photoUrl = null
        photoSize = null
        photoWidth = null
        photoHeight = null
    }
}
