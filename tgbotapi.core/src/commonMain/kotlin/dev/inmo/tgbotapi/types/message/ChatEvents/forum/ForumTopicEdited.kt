package dev.inmo.tgbotapi.types.message.ChatEvents.forum

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.iconCustomEmojiIdField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent
import dev.inmo.tgbotapi.types.nameField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumTopicEdited(
    @SerialName(nameField)
    val name: String,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId? = null,
) : ForumEvent
