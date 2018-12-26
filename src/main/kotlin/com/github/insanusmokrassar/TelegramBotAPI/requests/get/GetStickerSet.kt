package com.github.insanusmokrassar.TelegramBotAPI.requests.get

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.stickerSetNameField
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetStickerSet(
    @SerialName(stickerSetNameField)
    val name: String
): SimpleRequest<StickerSet> {
    override fun method(): String = "getStickerSet"
    override fun resultSerializer(): KSerializer<StickerSet> = StickerSet.serializer()
}
