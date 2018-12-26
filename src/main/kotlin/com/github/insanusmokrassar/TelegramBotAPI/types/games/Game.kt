package com.github.insanusmokrassar.TelegramBotAPI.types.games

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*
import kotlinx.serialization.*

@Serializable
data class Game(
    @SerialName(titleField)
    val title: String,
    @SerialName(descriptionField)
    val description: String,
    @Serializable(PhotoSerializer::class)
    @SerialName(photoField)
    val photo: Photo,
    @SerialName(textField)
    @Optional
    val text: String? = null,
    @Serializable(RawMessageEntitiesSerializer::class)
    @SerialName(textEntitiesField)
    @Optional
    private val textEntitiesRaw: RawMessageEntities? = null,
    @SerialName(animationField)
    @Optional
    val animation: AnimationFile? = null
) {
    @Transient
    val textEntities: List<MessageEntity>? = text ?.let {
        textEntitiesRaw ?.map {
            it.asMessageEntity(text)
        }
    }
}
