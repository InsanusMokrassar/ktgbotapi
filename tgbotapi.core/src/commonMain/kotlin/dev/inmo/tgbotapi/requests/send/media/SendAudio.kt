package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.abstracts.Performerable
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendAudio(
    chatId: ChatIdentifier,
    audio: InputFile,
    thumbnail: InputFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<AudioContent>> {
    val audioAsFile = audio as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendAudioData(
        chatId = chatId,
        audio = audio,
        thumbnail = thumbnail ?.fileId,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        duration = duration,
        performer = performer,
        title = title,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (audioAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(audioAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

fun SendAudio(
    chatId: ChatIdentifier,
    audio: InputFile,
    thumbnail: InputFile? = null,
    entities: List<TextSource>,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    threadId: MessageThreadId? = chatId.threadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<AudioContent>> {
    val audioAsFile = audio as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendAudioData(
        chatId = chatId,
        audio = audio,
        thumbnail = thumbnail ?.fileId,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        duration = duration,
        performer = performer,
        title = title,
        threadId = threadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (audioAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(audioAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<AudioContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendAudioData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(audioField)
    val audio: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(performerField)
    override val performer: String? = null,
    @SerialName(titleField)
    override val title: String? = null,
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
    @SerialName(messageEffectIdField)
    override val effectId: EffectId? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<AudioContent>>,
    SendContentMessageRequest<ContentMessage<AudioContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<AudioContent>>,
    TextableSendMessageRequest<ContentMessage<AudioContent>>,
    ThumbedSendMessageRequest<ContentMessage<AudioContent>>,
    TitledSendMessageRequest<ContentMessage<AudioContent>>,
    DuratedSendMessageRequest<ContentMessage<AudioContent>>,
    Performerable
{
    override val textSources: List<TextSource>? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendAudio"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<AudioContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendAudioFiles internal constructor(
    val audio: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    audioField to audio,
    thumbnailField to thumbnail
)
