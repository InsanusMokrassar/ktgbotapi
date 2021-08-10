package dev.inmo.tgbotapi.requests

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocator
import dev.inmo.tgbotapi.utils.ByteReadChannelAllocatorDeserializationStrategy
import kotlinx.serialization.DeserializationStrategy

class DownloadFileStream(
    val filePath: String
) : Request<ByteReadChannelAllocator> {
    override fun method(): String = filePath
    override val resultDeserializer: DeserializationStrategy<ByteReadChannelAllocator>
        get() = ByteReadChannelAllocatorDeserializationStrategy

}
