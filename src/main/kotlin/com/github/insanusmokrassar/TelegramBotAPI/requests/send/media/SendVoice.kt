package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategy
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
): Request<Message> {
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
) : DataRequest<Message>,
    SendMessageRequest<Message>,
    ReplyingMarkupSendMessageRequest<Message>,
    TextableSendMessageRequest<Message>,
    ThumbedSendMessageRequest<Message>,
    DuratedSendMessageRequest<Message>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendVoice"
    override val resultDeserializer: DeserializationStrategy<Message>
        get() = TelegramBotAPIMessageDeserializationStrategy
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
