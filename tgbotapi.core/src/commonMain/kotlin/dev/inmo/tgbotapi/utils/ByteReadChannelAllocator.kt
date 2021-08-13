package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.ByteArrayAllocatorSerializer
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

fun interface ByteReadChannelAllocator {
    suspend operator fun invoke(): ByteReadChannel
}

object ByteReadChannelAllocatorDeserializationStrategy : DeserializationStrategy<ByteReadChannelAllocator> {
    override val descriptor: SerialDescriptor = ByteArrayAllocatorSerializer.descriptor

    override fun deserialize(decoder: Decoder): ByteReadChannelAllocator {
        val byteArrayAllocator = ByteArrayAllocatorSerializer.deserialize(decoder)
        return ByteReadChannelAllocator { ByteReadChannel(byteArrayAllocator()) }
    }
}
