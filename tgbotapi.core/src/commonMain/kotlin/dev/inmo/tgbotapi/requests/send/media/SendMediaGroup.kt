package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.requests.send.media.base.*
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.media.*
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
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.jsonPrimitive

const val rawSendingMediaGroupsWarning = "Media groups contains restrictions related to combinations of media" +
    " types. Currently it is possible to combine photo + video OR audio OR documents"

@RiskFeature(rawSendingMediaGroupsWarning)
fun <T : MediaGroupPartContent> SendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
): Request<PossiblySentViaBotCommonMessage<T>> {
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
            }
        )
    }

    val data = SendMediaGroupData(
        chatId,
        media,
        threadId,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply
    )

    return (if (files.isEmpty()) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendMediaGroupFiles(files)
        )
    }) as Request<PossiblySentViaBotCommonMessage<T>>
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
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = SendMediaGroup<AudioContent>(chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

/**
 * Use this method to be sure that you are correctly sending documents media group
 *
 * @see TelegramMediaDocument
 */
@Suppress("NOTHING_TO_INLINE")
inline fun SendDocumentsGroup(
    chatId: ChatIdentifier,
    media: List<DocumentMediaGroupMemberTelegramMedia>,
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = SendMediaGroup<DocumentContent>(chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

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
    threadId: MessageThreadId? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageId? = null,
    allowSendingWithoutReply: Boolean? = null
) = SendMediaGroup<VisualMediaGroupPartContent>(chatId, media, threadId, disableNotification, protectContent, replyToMessageId, allowSendingWithoutReply)

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

@Serializable
data class SendMediaGroupData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    val media: List<MediaGroupMemberTelegramMedia> = emptyList(),
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageId? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null
) : DataRequest<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>>,
    SendMessageRequest<PossiblySentViaBotCommonMessage<MediaGroupContent<MediaGroupPartContent>>> {
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

data class SendMediaGroupFiles internal constructor(
    val files: List<MultipartFile>
) : Files by (files.map { it.fileId to it }.toMap())
