package dev.inmo.tgbotapi.types.reactions

import dev.inmo.tgbotapi.types.totalCountField
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionsCount(
    @SerialName(typeField)
    val reaction: Reaction,
    @SerialName(totalCountField)
    val count: Int,
)
