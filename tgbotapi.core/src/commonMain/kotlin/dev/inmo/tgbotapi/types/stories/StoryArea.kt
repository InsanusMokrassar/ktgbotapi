package dev.inmo.tgbotapi.types.stories

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryArea(
    val position: StoryAreaPosition,
    val type: StoryAreaType
)
