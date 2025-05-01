package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.utils.decodeDataAndJson
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject

@Serializable
sealed interface InputStoryContent {
    sealed interface Type {
        val name: String
    }
    val type : Type
    val media: Pair<String, MultipartFile>

    @Serializable
    data class Photo (
        @SerialName(photoField)
        val photo: MultipartFile
    ) : InputStoryContent {
        @EncodeDefault
        override val type: Type = Companion
        override val media: Pair<String, MultipartFile>
            get() = photoField to photo

        companion object : Type {
            override val name: String
                get() = "photo"
        }
    }
    @Serializable
    data class Video (
        @SerialName(videoField)
        val video: MultipartFile,
        @SerialName(durationField)
        val duration: DoubleSeconds? = null,
        @SerialName(coverFrameTimeStampField)
        val coverFrameTimeStamp: DoubleSeconds? = null,
        @SerialName(isAnimationField)
        val isAnimation: Boolean = false
    ) : InputStoryContent {
        @EncodeDefault
        override val type: Type = Companion
        override val media: Pair<String, MultipartFile>
            get() = videoField to video

        companion object : Type {
            override val name: String
                get() = "video"
        }
    }

    companion object : KSerializer<InputStoryContent> {
        private val serializer = JsonObject.serializer()
        override val descriptor: SerialDescriptor
            get() = serializer.descriptor

        override fun serialize(encoder: Encoder, value: InputStoryContent) {
            when (value) {
                is Photo -> Photo.serializer().serialize(encoder, value)
                is Video -> Video.serializer().serialize(encoder, value)
            }
        }

        override fun deserialize(decoder: Decoder): InputStoryContent {
            throw UnsupportedOperationException("InputStoryContent cannot be deserialized for now")
        }
    }
}
