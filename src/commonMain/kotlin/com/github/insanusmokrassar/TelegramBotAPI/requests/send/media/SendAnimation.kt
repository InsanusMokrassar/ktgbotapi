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

fun SendAnimation(
    chatId: ChatIdentifier,
    animation: InputFile,
    thumb: InputFile? = null,
    caption: String? = null,
    parseMode: ParseMode? = null,
    duration: Long? = null,
    width: Int? = null,
    height: Int? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<Message> {
    val animationAsFileId = (animation as? FileId) ?.fileId
    val animationAsFile = animation as? MultipartFile
    val thumbAsFileId = (thumb as? FileId) ?.fileId
    val thumbAsFile = thumb as? MultipartFile

    val data = SendAnimationData(
        chatId,
        animationAsFileId,
        thumbAsFileId,
        caption,
        parseMode,
        duration,
        width,
        height,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )

    return if (animationAsFile == null && thumbAsFile == null) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendAnimationFiles(animationAsFile, thumbAsFile)
        )
    }
}

@Serializable
data class SendAnimationData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(animationField)
    val animation: String? = null,
    @SerialName(thumbField)
    override val thumb: String? = null,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(widthField)
    override val width: Int? = null,
    @SerialName(heightField)
    override val height: Int? = null,
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
    DuratedSendMessageRequest<Message>,
    SizedSendMessageRequest<Message>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throw IllegalArgumentException("Caption must be in $captionLength range")
            }
        }
    }

    override fun method(): String = "sendAnimation"
    override val resultDeserializer: DeserializationStrategy<Message>
        get() = TelegramBotAPIMessageDeserializationStrategy
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendAnimationFiles internal constructor(
    val animation: MultipartFile? = null,
    val thumb: MultipartFile? = null
) : Files by mapOfNotNull(
    animationField to animation,
    thumbField to thumb
)
