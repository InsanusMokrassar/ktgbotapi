package com.github.insanusmokrassar.TelegramBotAPI.requests.get

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.FileId
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.files.PathedFile
import com.github.insanusmokrassar.TelegramBotAPI.types.files.abstracts.fileIdField
import kotlinx.serialization.*

@Serializable
data class GetFile(
    @SerialName(fileIdField)
    val fileId: FileId
): SimpleRequest<PathedFile> {
    override fun method(): String = "getFile"
    override fun resultDeserializer(): KSerializer<PathedFile> = PathedFile.serializer()
}
