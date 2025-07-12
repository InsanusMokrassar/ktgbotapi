@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.requests.business_connection

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.DoubleSeconds
import dev.inmo.tgbotapi.types.animationField
import dev.inmo.tgbotapi.types.mainFrameTimestampField
import dev.inmo.tgbotapi.types.photoField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject

@Serializable(InputProfilePhoto.Companion::class)
sealed interface InputProfilePhoto {
    val type: String
    val mediaPair: Pair<String, MultipartFile>
    @Serializable
    data class Static(
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(photoField)
        val photo: MultipartFile
    ) : InputProfilePhoto {
        override val mediaPair: Pair<String, MultipartFile>
            get() = photo.fileId to photo
        @EncodeDefault
        override val type: String = "static"
    }
    @Serializable
    data class Animated(
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(animationField)
        val animation: MultipartFile,
        @SerialName(mainFrameTimestampField)
        val mainFrameTimestamp: DoubleSeconds? = null
    ) : InputProfilePhoto {
        override val mediaPair: Pair<String, MultipartFile>
            get() = animation.fileId to animation
        @EncodeDefault
        override val type: String = "animated"
    }

    companion object : KSerializer<InputProfilePhoto> {
        override val descriptor: SerialDescriptor
            get() = JsonObject.serializer().descriptor

        override fun deserialize(decoder: Decoder): InputProfilePhoto {
            error("Deserialization is not supported yet")
        }

        override fun serialize(encoder: Encoder, value: InputProfilePhoto) {
            when (value) {
                is Animated -> Animated.serializer().serialize(encoder, value)
                is Static -> Static.serializer().serialize(encoder, value)
            }
        }
    }
}