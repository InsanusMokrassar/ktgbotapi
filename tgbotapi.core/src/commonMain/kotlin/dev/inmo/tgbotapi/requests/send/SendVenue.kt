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
    val foursquareId: FoursquareId? = null,
    @SerialName(foursquareTypeField)
    val foursquareType: FoursquareType? = null,
    @SerialName(googlePlaceIdField)
    val googlePlaceId: GooglePlaceId? = null,
    @SerialName(googlePlaceTypeField)
    val googlePlaceType: GooglePlaceType? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageId? = null,
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
        protectContent: Boolean = false,
        replyToMessageId: MessageId? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): this(
        chatId = chatId,
        latitude = venue.location.latitude,
        longitude = venue.location.longitude,
        title = venue.title,
        address = venue.address,
        foursquareId = venue.foursquareId,
        foursquareType = venue.foursquareType,
        googlePlaceId = venue.googlePlaceId,
        googlePlaceType = venue.googlePlaceType,
        disableNotification = disableNotification,
        protectContent = protectContent,
        replyToMessageId = replyToMessageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        replyMarkup = replyMarkup
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
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): SendVenue = SendVenue(
    chatId,
    this,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
)
