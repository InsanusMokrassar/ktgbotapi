package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.VenueContent
import com.github.insanusmokrassar.TelegramBotAPI.types.venue.Venue
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
    replyMarkup: KeyboardMarkup? = null
): SendVenue = SendVenue(
    chatId,
    this,
    disableNotification,
    replyToMessageId,
    replyMarkup
)
