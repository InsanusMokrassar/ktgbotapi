package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.ParseMode
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.parseModeField
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.media.AnimationContent
import com.github.insanusmokrassar.TelegramBotAPI.utils.mapOfNotNull
import com.github.insanusmokrassar.TelegramBotAPI.utils.throwRangeError
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
): Request<ContentMessage<AnimationContent>> {
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

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<AnimationContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

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
) : DataRequest<ContentMessage<AnimationContent>>,
    SendMessageRequest<ContentMessage<AnimationContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<AnimationContent>>,
    TextableSendMessageRequest<ContentMessage<AnimationContent>>,
    ThumbedSendMessageRequest<ContentMessage<AnimationContent>>,
    DuratedSendMessageRequest<ContentMessage<AnimationContent>>,
    SizedSendMessageRequest<ContentMessage<AnimationContent>>
{
    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendAnimation"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<AnimationContent>>
        get() = commonResultDeserializer
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
