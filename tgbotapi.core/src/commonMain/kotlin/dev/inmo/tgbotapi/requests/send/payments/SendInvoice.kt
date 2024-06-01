package dev.inmo.tgbotapi.requests.send.payments

import dev.inmo.tgbotapi.abstracts.CommonSendInvoiceData
import dev.inmo.tgbotapi.abstracts.types.*
import dev.inmo.tgbotapi.requests.send.abstracts.OptionallyWithEffectRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.LabeledPricesSerializer
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.XTR
import kotlinx.serialization.*

private val invoiceMessageSerializer: DeserializationStrategy<ContentMessage<InvoiceContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

/**
 * @param providerData - JSON-ENCODED FIELD
 */
@Serializable
data class SendInvoice(
    @SerialName(chatIdField)
    override val chatId: IdChatIdentifier,
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
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : CommonSendInvoiceData,
    ChatRequest,
    DisableNotification,
    WithReplyParameters,
    WithReplyMarkup,
    SendMessageRequest<ContentMessage<InvoiceContent>>,
    OptionallyWithEffectRequest<ContentMessage<InvoiceContent>> {
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

    constructor(
        chatId: IdChatIdentifier,
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
        threadId: MessageThreadId? = chatId.threadId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: InlineKeyboardMarkup? = null
    ) : this(
        chatId = chatId,
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
        threadId = threadId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

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
