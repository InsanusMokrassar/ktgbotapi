package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.InputStreamAllocator
import dev.inmo.tgbotapi.utils.InputStreamAllocatorSerializer
import kotlinx.serialization.DeserializationStrategy

class DownloadFileStream(
    val filePath: String
) : Request<InputStreamAllocator> {
    override fun method(): String = filePath
    override val resultDeserializer: DeserializationStrategy<InputStreamAllocator>
        get() = InputStreamAllocatorSerializer

}
