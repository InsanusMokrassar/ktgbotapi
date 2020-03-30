package com.github.insanusmokrassar.TelegramBotAPI.types.stickers

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PhotoSize
import com.github.insanusmokrassar.TelegramBotAPI.types.files.Sticker
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StickerSet(
    @SerialName(nameField)
    val name: String,
    @SerialName(titleField)
    val title: String,
    @SerialName(stickersField)
    val stickers: List<Sticker>,
    @SerialName(isAnimatedField)
    val isAnimated: Boolean = false,
    @SerialName(containsMasksField)
    val containsMasks: Boolean = false,
    @SerialName(thumbField)
    val thumb: PhotoSize? = null
)
