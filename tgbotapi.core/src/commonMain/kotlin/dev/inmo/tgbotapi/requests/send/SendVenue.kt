package dev.inmo.tgbotapi.requests.send

import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.VenueContent
import dev.inmo.tgbotapi.types.venue.Venue
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VenueContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVenue(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(latitudeField)
    override val latitude: Double,
    @SerialName(longitudeField)
    override val longitude: Double,
    @SerialName(titleField)
    override val title: String,
    @SerialName(addressField)
    val address: String,
    @SerialName(foursquareIdField)
    val foursquareId: String? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<VenueContent>>,
    PositionedSendMessageRequest<ContentMessage<VenueContent>>,
    TitledSendMessageRequest<ContentMessage<VenueContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VenueContent>>
{
    constructor(
        chatId: ChatIdentifier,
        venue: Venue,
        disableNotification: Boolean = false,
        replyToMessageId: MessageIdentifier? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): this(
        chatId,
        venue.location.latitude,
        venue.location.longitude,
        venue.title,
        venue.address,
        venue.foursquareId,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    override fun method(): String = "sendVenue"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VenueContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun Venue.toRequest(
    chatId: ChatIdentifier,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): SendVenue = SendVenue(
    chatId,
    this,
    disableNotification,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)
