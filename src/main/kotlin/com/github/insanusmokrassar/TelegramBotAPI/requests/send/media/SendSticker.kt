package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

fun SendSticker(
    chatId: ChatIdentifier,
    sticker: InputFile,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
): Request<RawMessage> = SendStickerByFileId(
    chatId,
    sticker as? FileId,
    disableNotification,
    replyToMessageId,
    replyMarkup
).let {
    when (sticker) {
        is MultipartFile -> SendStickerByFile(it, sticker)
        is FileId -> it
    }
}

@Serializable
data class SendStickerByFileId internal constructor(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(stickerField)
    val sticker: FileId? = null,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<RawMessage>, ReplyingMarkupSendMessageRequest<RawMessage> {
    override fun method(): String = "sendSticker"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}

data class SendStickerByFile internal constructor(
    @Transient
    private val sendStickerByFileId: SendStickerByFileId,
    val sticker: MultipartFile
) : MultipartRequest<RawMessage>, Request<RawMessage> by sendStickerByFileId {
    override val mediaMap: Map<String, MultipartFile> = mapOf(stickerField to sticker)
    override val paramsJson: JsonObject = sendStickerByFileId.toJsonWithoutNulls(SendStickerByFileId.serializer())
}


