@file:Suppress("FunctionName", "DuplicatedCode")

package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendContentMessageRequest
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.media.*
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializerClass
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.utils.*
import dev.inmo.tgbotapi.utils.extensions.asMediaGroupMessage
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.buildJsonArray

const val rawSendingMediaGroupsWarning = "Media groups contains restrictions related to combinations of media" +
    " types. Currently it is possible to combine photo + video OR audio OR documents"

@RiskFeature(rawSendingMediaGroupsWarning)
fun <T : MediaGroupPartContent> SendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
): Request<ContentMessage<MediaGroupContent<T>>> {
    if (media.size !in mediaCountInMediaGroup) {
        throwRangeError("Count of members in media group", mediaCountInMediaGroup, media.size)
    }

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

    val data = SendMediaGroupData(
        chatId = chatId,
        media = media,
        threadId = threadId,
        directMessageThreadId = directMessageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        allowPaidBroadcast = allowPaidBroadcast,
        effectId = effectId,
        replyParameters = replyParameters
    )

    @Suppress("UNCHECKED_CAST")
    return (if (files.isEmpty()) {
        data
    } else {
        CommonMultipartFileRequest(
            data,
            files.associateBy { it.fileId }
        )
    }) as Request<ContentMessage<MediaGroupContent<T>>>
}

/**
 * Use this method to be sure that you are correctly sending playlist with audios
 *
 * @see TelegramMediaAudio
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SendPlaylist(
    chatId: ChatIdentifier,
    media: List<AudioMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?/* = chatId.directMessageThreadId*/,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = SendMediaGroup<AudioContent>(
    chatId = chatId,
    media = media,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters
)

/**
 * Use this method to be sure that you are correctly sending documents media group
 *
 * @see TelegramMediaDocument
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?/* = chatId.directMessageThreadId*/,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null
) = SendMediaGroup<DocumentContent>(
    chatId = chatId,
    media = media,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters
)

/**
 * Use this method to be sure that you are correctly sending visual media group
 *
 * @see TelegramMediaPhoto
 * @see TelegramMediaVideo
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SendVisualMediaGroup(
    chatId: ChatIdentifier,
    media: List<VisualMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = chatId.threadId,
    directMessageThreadId: DirectMessageThreadId?/* = chatId.directMessageThreadId*/,
    businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    allowPaidBroadcast: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
) = SendMediaGroup<VisualMediaGroupPartContent>(
    chatId = chatId,
    media = media,
    threadId = threadId,
    directMessageThreadId = directMessageThreadId,
    businessConnectionId = businessConnectionId,
    disableNotification = disableNotification,
    protectContent = protectContent,
    allowPaidBroadcast = allowPaidBroadcast,
    effectId = effectId,
    replyParameters = replyParameters
)

private object MessagesListSerializer: KSerializer<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>> {
    private val serializer = ListSerializer(TelegramBotAPIMessageDeserializeOnlySerializerClass<PossiblySentViaBotCommonMessage<MediaGroupPartContent>>())
    override val descriptor: SerialDescriptor = serializer.descriptor

    override fun deserialize(decoder: Decoder): PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>> {
        val messages = serializer.deserialize(decoder)
        return messages.asMediaGroupMessage()
    }

    override fun serialize(encoder: Encoder, value: PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>) {
        serializer.serialize(encoder, value.content.group.map { it.sourceMessage })
    }

}

@ConsistentCopyVisibility
@Serializable
data class SendMediaGroupData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    val media: List<MediaGroupMemberTelegramMedia> = emptyList(),
    @OptIn(ExperimentalSerializationApi::class)
    @SerialName(messageThreadIdField)
    @EncodeDefault
    override val threadId: MessageThreadId? = chatId.threadId,
    @OptIn(ExperimentalSerializationApi::class)
    @EncodeDefault
    @SerialName(directMessagesTopicIdField)
    override val directMessageThreadId: DirectMessageThreadId?,// = chatId.directMessageThreadId
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
) : DataRequest<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>>,
    SendContentMessageRequest<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>> {
    @Suppress("unused")
    @SerialName(mediaField)
    private val convertedMedia: String
        get() = buildJsonArray {
            media.forEach {
                add(it.toJsonWithoutNulls(MediaGroupMemberTelegramMediaSerializer))
            }
        }.toString()


    override fun method(): String = "sendMediaGroup"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>>
        get() = MessagesListSerializer
}

@Suppress("unused")
@ConsistentCopyVisibility
data class SendMediaGroupFiles internal constructor(
    val files: List<MultipartFile>
) : Files by (files.associateBy { it.fileId })
