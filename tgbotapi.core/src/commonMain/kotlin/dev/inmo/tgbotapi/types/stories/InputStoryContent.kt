package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.photoField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
        override val type: Type
            get() = Companion

        companion object : Type {
            override val name: String
                get() = "photo"
        }
    }
}
