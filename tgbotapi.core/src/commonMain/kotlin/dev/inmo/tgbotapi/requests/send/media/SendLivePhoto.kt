@file:Suppress("FunctionName")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.*
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.textsources.TextSourcesList
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.parseModeField
import dev.inmo.tgbotapi.types.message.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.LivePhotoContent
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeString
import dev.inmo.tgbotapi.utils.mapOfNotNull
import dev.inmo.tgbotapi.utils.throwRangeError
import kotlinx.serialization.*

fun SendLivePhoto(
    chatId: ChatIdentifier,
    livePhoto: InputFile,
    photo: InputFile,
    text: String? = null,
    parseMode: ParseMode? = null,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<LivePhotoContent>> {
    val livePhotoAsFile = livePhoto as? MultipartFile
    val photoAsFile = photo as? MultipartFile

    val data = SendLivePhotoData(
        chatId = chatId,
        livePhoto = livePhoto,
        photo = photo,
        text = text,
        parseMode = parseMode,
        rawEntities = null,
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        receiverUserId = receiverUserId,
        callbackQueryId = callbackQueryId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (livePhotoAsFile == null && photoAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(livePhotoAsFile, photoAsFile).associateBy { it.fileId }
        )
    }
}

fun SendLivePhoto(
    chatId: ChatIdentifier,
    livePhoto: InputFile,
    photo: InputFile,
    entities: TextSourcesList,
    showCaptionAboveMedia: Boolean = false,
    spoilered: Boolean = false,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    receiverUserId: UserId? = chatId.receiverUser,
    callbackQueryId: CallbackQueryId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    suggestedPostParameters: SuggestedPostParameters? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ChatContentMessage<LivePhotoContent>> {
    val livePhotoAsFile = livePhoto as? MultipartFile
    val photoAsFile = photo as? MultipartFile

    val data = SendLivePhotoData(
        chatId = chatId,
        livePhoto = livePhoto,
        photo = photo,
        text = entities.makeString(),
        parseMode = null,
        rawEntities = entities.toRawMessageEntities(),
        showCaptionAboveMedia = showCaptionAboveMedia,
        spoilered = spoilered,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        receiverUserId = receiverUserId,
        callbackQueryId = callbackQueryId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        suggestedPostParameters = suggestedPostParameters,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )

    return if (livePhotoAsFile == null && photoAsFile == null) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            listOfNotNull(livePhotoAsFile, photoAsFile).associateBy { it.fileId }
        )
    }
}

private val commonResultDeserializer: DeserializationStrategy<ChatContentMessage<LivePhotoContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@ConsistentCopyVisibility
@Serializable
data class SendLivePhotoData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(livePhotoField)
    val livePhoto: InputFile,
    @SerialName(photoField)
    val photo: InputFile,
    @SerialName(captionField)
    override val text: String? = null,
    @SerialName(parseModeField)
    override val parseMode: ParseMode? = null,
    @SerialName(captionEntitiesField)
    private val rawEntities: List<RawMessageEntity>? = null,
    @SerialName(showCaptionAboveMediaField)
    override val showCaptionAboveMedia: Boolean = false,
    @SerialName(hasSpoilerField)
    override val spoilered: Boolean = false,
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
    @SerialName(receiverUserIdField)
    override val receiverUserId: UserId? = chatId.receiverUser,
    @SerialName(callbackQueryIdField)
    override val callbackQueryId: CallbackQueryId? = null,
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
) : DataRequest<ChatContentMessage<LivePhotoContent>>,
    SendContentMessageRequest<ChatContentMessage<LivePhotoContent>>,
    ReplyingMarkupSendMessageRequest<ChatContentMessage<LivePhotoContent>>,
    TextableSendMessageRequest<ChatContentMessage<LivePhotoContent>>,
    WithCustomizableCaptionRequest<ChatContentMessage<LivePhotoContent>>,
    OptionallyWithSpoilerRequest,
    OptionallyEphemeralSendRequest
{
    override val textSources: TextSourcesList? by lazy {
        rawEntities ?.asTextSources(text ?: return@lazy null)
    }

    init {
        text ?.let {
            if (it.length !in captionLength) {
                throwRangeError("Caption length", captionLength, it.length)
            }
        }
    }

    override fun method(): String = "sendLivePhoto"
    override val resultDeserializer: DeserializationStrategy<ChatContentMessage<LivePhotoContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendLivePhotoFiles internal constructor(
    val livePhoto: MultipartFile? = null,
    val photo: MultipartFile? = null
) : Files by mapOfNotNull(
    livePhotoField to livePhoto,
    photoField to photo
)
