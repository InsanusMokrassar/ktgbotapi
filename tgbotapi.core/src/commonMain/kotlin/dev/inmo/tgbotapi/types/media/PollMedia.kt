package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.types.animationField
import dev.inmo.tgbotapi.types.audioField
import dev.inmo.tgbotapi.types.documentField
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.DocumentFile
import dev.inmo.tgbotapi.types.files.LivePhotoFile
import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.files.Sticker
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.livePhotoField
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.locationField
import dev.inmo.tgbotapi.types.photoField
import dev.inmo.tgbotapi.types.stickerField
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.types.venueField
import dev.inmo.tgbotapi.types.videoField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(PollMedia.Serializer::class)
interface PollMedia : BaseTelegramMediaFile {
    object Serializer : KSerializer<PollMedia> {
        @Serializable
        data class Surrogate(
            @SerialName(animationField)
            val animation: AnimationFile? = null,
            @SerialName(audioField)
            val audio: AudioFile? = null,
            @SerialName(documentField)
            val document: DocumentFile? = null,
            @SerialName(livePhotoField)
            val livePhoto: LivePhotoFile? = null,
            @SerialName(photoField)
            val photo: PhotoFile? = null,
            @SerialName(stickerField)
            val sticker: Sticker? = null,
            @SerialName(videoField)
            val video: VideoFile? = null,
            @SerialName(locationField)
            val location: StaticLocation? = null,
            @SerialName(venueField)
            val venue: Venue? = null
        ) {
        }

        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): PollMedia {
            val surrogate = decoder.decodeSerializableValue(Surrogate.serializer())

            return when {
                surrogate.animation != null -> surrogate.animation
                surrogate.audio != null -> surrogate.audio
                surrogate.document != null -> surrogate.document
                surrogate.livePhoto != null -> surrogate.livePhoto
                surrogate.photo != null -> surrogate.photo
                surrogate.sticker != null -> surrogate.sticker
                surrogate.video != null -> surrogate.video
                surrogate.location != null -> surrogate.location
                surrogate.venue != null -> surrogate.venue
                else -> error("Unexpected media type in PollMedia deserialization")
            }
        }

        override fun serialize(encoder: Encoder, value: PollMedia) {
            val surrogate = Surrogate(
                animation = value as? AnimationFile,
                audio = value as? AudioFile,
                document = value as? DocumentFile,
                livePhoto = value as? LivePhotoFile,
                photo = value as? PhotoFile,
                sticker = value as? Sticker,
                video = value as? VideoFile,
                location = value as? StaticLocation,
                venue = value as? Venue ?: error("Unexpected media type in PollMedia serialization")
            )

            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }
    }
}