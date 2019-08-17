package com.github.insanusmokrassar.TelegramBotAPI.types.games

import com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity.*
import com.github.insanusmokrassar.TelegramBotAPI.types.files.*
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.*
import java.lang.UnsupportedOperationException

@Serializable(GameSerializer::class)
data class Game(
    @SerialName(titleField)
    override val title: String,
    @SerialName(descriptionField)
    val description: String,
    @Serializable(PhotoSerializer::class)
    @SerialName(photoField)
    val photo: Photo,
    @SerialName(textField)
    override val caption: String? = null,
    @SerialName(textEntitiesField)
    override val captionEntities: List<MessageEntity> = emptyList(),
    @SerialName(animationField)
    val animation: AnimationFile? = null
) : Titled, CaptionedInput {

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

internal object GameSerializer : KSerializer<Game> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("GameSerializer")

    override fun deserialize(decoder: Decoder): Game {
        val json = JsonObjectSerializer.deserialize(decoder)

        val text = json.getPrimitiveOrNull(textField) ?.content

        return Game(
            json.getPrimitive(titleField).content,
            json.getPrimitive(descriptionField).content,
            Json.nonstrict.fromJson(
                PhotoSerializer,
                json.getObject(photoField)
            ),
            text,
            text ?.let {
                Json.nonstrict.fromJson(
                    RawMessageEntitiesSerializer,
                    json.getArray(textEntitiesField)
                ).map {
                    it.asMessageEntity(text)
                }
            } ?: emptyList(),
            json.getObjectOrNull(
                animationField
            ) ?.let { animatedFileJson ->
                Json.nonstrict.fromJson(
                    AnimationFile.serializer(),
                    animatedFileJson
                )
            }
        )
    }

    override fun serialize(encoder: Encoder, obj: Game) = throw UnsupportedOperationException(
        "Objects of class Game can't be serialized for now"
    )
}
