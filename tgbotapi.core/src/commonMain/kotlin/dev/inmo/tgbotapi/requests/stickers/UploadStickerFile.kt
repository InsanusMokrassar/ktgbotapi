package dev.inmo.tgbotapi.requests.stickers

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.requests.abstracts.MultipartRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.File
import dev.inmo.tgbotapi.utils.toJsonWithoutNulls
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

@Serializable
data class UploadStickerFile(
    @SerialName(userIdField)
    val userId: UserId,
    @Transient
    val sticker: MultipartFile = throw IllegalStateException("Detected autocreating try: this class can't be deserialized"),
    @SerialName(stickerFormatField)
    val stickerFormat: StickerFormat,
) : MultipartRequest<File> {
    init {
        // TODO:: add check of width/height of image and type of file - it must be png with max side length is 512px
    }

    override fun method(): String = "uploadStickerFile"

    @Transient
    override val mediaMap: Map<String, MultipartFile> = mapOf(stickerField to sticker)

    @Transient
    override val paramsJson: JsonObject = toJsonWithoutNulls(serializer())
    override val resultDeserializer: DeserializationStrategy<File>
        get() = File.serializer()
}
