package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.common.CommonMultipartFileRequest
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.StickerContent
import kotlinx.serialization.*

fun SendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    threadId: MessageThreadId? = chatId.threadId,
    emoji: String? = null,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<StickerContent>> = SendStickerByFileId(
    chatId,
    sticker,
    threadId,
    disableNotification,
    protectContent,
    replyParameters,
    replyMarkup
).let {
    when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            it,
            listOf(sticker).associateBy { it.fileId }
        )
        is FileId -> it
    }
}

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<StickerContent>>
    = TelegramBotAPIMessageDeserializationStrategyClass()

@Serializable
data class SendStickerByFileId internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(stickerField)
    val sticker: InputFile,
    @SerialName(messageThreadIdField)
    override val threadId: MessageThreadId? = chatId.threadId,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyParametersField)
    override val replyParameters: ReplyParameters? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<StickerContent>>, ReplyingMarkupSendMessageRequest<ContentMessage<StickerContent>> {
    override fun method(): String = "sendSticker"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<StickerContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
