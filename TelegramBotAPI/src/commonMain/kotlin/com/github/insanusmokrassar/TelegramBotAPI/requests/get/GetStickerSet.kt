package com.github.insanusmokrassar.TelegramBotAPI.requests.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import com.github.insanusmokrassar.TelegramBotAPI.types.stickerSetNameField
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet
import kotlinx.serialization.*

@Serializable
data class GetStickerSet(
    @SerialName(stickerSetNameField)
    val name: String
): SimpleRequest<StickerSet> {
    override fun method(): String = "getStickerSet"
    override val resultDeserializer: DeserializationStrategy<StickerSet>
        get() = StickerSet.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getStickerSet(
    name: String
) = execute(
    GetStickerSet(name)
)

@Deprecated("Deprecated due to extracting into separated library")
suspend fun RequestsExecutor.getStickerSet(
    sticker: Sticker
) = getStickerSet(
    sticker.stickerSetName ?: error("Sticker must contains stickerSetName to be correctly used in getStickerSet method")
)
