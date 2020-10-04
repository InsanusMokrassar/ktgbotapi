package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollAnswer(
    @SerialName(pollIdField)
    val pollId: PollIdentifier,
    @SerialName(userField)
    val user: User,
    @SerialName(optionIdsField)
    val chosen: List<Int>
)
