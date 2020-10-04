package dev.inmo.tgbotapi.types.games

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.MessageEntity.RawMessageEntities
import dev.inmo.tgbotapi.types.MessageEntity.asTextParts
import dev.inmo.tgbotapi.types.files.*
import kotlinx.serialization.*

@Serializable
internal data class RawGame(
    @SerialName(titleField)
    private val title: String,
    @SerialName(descriptionField)
    private val description: String,
    @Serializable(PhotoSerializer::class)
    @SerialName(photoField)
    private val photo: Photo,
    @SerialName(textField)
    private val caption: String? = null,
    @SerialName(textEntitiesField)
    private val captionEntities: RawMessageEntities = emptyList(),
    @SerialName(animationField)
    private val animation: AnimationFile? = null
) {
    @Transient
    val asGame = Game(
        title,
        description,
        photo,
        caption,
        caption ?.let { _ -> captionEntities.asTextParts(caption) } ?: emptyList(),
        animation
    )
}
