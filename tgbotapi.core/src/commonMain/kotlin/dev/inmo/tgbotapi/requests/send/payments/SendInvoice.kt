package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.CommonAbstracts.CommonSendInvoiceData
import dev.inmo.tgbotapi.CommonAbstracts.types.*
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.LabeledPricesSerializer
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import kotlinx.serialization.*

private val invoiceMessageSerializer: DeserializationStrategy<ContentMessage<InvoiceContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

/**
 * @param providerData - JSON-ENCODED FIELD
 */
@Serializable
data class SendInvoice(
    @SerialName(chatIdField)
    override val chatId: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(descriptionField)
    override val description: String,
    @SerialName(payloadField)
    override val payload: String,
    @SerialName(providerTokenField)
    override val providerToken: String,
    @SerialName(currencyField)
    override val currency: Currency,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>,
    @SerialName(maxTipAmountField)
    override val maxTipAmount: Int? = null,
    @SerialName(suggestedTipAmountsField)
    override val suggestedTipAmounts: List<Int>? = null,
    @SerialName(startParameterField)
    val startParameter: StartParameter? = null,
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
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : CommonSendInvoiceData,
    ChatRequest,
    DisableNotification,
    ReplyMessageId,
    ReplyMarkup,
    SendMessageRequest<ContentMessage<InvoiceContent>> {
    override fun method(): String = "sendInvoice"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<InvoiceContent>>
        get() = invoiceMessageSerializer
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

    init {
        suggestedTipAmounts ?.let { _ ->
            require(suggestedTipAmounts.size in suggestedTipAmountsLimit)
            maxTipAmount ?.let { _ ->
                require(
                    suggestedTipAmounts.none { it > maxTipAmount }
                )
            }
        }
    }

    override fun setPhoto(
        photoUrl: String,
        photoSize: Long?,
        photoWidth: Int?,
        photoHeight: Int?
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
