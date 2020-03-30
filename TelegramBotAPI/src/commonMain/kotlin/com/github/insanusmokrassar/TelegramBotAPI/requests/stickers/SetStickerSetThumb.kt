package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.common.CommonMultipartFileRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts.StickerSetAction
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

fun SetStickerSetThumb(
    stickerSetName: String,
    userId: UserId,
    sticker: InputFile
): Request<Boolean> {
    val data = SetStickerSetThumb(userId, stickerSetName, sticker as? FileId)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(thumbField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class SetStickerSetThumb internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: StickerSetName,
    @SerialName(thumbField)
    val thumb: FileId? = null
) : StickerSetAction {
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "setStickerSetThumb"
}
