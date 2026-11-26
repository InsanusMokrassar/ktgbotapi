package dev.inmo.tgbotapi.requests.send

import dev.inmo.micro_utils.common.Warning
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.VenueContent
import dev.inmo.tgbotapi.types.venue.Venue
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<VenueContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVenue @Warning(sendMessagePrimaryConstructorWarning) constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier? = null,
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
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(ephemeralMessageParametersField)
    override val ephemeralMessageParameters: EphemeralMessageParameters? = chatId.receiverUser ?.let(::EphemeralMessageParameters),
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(suggestedPostParametersField)
    override val suggestedPostParameters: SuggestedPostParameters? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendContentMessageRequest<ChatContentMessage<VenueContent>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<VenueContent>>,
    TitledSendMessageRequest<ChatContentMessage<VenueContent>>,
    PositionedSendMessageRequest<ChatContentMessage<VenueContent>>,
    OptionallyEphemeralSendRequest
{
    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    @OptIn(Warning::class)
    constructor(
        chatId: ChatIdentifier,
        latitude: Double,
        longitude: Double,
        title: String,
        address: String,
        foursquareId: FoursquareId? = null,
        foursquareType: FoursquareType? = null,
        googlePlaceId: GooglePlaceId? = null,
        googlePlaceType: GooglePlaceType? = null,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        receiverUserId: UserId? = chatId.receiverUser,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        latitude = latitude,
        longitude = longitude,
        title = title,
        address = address,
        foursquareId = foursquareId,
        foursquareType = foursquareType,
        googlePlaceId = googlePlaceId,
        googlePlaceType = googlePlaceType,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = receiverUserId?.let(::EphemeralMessageParameters),
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    @OptIn(Warning::class)
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        latitude: Double,
        longitude: Double,
        title: String,
        address: String,
        foursquareId: FoursquareId? = null,
        foursquareType: FoursquareType? = null,
        googlePlaceId: GooglePlaceId? = null,
        googlePlaceType: GooglePlaceType? = null,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = null,
        latitude = latitude,
        longitude = longitude,
        title = title,
        address = address,
        foursquareId = foursquareId,
        foursquareType = foursquareType,
        googlePlaceId = googlePlaceId,
        googlePlaceType = googlePlaceType,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        ephemeralMessageParameters = ephemeralMessageParameters,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    init {
        requireSendMessageTarget(chatId, ephemeralMessageParameters)
    }

    /** Sends to [chatId]. Set [receiverUserId] to deliver an ephemeral message to one user in the chat. */
    constructor(
        chatId: ChatIdentifier,
        venue: Venue,
        threadId: MessageThreadId? = chatId.threadId,
        directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        receiverUserId: UserId? = chatId.receiverUser,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        chatId = chatId,
        latitude = venue.location.latitude,
        longitude = venue.location.longitude,
        title = venue.title,
        address = venue.address,
        foursquareId = venue.foursquareId,
        foursquareType = venue.foursquareType,
        googlePlaceId = venue.googlePlaceId,
        googlePlaceType = venue.googlePlaceType,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        receiverUserId = receiverUserId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    /** Creates an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
    constructor(
        ephemeralMessageParameters: EphemeralMessageParameters,
        venue: Venue,
        threadId: MessageThreadId? = null,
        directMessageThreadId: DirectMessageThreadId? = null,
        businessConnectionId: BusinessConnectionId? = null,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        allowPaidBroadcast: Boolean = false,
        effectId: EffectId? = null,
        suggestedPostParameters: SuggestedPostParameters? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ) : this(
        ephemeralMessageParameters = ephemeralMessageParameters,
        latitude = venue.location.latitude,
        longitude = venue.location.longitude,
        title = venue.title,
        address = venue.address,
        foursquareId = venue.foursquareId,
        foursquareType = venue.foursquareType,
        googlePlaceId = venue.googlePlaceId,
        googlePlaceType = venue.googlePlaceType,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    override fun method(): String = "sendVenue"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<VenueContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

fun Venue.toRequest(
    chatId: ChatIdentifier,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendVenue = SendVenue(
    chatId = chatId,
    venue = this,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    receiverUserId = receiverUserId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)

/** Sends the message as an ephemeral reply to the callback query in [ephemeralMessageParameters]. */
fun Venue.toRequest(
    ephemeralMessageParameters: EphemeralMessageParameters,
    threadId: MessageThreadId? = null,
    directMessageThreadId: DirectMessageThreadId? = null,
    businessConnectionId: BusinessConnectionId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): SendVenue = SendVenue(
    ephemeralMessageParameters = ephemeralMessageParameters,
    venue = this,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    suggestedPostParameters = suggestedPostParameters,
    replyParameters = replyParameters,
    replyMarkup = replyMarkup
)
