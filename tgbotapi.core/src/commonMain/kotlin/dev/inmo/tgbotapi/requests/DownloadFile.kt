package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.Request
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ByteArraySerializer

class DownloadFile(
    val filePath: String,
) : Request<ByteArray> {
    override fun method(): String = filePath

    override val resultDeserializer: DeserializationStrategy<ByteArray>
        get() = ByteArraySerializer()
}
