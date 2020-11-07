package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.CommonAbstracts.types.*
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.LabeledPricesSerializer
import dev.inmo.tgbotapi.types.payments.abstracts.*
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
    val title: String,
    @SerialName(descriptionField)
    val description: String,
    @SerialName(payloadField)
    val payload: String,
    @SerialName(providerTokenField)
    val providerToken: String,
    @SerialName(startParameterField)
    val startParameter: StartParameter,
    @SerialName(currencyField)
    override val currency: Currency,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>,
    @SerialName(providerDataField)
    val providerData: String? = null,
    @SerialName(requireNameField)
    val requireName: Boolean = false,
    @SerialName(requirePhoneNumberField)
    val requirePhoneNumber: Boolean = false,
    @SerialName(requireEmailField)
    val requireEmail: Boolean = false,
    @SerialName(requireShippingAddressField)
    val requireShippingAddress: Boolean = false,
    @SerialName(shouldSendPhoneNumberToProviderField)
    val shouldSendPhoneNumberToProvider: Boolean = false,
    @SerialName(shouldSendEmailToProviderField)
    val shouldSendEmailToProvider: Boolean = false,
    @SerialName(priceDependOnShipAddressField)
    val priceDependOnShipAddress: Boolean = false,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : Currencied,
    Priced,
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
    var photoUrl: String? = null
        private set
    @SerialName(photoSizeField)
    var photoSize: Long? = null
        private set

    @SerialName(photoWidthField)
    var photoWidth: Int? = null
        private set
    @SerialName(photoHeightField)
    var photoHeight: Int? = null
        private set

    fun setPhoto(
        photoUrl: String,
        photoSize: Long? = null,
        photoWidth: Int? = null,
        photoHeight: Int? = null
    ) {
        this.photoUrl = photoUrl
        this.photoSize = photoSize
        this.photoWidth = photoWidth
        this.photoHeight = photoHeight
    }

    fun unsetPhoto() {
        photoUrl = null
        photoSize = null
        photoWidth = null
        photoHeight = null
    }
}
