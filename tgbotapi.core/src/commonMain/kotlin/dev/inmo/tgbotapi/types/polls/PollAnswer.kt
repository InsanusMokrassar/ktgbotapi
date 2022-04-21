package dev.inmo.tgbotapi.types.polls

import dev.inmo.tgbotapi.CommonAbstracts.FromUser
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.User
import kotlinx.serialization.*

@Serializable
data class PollAnswer(
    @SerialName(pollIdField)
    val pollId: PollIdentifier,
    @SerialName(userField)
    override val user: User,
    @SerialName(optionIdsField)
    val chosen: List<Int>
) : FromUser {
    @Transient
    override val from: User
        get() = user
}
