package dev.inmo.tgbotapi.requests.send.media.base

import dev.inmo.tgbotapi.requests.abstracts.*
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonObject

class MultipartRequestImpl<D : DataRequest<R>, F : Files, R : Any>(
    val data: D,
    val files: F,
) : MultipartRequest<R> {
    override fun method(): String = data.method()

    override val resultDeserializer: DeserializationStrategy<R>
        get() = data.resultDeserializer
    override val paramsJson: JsonObject = data.json()
    override val mediaMap: Map<String, MultipartFile> = files
}
