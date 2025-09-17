package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.SuggestedPostParameters
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.VideoNoteContent
import dev.inmo.tgbotapi.utils.mapOfNotNull
import kotlinx.serialization.*

@Suppress("FunctionName")
fun SendVideoNote(
    chatId: ChatIdentifier,
    videoNote: InputFile,
    thumbnail: InputFile? = null,
    duration: Long? = null,
    size: Int? = null, // in documentation - length (size of video side)
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<VideoNoteContent>> {
    val videoNoteAsFile = videoNote as? MultipartFile
    val thumbAsFile = thumbnail as? MultipartFile

    val data = SendVideoNoteData(
        chatId = chatId,
        videoNote = videoNote,
        thumbnail = thumbnail ?.fileId,
        duration = duration,
        width = size,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (videoNoteAsFile == null && thumbAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(videoNoteAsFile, thumbAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<VideoNoteContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendVideoNoteData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(videoNoteField)
    val videoNote: InputFile,
    @SerialName(thumbnailField)
    override val thumbnail: String? = null,
    @SerialName(durationField)
    override val duration: Long? = null,
    @SerialName(lengthField)
    override val width: Int? = null,
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
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
    @SerialName(suggestedPostParametersField)
    override val suggestedPostParameters: SuggestedPostParameters? = null,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : DataRequest<ContentMessage<VideoNoteContent>>,
    SendContentMessageRequest<ContentMessage<VideoNoteContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<VideoNoteContent>>,
    ThumbedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    DuratedSendMessageRequest<ContentMessage<VideoNoteContent>>,
    SizedSendMessageRequest<ContentMessage<VideoNoteContent>>
{
    override val height: Int?
        get() = width

    override fun method(): String = "sendVideoNote"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<VideoNoteContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendVideoNoteFiles internal constructor(
    val videoNote: MultipartFile? = null,
    val thumbnail: MultipartFile? = null
) : Files by mapOfNotNull(
    videoNoteField to videoNote,
    thumbnailField to thumbnail
)
