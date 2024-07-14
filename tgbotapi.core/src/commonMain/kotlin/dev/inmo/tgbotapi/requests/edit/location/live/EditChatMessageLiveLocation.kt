package dev.inmo.tgbotapi.requests.edit.location.live

import dev.inmo.tgbotapi.requests.edit.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.location.LiveLocation
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.LiveLocationContent
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

private val commonResultDeserializer = TelegramBotAPIMessageDeserializationStrategyClass<ContentMessage<LiveLocationContent>>()
const val editMessageLiveLocationMethod = "editMessageLiveLocation"

@Serializable
data class EditChatMessageLiveLocation(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(messageIdField)
    override val messageId: MessageId,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(livePeriodField)
    val livePeriod: Seconds? = null,
    @SerialName(horizontalAccuracyField)
    override val horizontalAccuracy: Meters? = null,
    @SerialName(headingField)
    override val heading: Degrees? = null,
    @SerialName(proximityAlertRadiusField)
    override val proximityAlertRadius: Meters? = null,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(replyMarkupField)
    override val replyMarkup: InlineKeyboardMarkup? = null
) : EditChatMessage<LiveLocationContent>, EditReplyMessage, EditLocationMessage {
    override fun method(): String = editMessageLiveLocationMethod
    override val resultDeserializer: DeserializationStrategy<ContentMessage<LiveLocationContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        if (horizontalAccuracy != null && horizontalAccuracy !in horizontalAccuracyLimit) {
            throwRangeError("horizontalAccuracy", horizontalAccuracyLimit, horizontalAccuracy)
        }
    }
}
