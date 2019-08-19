package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.stickerField
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class DeleteStickerFromSet(
    @SerialName(stickerField)
    val sticker: FileId
) : SimpleRequest<Boolean> {
    override fun method(): String = "deleteStickerFromSet"
    override fun resultDeserializer(): KSerializer<Boolean> = BooleanSerializer
}
