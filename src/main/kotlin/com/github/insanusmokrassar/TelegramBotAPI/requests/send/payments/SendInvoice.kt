package com.github.insanusmokrassar.TelegramBotAPI.requests.send.payments

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.types.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.LabeledPrice
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.LabeledPricesSerializer
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.*
import kotlinx.serialization.*
import kotlinx.serialization.Optional
import java.util.*

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
    @Serializable(CurrencySerializer::class)
    @SerialName(currencyField)
    override val currency: Currency,
    @Serializable(LabeledPricesSerializer::class)
    @SerialName(pricesField)
    override val prices: List<LabeledPrice>,
    @SerialName(providerDataField)
    @Optional
    val providerData: String? = null,
    @SerialName(requireNameField)
    @Optional
    val requireName: Boolean = false,
    @SerialName(requirePhoneNumberField)
    @Optional
    val requirePhoneNumber: Boolean = false,
    @SerialName(requireEmailField)
    @Optional
    val requireEmail: Boolean = false,
    @SerialName(requireShippingAddressField)
    @Optional
    val requireShippingAddress: Boolean = false,
    @SerialName(shouldSendPhoneNumberToProviderField)
    @Optional
    val shouldSendPhoneNumberToProvider: Boolean = false,
    @SerialName(shouldSendEmailToProviderField)
    @Optional
    val shouldSendEmailToProvider: Boolean = false,
    @SerialName(priceDependOnShipAddressField)
    @Optional
    val priceDependOnShipAddress: Boolean = false,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: InlineKeyboardMarkup? = null
) : Currencied,
    Priced,
    ChatRequest,
    DisableNotification,
    ReplyMessageId,
    ReplyMarkup,
    SendMessageRequest<RawMessage> {
    override fun method(): String = "sendInvoice"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()

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