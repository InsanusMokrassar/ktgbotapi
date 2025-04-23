package dev.inmo.tgbotapi.types.message.ChatEvents.forum

import dev.inmo.tgbotapi.types.CustomEmojiId
import dev.inmo.tgbotapi.types.iconColorField
import dev.inmo.tgbotapi.types.iconCustomEmojiIdField
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.utils.RGBColor
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForumTopicCreated(
    @SerialName(nameField)
    val name: String,
    @SerialName(iconColorField)
    val iconColor: RGBColor,
    @SerialName(iconCustomEmojiIdField)
    val iconEmojiId: CustomEmojiId? = null,
) : ForumEvent
