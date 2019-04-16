package com.github.insanusmokrassar.TelegramBotAPI.types.polls

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Poll(
    @SerialName(idField)
    val id: PollIdentifier,
    @SerialName(questionField)
    val question: String,
    @SerialName(optionsField)
    val options: List<PollOption>,
    @SerialName(isClosedField)
    val closed: Boolean = false
)
