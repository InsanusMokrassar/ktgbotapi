package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendLiveLocation
import dev.inmo.tgbotapi.requests.send.SendStaticLocation
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.location.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.*

@Serializable(LocationContentSerializer::class)
sealed interface LocationContent : MessageContent {
    val location: Location

    companion object {
        operator fun invoke(location: Location): LocationContent {
            return when (location) {
                is StaticLocation -> StaticLocationContent(location)
                is LiveLocation -> LiveLocationContent(location)
            }
        }
    }
}

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

@Serializable(LocationContentSerializer::class)
data class LiveLocationContent(
    override val location: LiveLocation
) : LocationContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
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
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    ) as SendMessageRequest<ContentMessage<LiveLocationContent>>
}

@Serializable(LocationContentSerializer::class)
data class StaticLocationContent(
    override val location: StaticLocation
) : LocationContent {
    override fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<StaticLocationContent>> = SendStaticLocation(
        chatId,
        location.latitude,
        location.longitude,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    ) as SendMessageRequest<ContentMessage<StaticLocationContent>>
}
