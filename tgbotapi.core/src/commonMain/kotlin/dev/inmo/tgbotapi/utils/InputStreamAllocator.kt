package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.ByteArrayAllocatorSerializer
import io.ktor.util.toByteArray
import io.ktor.utils.io.ByteReadChannel
import kotlinx.coroutines.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

fun interface InputStreamAllocator {
    suspend operator fun invoke(): ByteReadChannel
}

object InputStreamAllocatorSerializer : KSerializer<InputStreamAllocator> {
    override val descriptor: SerialDescriptor = ByteArrayAllocatorSerializer.descriptor

    override fun serialize(encoder: Encoder, value: InputStreamAllocator) {
        TODO("Not yet implemented")
//        ByteArrayAllocatorSerializer.serialize(
//            encoder
//        ) {
//        }
    }

    override fun deserialize(decoder: Decoder): InputStreamAllocator {
        val byteArrayAllocator = ByteArrayAllocatorSerializer.deserialize(decoder)
        return InputStreamAllocator { ByteReadChannel(byteArrayAllocator()) }
    }
}
