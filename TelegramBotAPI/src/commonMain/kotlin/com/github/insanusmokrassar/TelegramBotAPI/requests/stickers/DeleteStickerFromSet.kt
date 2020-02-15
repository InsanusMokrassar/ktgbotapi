package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import com.github.insanusmokrassar.TelegramBotAPI.types.stickerField
import kotlinx.serialization.*
import kotlinx.serialization.internal.BooleanSerializer

@Serializable
data class DeleteStickerFromSet(
    @SerialName(stickerField)
    val sticker: FileId
) : SimpleRequest<Boolean> {
    override fun method(): String = "deleteStickerFromSet"
    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = BooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}



@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.deleteStickerFromSet(
    sticker: FileId
) = execute(
    DeleteStickerFromSet(
        sticker
    )
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.deleteStickerFromSet(
    sticker: Sticker
) = deleteStickerFromSet(
    sticker.fileId
)
