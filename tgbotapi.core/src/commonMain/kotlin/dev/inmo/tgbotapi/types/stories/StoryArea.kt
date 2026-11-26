package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryArea(
    @SerialName(positionField)
    val position: AreaPosition,
    @SerialName(typeField)
    val type: StoryAreaType
)
