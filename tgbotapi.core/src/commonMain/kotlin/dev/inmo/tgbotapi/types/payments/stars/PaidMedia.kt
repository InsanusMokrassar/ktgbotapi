package dev.inmo.tgbotapi.types.payments.stars

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PhotoSize
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
        val photo: Photo
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
        val video: Video
    ) : PaidMedia {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            val type: String = "video"
        }
    }

    @Serializable
    data class Video(
        @SerialName(videoField)
        val video: Video
    ) : PaidMedia {
        @EncodeDefault
        @SerialName(typeField)
        override val type: String = Companion.type

        companion object {
            val type: String = "video"
        }
    }

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
            val photo: Photo? = null,
            @SerialName(videoField)
            val video: Video? = null
        )

        override val descriptor: SerialDescriptor
            get() = TODO("Not yet implemented")

        override fun deserialize(decoder: Decoder): PaidMedia {
            TODO("Not yet implemented")
        }

        override fun serialize(encoder: Encoder, value: PaidMedia) {
            TODO("Not yet implemented")
        }

    }
}
