package com.github.insanusmokrassar.TelegramBotAPI.types.files

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Sticker(
    @SerialName(fileIdField)
    override val fileId: FileId,
    @SerialName(fileUniqueIdField)
    override val fileUniqueId: FileUniqueId,
    @SerialName(widthField)
    override val width: Int,
    @SerialName(heightField)
    override val height: Int,
    @SerialName(thumbField)
    override val thumb: PhotoSize? = null,
    @SerialName(emojiField)
    val emoji: String? = null,
    @SerialName(stickerSetNameField)
    val stickerSetName: StickerSetName? = null,
    @SerialName(isAnimatedField)
    val isAnimated: Boolean = false,
    @SerialName(maskPositionField)
    val maskPosition: MaskPosition? = null,
    @SerialName(fileSizeField)
    override val fileSize: Long? = null
) : TelegramMediaFile, SizedMediaFile, ThumbedMediaFile
