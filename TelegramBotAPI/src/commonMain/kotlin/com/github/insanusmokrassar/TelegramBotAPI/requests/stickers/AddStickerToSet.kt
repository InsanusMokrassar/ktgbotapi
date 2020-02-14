package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.requests.common.CommonMultipartFileRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.stickers.abstracts.StickerSetAction
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.MaskPosition
import com.github.insanusmokrassar.TelegramBotAPI.types.stickers.StickerSet
import kotlinx.serialization.*

fun AddStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: InputFile,
    emojis: String,
    maskPosition: MaskPosition? = null
): Request<Boolean> {
    val data = AddStickerToSet(userId, stickerSetName, emojis, sticker as? FileId, maskPosition)
    return when (sticker) {
        is MultipartFile -> CommonMultipartFileRequest(
            data,
            mapOf(pngStickerField to sticker)
        )
        is FileId -> data
    }
}

@Serializable
data class AddStickerToSet internal constructor(
    @SerialName(userIdField)
    override val userId: UserId,
    @SerialName(nameField)
    override val name: String,
    @SerialName(emojisField)
    override val emojis: String,
    @SerialName(pngStickerField)
    val sticker: FileId? = null,
    @SerialName(maskPositionField)
    override val maskPosition: MaskPosition? = null
) : StickerSetAction {
    init {
        if(emojis.isEmpty()) {
            throw IllegalArgumentException("Emojis must not be empty")
        }
    }

    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    override fun method(): String = "addStickerToSet"
}

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    AddStickerToSet(
        userId, stickerSetName, emojis, sticker, maskPosition
    )
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = execute(
    CommonMultipartFileRequest(
        AddStickerToSet(
            userId, stickerSetName, emojis, null, maskPosition
        ),
        mapOf(pngStickerField to sticker)
    )
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSetName: String,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSetName, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    userId: UserId,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    userId, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: FileId,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)

suspend fun RequestsExecutor.addStickerToSet(
    user: CommonUser,
    stickerSet: StickerSet,
    sticker: MultipartFile,
    emojis: String,
    maskPosition: MaskPosition? = null
) = addStickerToSet(
    user.id, stickerSet.name, sticker, emojis, maskPosition
)
