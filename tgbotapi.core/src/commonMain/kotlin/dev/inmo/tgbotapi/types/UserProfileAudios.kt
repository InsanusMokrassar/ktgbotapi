package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.files.AudioFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileAudios(
    @SerialName(totalCountField)
    val totalCount: Int,
    @SerialName(audiosField)
    val audios: List<AudioFile>
)