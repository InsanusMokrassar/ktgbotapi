@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.types.media.TelegramPaidMedia
import dev.inmo.tgbotapi.types.message.content.PaidMediaInfoContent
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.media.CoveredTelegramMedia
import dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendPaidMedia(
    chatId: ChatIdentifier,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    text: String? = null,
    parseMode: ParseMode? = null,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PaidMediaInfoContent>> {
    val data = SendPaidMediaData(
        chatId = chatId,
        starCount = starCount,
        media = media,
        payload = payload,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    val files: List<MultipartFile> = media.flatMap {
        listOfNotNull(
            it.file as? MultipartFile,
            if (it is ThumbedTelegramMedia) {
                it.thumb as? MultipartFile
            } else {
                null
            },
            if (it is CoveredTelegramMedia) {
                it.cover as? MultipartFile
            } else {
                null
            }
        )
    }

    return if (files.isNotEmpty()) {
        CommonMultipartFileRequest(
            data,
            files.associateBy { it.fileId }
        )
    } else {
        data
    }
}

fun SendPaidMedia(
    chatId: ChatIdentifier,
    starCount: Int,
    media: List<TelegramPaidMedia>,
    entities: TextSourcesList,
    payload: PaidMediaPayload? = null,
    showCaptionAboveMedia: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<PaidMediaInfoContent>> {
    val data = SendPaidMediaData(
        chatId = chatId,
        starCount = starCount,
        media = media,
        payload = payload,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    val files: List<MultipartFile> = media.flatMap {
        listOfNotNull(
            it.file as? MultipartFile,
            if (it is ThumbedTelegramMedia) {
                it.thumb as? MultipartFile
            } else {
                null
            },
            if (it is CoveredTelegramMedia) {
                it.cover as? MultipartFile
            } else {
                null
            }
        )
    }

    return if (files.isNotEmpty()) {
        CommonMultipartFileRequest(
            data,
            files.associateBy { it.fileId }
        )
    } else {
        data
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PaidMediaInfoContent>>
        = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendPaidMediaData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(starCountField)
    val starCount: Int,
    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @SerialName(mediaField)
    val media: List<TelegramPaidMedia>,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(payloadField)
    val payload: PaidMediaPayload? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(businessConnectionIdField)
    override val businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(allowPaidBroadcastField)
    override val allowPaidBroadcast: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<PaidMediaInfoContent>>,
    SendContentMessageRequest<ContentMessage<PaidMediaInfoContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PaidMediaInfoContent>>,
    TextableSendMessageRequest<ContentMessage<PaidMediaInfoContent>>,
    WithCustomizableCaptionRequest<ContentMessage<PaidMediaInfoContent>>
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }
    override val effectId: EffectId?
        get() = null

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendPaidMedia"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<PaidMediaInfoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendPaidMediaFiles internal constructor(
    val photo: MultipartFile
) : Files by mapOf(
    photoField to photo
)
