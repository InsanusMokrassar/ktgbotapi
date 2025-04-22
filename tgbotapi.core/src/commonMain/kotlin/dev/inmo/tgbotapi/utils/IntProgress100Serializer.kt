package dev.inmo.tgbotapi.utils

import dev.inmo.micro_utils.common.Progress
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object IntProgress100Serializer : KSerializer<Progress> {
    override val descriptor: SerialDescriptor
        get() = Int.serializer().descriptor

    override fun deserialize(decoder: Decoder): Progress {
        return Progress(
            decoder.decodeInt(),
            100,
        )
    }

    override fun serialize(
        encoder: Encoder,
        value: Progress,
    ) {
        encoder.encodeInt(
            value.of100Int,
        )
    }
}
