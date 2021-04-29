package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.CommonAbstracts.makeString
import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.*
import dev.inmo.tgbotapi.types.ParseMode.ParseMode
import dev.inmo.tgbotapi.types.ParseMode.parseModeField
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.media.VoiceContent
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendVoice(
    chatId: ChatIdentifier,
    voice: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VoiceContent>> {
    val voiceAsFileId = (voice as? FileId) ?.fileId
    val voiceAsFile = voice as? MultipartFile

    val data = SendVoiceData(
        chatId,
        voiceAsFileId,
        text,
        parseMode,
        null,
        duration,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    return if (voiceAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVoiceFiles(voiceAsFile)
        )
    }
}

fun SendVoice(
    chatId: ChatIdentifier,
    voice: InputFile,
    entities: List<TextSource>,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VoiceContent>> {
    val voiceAsFileId = (voice as? FileId) ?.fileId
    val voiceAsFile = voice as? MultipartFile

    val data = SendVoiceData(
        chatId,
        voiceAsFileId,
        entities.makeString(),
        null,
        entities.toRawMessageEntities(),
        duration,
        disableNotification,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )

    return if (voiceAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVoiceFiles(voiceAsFile)
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VoiceContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendVoiceData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(voiceField)
    val voice: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VoiceContent>>,
    SendMessageRequest<ContentMessage<VoiceContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VoiceContent>>,
    TextableSendMessageRequest<ContentMessage<VoiceContent>>,
    DuratedSendMessageRequest<ContentMessage<VoiceContent>>
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

    override fun method(): String = "sendVoice"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VoiceContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendVoiceFiles internal constructor(
    val voice: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    voiceField to voice,
    thumbField to thumb
)
