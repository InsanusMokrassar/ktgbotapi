package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.Performerable
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import kotlinx.serialization.*

fun SendAudio(
    chatId: ChatIdentifier,
    audio: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    performer: String? = null,
    title: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<RawMessage> {
    val audioAsFileId = (audio as? FileId) ?.fileId
    val audioAsFile = audio as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendAudioData(
        chatId,
        audioAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        duration,
        performer,
        title,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (audioAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendAudioFiles(audioAsFile, thumbAsFile)
        )
    }
}

@Serializable
data class SendAudioData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(audioField)
    @Optional
    val audio: String? = null,
    @SerialName(thumbField)
    @Optional
    override val thumb: String? = null,
    @SerialName(captionField)
    @Optional
    override val text: String? = null,
    @SerialName(parseModeField)
    @Optional
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    @Optional
    override val duration: Long? = null,
    @SerialName(performerField)
    @Optional
    override val performer: String? = null,
    @SerialName(titleField)
    @Optional
    override val title: String? = null,
    @SerialName(disableNotificationField)
    @Optional
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    @Optional
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    @Optional
    override val replyMarkup: KeyboardMarkup? = null
) : Data<RawMessage>,
    SendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage>,
    TextableSendMessageRequest<RawMessage>,
    ThumbedSendMessageRequest<RawMessage>,
    TitledSendMessageRequest<RawMessage>,
    DuratedSendMessageRequest<RawMessage>,
    Performerable
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendAudio"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}

data class SendAudioFiles internal constructor(
    val audio: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    audioField to audio,
    thumbField to thumb
)
