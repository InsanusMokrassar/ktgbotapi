package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryArea(
    @SerialName(positionField)
    val position: StoryAreaPosition,
    @SerialName(typeField)
    val type: StoryAreaType
)
