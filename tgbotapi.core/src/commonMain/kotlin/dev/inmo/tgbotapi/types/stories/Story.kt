package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.types.ReplyInfo
import dev.inmo.tgbotapi.types.StoryId
import dev.inmo.tgbotapi.types.chat.PreviewChat
import dev.inmo.tgbotapi.types.chatField
import dev.inmo.tgbotapi.types.idField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Story(
    @SerialName(idField)
    val id: StoryId,
    @SerialName(chatField)
    val chat: PreviewChat
) : ReplyInfo.External.ContentVariant
