package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import kotlinx.serialization.*

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
    @Optional
    val foursquareId: String? = null,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<RawMessage>,
    PositionedSendMessageRequest<RawMessage>,
    TitledSendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage>
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
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
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
