package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.Request
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.InputMedia.*
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializeOnlySerializerClass
import com.github.insanusmokrassar.TelegramBotAPI.utils.throwRangeError
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.buildJsonArray

fun SendMediaGroup(
    chatId: ChatIdentifier,
    media: List<MediaGroupMemberInputMedia>,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null
): Request<List<MediaGroupMessage>> {
    if (media.size !in mediaCountInMediaGroup) {
        throwRangeError("Count of members in media group", mediaCountInMediaGroup, media.size)
    }

    val files: List<MultipartFile> = media.flatMap {
        listOfNotNull(
            it.file as? MultipartFile,
            if (it is ThumbedInputMedia) {
                it.thumb as? MultipartFile
            } else {
                null
            }
        )
    }

    val data = SendMediaGroupData(
        chatId,
        media,
        disableNotification,
        replyToMessageId
    )

    return if (files.isEmpty()) {
        data
    } else {
        MultipartRequestImpl(
            data,
            SendMediaGroupFiles(files)
        )
    }
}

private val messagesListSerializer: KSerializer<List<MediaGroupMessage>>
    = ListSerializer(TelegramBotAPIMessageDeserializeOnlySerializerClass())

@Serializable
data class SendMediaGroupData internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    val media: List<MediaGroupMemberInputMedia> = emptyList(),
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null
) : DataRequest<List<MediaGroupMessage>>, SendMessageRequest<List<MediaGroupMessage>> {
    @SerialName(mediaField)
    private val convertedMedia: String
        get() = buildJsonArray {
            media.forEach {
                add(it.toJsonWithoutNulls(MediaGroupMemberInputMediaSerializer))
            }
        }.toString()


    override fun method(): String = "sendMediaGroup"
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
    override val resultDeserializer: DeserializationStrategy<List<MediaGroupMessage>>
        get() = messagesListSerializer
}

data class SendMediaGroupFiles internal constructor(
    val files: List<MultipartFile>
) : Files by (files.map { it.fileId to it }.toMap())
