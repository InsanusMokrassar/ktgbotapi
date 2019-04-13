package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import kotlinx.serialization.*

@Serializable
data class Sticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    val emoji: String? = null,
    @SerialName(stickerSetNameField)
    val stickerSetName: String? = null,
    @SerialName(maskPositionField)
    val maskPosition: MaskPosition? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, SizedMediaFile, ThumbedMediaFile
