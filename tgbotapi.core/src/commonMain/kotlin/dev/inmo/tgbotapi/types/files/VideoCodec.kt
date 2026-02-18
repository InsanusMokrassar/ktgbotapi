package dev.inmo.tgbotapi.types.files

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(VideoCodec.Serializer::class)
sealed interface VideoCodec {
    val name: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(VideoCodec.Serializer::class)
    data object H264 : VideoCodec { override val name = "h264" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(VideoCodec.Serializer::class)
    data object H265 : VideoCodec { override val name = "h265" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(VideoCodec.Serializer::class)
    data object AV1 : VideoCodec { override val name = "av01" }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(VideoCodec.Serializer::class)
    data class Custom(
        override val name: String
    ) : VideoCodec

    object Serializer : KSerializer<VideoCodec> {
        override val descriptor: SerialDescriptor
            get() = String.serializer().descriptor

        override fun serialize(
            encoder: Encoder,
            value: VideoCodec
        ) {
            encoder.encodeString(value.name)
        }

        override fun deserialize(decoder: Decoder): VideoCodec {
            return when (val name = decoder.decodeString()) {
                H264.name -> H264
                H265.name -> H265
                AV1.name -> AV1
                else -> Custom(name)
            }
        }

    }
}