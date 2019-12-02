package com.github.insanusmokrassar.TelegramBotAPI.requests.send.media.base

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject

/**
 * Will be used as SimpleRequest if
 */
class MultipartRequestImpl<D: DataRequest<R>, F: Files, R: Any>(
    val data: D,
    val files: F
) : MultipartRequest<R> {
    override fun method(): String = data.method()
    override val resultDeserializer: DeserializationStrategy<R>
        get() = data.resultDeserializer
    override val paramsJson: JsonObject = data.json()
    override val mediaMap: Map<String, MultipartFile> = files
}
