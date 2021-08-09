package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.ByteArrayAllocatorSerializer
import io.ktor.utils.io.ByteReadChannel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

fun interface ByteReadChannelAllocator {
    suspend operator fun invoke(): ByteReadChannel
}

object ByteReadChannelAllocatorSerializer : KSerializer<ByteReadChannelAllocator> {
    override val descriptor: SerialDescriptor = ByteArrayAllocatorSerializer.descriptor

    override fun serialize(encoder: Encoder, value: ByteReadChannelAllocator) {
        TODO("Not yet implemented")
//        ByteArrayAllocatorSerializer.serialize(
//            encoder
//        ) {
//        }
    }

    override fun deserialize(decoder: Decoder): ByteReadChannelAllocator {
        val byteArrayAllocator = ByteArrayAllocatorSerializer.deserialize(decoder)
        return ByteReadChannelAllocator { ByteReadChannel(byteArrayAllocator()) }
    }
}
