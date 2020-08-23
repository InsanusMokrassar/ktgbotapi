package com.github.insanusmokrassar.TelegramBotAPI.requests.stickers

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.File
import com.github.insanusmokrassar.TelegramBotAPI.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

@Serializable
data class UploadStickerFile(
    @SerialName(userIdField)
    val userId: UserId,
    @Transient
    val sticker: MultipartFile = throw IllegalStateException("Detected autocreating try: this class can't be deserialized")
): MultipartRequest<File> {
    init {
        // TODO:: add check of width/height of image and type of file - it must be png with max side length is 512px
    }

    override fun method(): String = "uploadStickerFile"
    @Transient
    override val mediaMap: Map<String, MultipartFile> = mapOf(pngStickerField to sticker)
    @Transient
    override val paramsJson: JsonObject = toJsonWithoutNulls(serializer())
    override val resultDeserializer: DeserializationStrategy<File>
        get() = File.serializer()
}
