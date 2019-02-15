package com.github.insanusmokrassar.TelegramBotAPI.types.stickers

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import kotlinx.serialization.*

@Serializable
data class StickerSet(
    @SerialName(nameField)
    val name: String,
    @SerialName(titleField)
    val title: String,
    @SerialName(stickersField)
    val stickers: List<Sticker>,
    @SerialName(containsMasksField)
    @Optional
    val containsMasks: Boolean = false
)
