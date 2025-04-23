package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.abstracts.CommonSendInvoiceData
import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.LabeledPricesSerializer
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.XTR
import dev.inmo.tgbotapi.utils.TimeSpanAsSecondsSerializer
import korlibs.time.TimeSpan
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

/**
 * @param providerData - JSON-ENCODED FIELD
 */
@Serializable
data class CreateInvoiceLink(
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
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = null,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>,
    @SerialName(subscriptionPeriodField)
    @Serializable(TimeSpanAsSecondsSerializer::class)
    override val subscriptionPeriod: TimeSpan? = null,
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
) : CommonSendInvoiceData, SimpleRequest<String>, WithOptionalBusinessConnectionId, SubscriptionPeriodInfo {
    override fun method(): String = "createInvoiceLink"

    override val resultDeserializer: DeserializationStrategy<String>
        get() = String.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

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

    init {
        suggestedTipAmounts ?.let { _ ->
            require(suggestedTipAmounts.size in suggestedTipAmountsLimit)
            maxTipAmount ?.let { _ ->
                require(
                    suggestedTipAmounts.none { it > maxTipAmount },
                )
            }
        }
    }

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

    companion object {
        const val DEFAULT: Seconds = 2592000 // 30 days
    }
}
