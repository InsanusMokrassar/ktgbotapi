package com.github.insanusmokrassar.TelegramBotAPI.types.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*
import kotlinx.serialization.*

@Serializable
data class Game internal constructor(
    @SerialName(titleField)
    override val title: String,
    @SerialName(descriptionField)
    val description: String,
    @Serializable(PhotoSerializer::class)
    @SerialName(photoField)
    val photo: Photo,
    @SerialName(textField)
    override val caption: String? = null,
    @Serializable(RawMessageEntitiesSerializer::class)
    @SerialName(textEntitiesField)
    private val rawEntities: RawMessageEntities? = null,
    @SerialName(animationField)
    val animation: AnimationFile? = null
) : Titled, CaptionedInput {
    @Transient
    override val captionEntities: List<MessageEntity> = caption ?.let {
        rawEntities ?.map {
            it.asMessageEntity(caption)
        }
    } ?: emptyList()

    @Deprecated(
        "Missinterfaced field",
        ReplaceWith("caption")
    )
    @Transient
    val text: String?
        get() = caption

    @Deprecated(
        "Missinterfaced field",
        ReplaceWith("captionEntities")
    )
    @Transient
    val textEntities: List<MessageEntity>?
        get() = captionEntities
}
