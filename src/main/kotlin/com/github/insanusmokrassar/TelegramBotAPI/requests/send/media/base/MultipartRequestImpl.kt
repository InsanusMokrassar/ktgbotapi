package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartFile
import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.MultipartRequest
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonObject

/**
 * Will be used as SimpleRequest if
 */
class MultipartRequestImpl<D: DataRequest<R>, F: Files, R: Any>(
    val data: D,
    val files: F
) : MultipartRequest<R> {
    override fun method(): String = data.method()
    override fun resultDeserializer(): DeserializationStrategy<R> = data.resultDeserializer()
    override val paramsJson: JsonObject = data.json()
    override val mediaMap: Map<String, MultipartFile> = files
}
