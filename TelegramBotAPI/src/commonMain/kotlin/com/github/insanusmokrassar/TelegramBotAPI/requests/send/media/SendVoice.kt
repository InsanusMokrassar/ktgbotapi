package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.files.AudioFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.VoiceContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendVoice(
    chatId: ChatIdentifier,
    voice: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VoiceContent>> {
    val voiceAsFileId = (voice as? FileId) ?.fileId
    val voiceAsFile = voice as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendVoiceData(
        chatId,
        voiceAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        duration,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (voiceAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendVoiceFiles(voiceAsFile, thumbAsFile)
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
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VoiceContent>>,
    SendMessageRequest<ContentMessage<VoiceContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VoiceContent>>,
    TextableSendMessageRequest<ContentMessage<VoiceContent>>,
    ThumbedSendMessageRequest<ContentMessage<VoiceContent>>,
    DuratedSendMessageRequest<ContentMessage<VoiceContent>>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
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

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: FileId,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendVoiceData(
        chatId,
        voice.fileId,
        thumb ?.fileId,
        text,
        parseMode,
        duration,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: AudioFile,
    thumb: PhotoSize? = voice.thumb,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice.fileId, thumb ?.fileId, text, parseMode, voice.duration, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: MultipartFile,
    thumb: FileId? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVoiceData(
            chatId, null, thumb ?.fileId, text, parseMode, duration, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVoiceFiles(voice)
    )
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: MultipartFile,
    thumb: MultipartFile? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVoiceData(
            chatId, null, null, text, parseMode, duration, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVoiceFiles(voice, thumb)
    )
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: FileId,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    MultipartRequestImpl(
        SendVoiceData(
            chatId, voice.fileId, null, text, parseMode, duration, disableNotification, replyToMessageId, replyMarkup
        ),
        SendVoiceFiles(null, thumb)
    )
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: MultipartFile,
    thumb: PhotoSize? = null,
    text: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice, thumb ?.fileId , text, parseMode, duration, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendVoice(
    chatId: ChatIdentifier,
    voice: AudioFile,
    thumb: MultipartFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendVoice(
    chatId, voice.fileId, thumb, text, parseMode, voice.duration, disableNotification, replyToMessageId, replyMarkup
)

