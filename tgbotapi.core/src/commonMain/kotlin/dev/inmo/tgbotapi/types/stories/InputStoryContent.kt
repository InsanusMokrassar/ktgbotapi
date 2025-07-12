@file:OptIn(ExperimentalSerializationApi::class)
@file:Suppress("SERIALIZER_TYPE_INCOMPATIBLE")

package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlin.jvm.JvmInline

@Serializable(InputStoryContent.Companion::class)
sealed interface InputStoryContent {
    @Serializable(Type.Companion::class)
    sealed interface Type {
        val name: String
        companion object : KSerializer<Type> {
            @Serializable
            @JvmInline
            value class Unknown(override val name: String) : Type
            override val descriptor: SerialDescriptor
                get() = String.serializer().descriptor

            override fun serialize(encoder: Encoder, value: Type) { encoder.encodeString(value.name) }

            override fun deserialize(decoder: Decoder): Type {
                val name = decoder.decodeString()
                return when (name) {
                    Photo.Type.name -> Photo.Type
                    Video.Type.name -> Video.Type
                    else -> Unknown(name)
                }
            }
        }
    }
    val type : Type
    val media: Pair<String, MultipartFile>

    @Serializable
    data class Photo (
        @SerialName(photoField)
        val photo: MultipartFile
    ) : InputStoryContent {
        @EncodeDefault
        override val type: Type = Type
        override val media: Pair<String, MultipartFile>
            get() = photo.fileId to photo

        @Serializable(InputStoryContent.Type.Companion::class)
        object Type : InputStoryContent.Type {
            @EncodeDefault
            override val name: String = "photo"
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
        override val type: Type = Type
        override val media: Pair<String, MultipartFile>
            get() = video.fileId to video

        @Serializable(InputStoryContent.Type.Companion::class)
        object Type : InputStoryContent.Type {
            @EncodeDefault
            override val name: String = "video"
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
