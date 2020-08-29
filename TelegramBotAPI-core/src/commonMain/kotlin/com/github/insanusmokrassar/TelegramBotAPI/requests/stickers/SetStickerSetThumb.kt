package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.common.CommonMultipartFileRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts.StickerSetAction
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.*

fun SetStickerSetThumb(
    userId: UserId,
    stickerSetName: String,
    thumb: MultipartFile
): Request<Boolean> {
    return CommonMultipartFileRequest(
        SetStickerSetThumb(userId, stickerSetName),
        mapOf(thumbField to thumb)
    )
}

@Serializable
data class SetStickerSetThumb (
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
