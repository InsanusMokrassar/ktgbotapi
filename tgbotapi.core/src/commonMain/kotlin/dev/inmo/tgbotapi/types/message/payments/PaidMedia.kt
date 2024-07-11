package dev.inmo.tgbotapi.types.message.payments

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonElement

@Serializable(PaidMedia.Companion::class)
sealed interface PaidMedia {
    val type: String

    @Serializable
    data class Preview(
        @SerialName(widthField)
        val width: Int? = null,
        @SerialName(heightField)
        val height: Int? = null,
        @SerialName(durationField)
        val duration: Int? = null
    ) : PaidMedia {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            val type: String = "preview"
        }
    }

    @Serializable
    data class Photo(
        @SerialName(photoField)
        val photo: PhotoFile
    ) : PaidMedia {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            val type: String = "photo"
        }
    }

    @Serializable
    data class Video(
        @SerialName(videoField)
        val video: VideoFile
    ) : PaidMedia {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            val type: String = "video"
        }
    }

    @Serializable(Companion::class)
    data class Unknown(
        @SerialName(typeField)
        override val type: String,
        val raw: JsonElement?
    ) : PaidMedia

    companion object : KSerializer<PaidMedia> {
        @Serializable
        private class Surrogate(
            @SerialName(typeField)
            val type: String,
            @SerialName(widthField)
            val width: Int? = null,
            @SerialName(heightField)
            val height: Int? = null,
            @SerialName(durationField)
            val duration: Int? = null,
            @SerialName(photoField)
            val photo: PhotoFile? = null,
            @SerialName(videoField)
            val video: VideoFile? = null
        )

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): PaidMedia {
            val (data, json) = decoder.decodeDataAndJson(Surrogate.serializer())
            val unknown by lazy {
                Unknown(data.type, json)
            }
            return when (data.type) {
                Preview.type -> Preview(
                    data.width,
                    data.height,
                    data.duration
                )
                Photo.type -> Photo(
                    data.photo ?: return unknown
                )
                Video.type -> Video(
                    data.video ?: return unknown
                )
                else -> unknown
            }
        }

        override fun serialize(encoder: Encoder, value: PaidMedia) {
            if (value is Unknown && value.raw != null) {
                JsonElement.serializer().serialize(encoder, value.raw)
            } else {
                val surrogate = Surrogate(
                    value.type,
                    (value as? Preview) ?.width,
                    (value as? Preview) ?.height,
                    (value as? Preview) ?.duration,
                    (value as? Photo) ?.photo,
                    (value as? Video) ?.video,
                )
                Surrogate.serializer().serialize(encoder, surrogate)
            }
        }

    }
}
