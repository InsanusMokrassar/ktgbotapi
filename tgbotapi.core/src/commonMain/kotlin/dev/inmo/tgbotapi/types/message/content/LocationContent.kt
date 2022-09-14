package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendLiveLocation
import dev.inmo.tgbotapi.requests.send.SendStaticLocation
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.*

/**
 * [MessageContent] with [location]. This interface contains [copy] method for cases when you do not want to use some
 * class casts for copying of content
 *
 * @see LocationContentSerializer
 * @see Location
 */
@Serializable(LocationContentSerializer::class)
sealed interface LocationContent : MessageContent {
    val location: Location

    fun copy(location: Location = this.location) {
        when (this) {
            is LiveLocationContent -> LiveLocationContent(
                (location as? LiveLocation) ?: this.location.copy(
                    longitude = location.longitude,
                    latitude = location.latitude,
                    horizontalAccuracy = location.horizontalAccuracy
                )
            )
            is StaticLocationContent -> StaticLocationContent(
                (location as? StaticLocation) ?: this.location.copy(
                    longitude = location.longitude,
                    latitude = location.latitude,
                    horizontalAccuracy = location.horizontalAccuracy
                )
            )
        }
    }

    companion object {
        operator fun invoke(location: Location): LocationContent {
            return when (location) {
                is StaticLocation -> StaticLocationContent(location)
                is LiveLocation -> LiveLocationContent(location)
            }
        }
    }
}

/**
 * [KSerializer] for [LocationContent]
 */
@Serializer(LocationContent::class)
object LocationContentSerializer : KSerializer<LocationContent> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LocationContent") {
        element(LocationContent::location.name, LocationSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): LocationContent {
        lateinit var location: Location

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> location = decodeSerializableElement(descriptor, index, LocationSerializer)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
        }

        return LocationContent(location)
    }

    override fun serialize(encoder: Encoder, value: LocationContent) {
        encoder.beginStructure(descriptor).apply {
            encodeSerializableElement(descriptor, 0, LocationSerializer, value.location)
        }.endStructure(descriptor)
    }

}

/**
 * [LocationContent] which represents content with [LiveLocation]. In case you are tracking this content throw message
 * changes, may evolve to [StaticLocationContent]
 *
 * @see dev.inmo.tgbotapi.extensions.behaviour_builder.utils.followLocation
 */
@Serializable(LocationContentSerializer::class)
data class LiveLocationContent(
    override val location: LiveLocation
) : LocationContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<LiveLocationContent>> = SendLiveLocation(
        chatId,
        location.latitude,
        location.longitude,
        location.livePeriod,
        location.horizontalAccuracy,
        location.heading,
        location.proximityAlertRadius,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    ) as SendMessageRequest<ContentMessage<LiveLocationContent>>
}

/**
 * Just a [LocationContent] with [StaticLocation] [location]. It could be [LiveLocationContent] in previous time in case
 * when somebody has sent [LiveLocation] in chat and then stop to broadcast location
 */
@Serializable(LocationContentSerializer::class)
data class StaticLocationContent(
    override val location: StaticLocation
) : LocationContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<StaticLocationContent>> = SendStaticLocation(
        chatId,
        location.latitude,
        location.longitude,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    ) as SendMessageRequest<ContentMessage<StaticLocationContent>>
}
