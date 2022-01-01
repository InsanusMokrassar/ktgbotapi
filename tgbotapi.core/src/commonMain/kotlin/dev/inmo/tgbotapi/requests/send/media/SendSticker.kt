package dev.inmo.tgbotapi.requests.send.media

import dev.inmo.tgbotapi.requests.abstracts.*
import dev.inmo.tgbotapi.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import dev.inmo.tgbotapi.requests.send.abstracts.SendMessageRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import dev.inmo.tgbotapi.types.message.content.media.StickerContent
import dev.inmo.tgbotapi.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

fun SendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    allowSendingWithoutReply: Boolean? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<ContentMessage<StickerContent>> = SendStickerByFileId(
    chatId,
    sticker as? FileId,
    disableNotification,
    protectContent,
    replyToMessageId,
    allowSendingWithoutReply,
    replyMarkup
).let {
    when (sticker) {
        is MultipartFile -> SendStickerByFile(it, sticker)
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
    val sticker: FileId? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(protectContentField)
    override val protectContent: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(allowSendingWithoutReplyField)
    override val allowSendingWithoutReply: Boolean? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<ContentMessage<StickerContent>>, ReplyingMarkupSendMessageRequest<ContentMessage<StickerContent>> {
    override fun method(): String = "sendSticker"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<StickerContent>>
        get() = commonResultDeserializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

data class SendStickerByFile internal constructor(
    @Transient
    private val sendStickerByFileId: SendStickerByFileId,
    val sticker: MultipartFile
) : MultipartRequest<ContentMessage<StickerContent>>, Request<ContentMessage<StickerContent>> by sendStickerByFileId {
    override val mediaMap: Map<String, MultipartFile> = mapOf(stickerField to sticker)
    override val paramsJson: JsonObject = sendStickerByFileId.toJsonWithoutNulls(SendStickerByFileId.serializer())
}
