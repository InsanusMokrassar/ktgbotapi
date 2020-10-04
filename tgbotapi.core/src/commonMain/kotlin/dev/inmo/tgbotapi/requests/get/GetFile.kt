package dev.inmo.tgbotapi.requests.get

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.files.PathedFile
import dev.inmo.tgbotapi.types.files.abstracts.fileIdField
import kotlinx.serialization.*

@Serializable
data class GetFile(
    @SerialName(fileIdField)
    val fileId: FileId
): SimpleRequest<PathedFile> {
    override fun method(): String = "getFile"
    override val resultDeserializer: DeserializationStrategy<PathedFile>
        get() = PathedFile.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
