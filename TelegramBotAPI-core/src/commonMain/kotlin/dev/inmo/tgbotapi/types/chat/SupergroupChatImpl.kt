package dev.inmo.tgbotapi.types.chat

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.abstracts.SupergroupChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SupergroupChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(titleField)
    override val title: String,
    @SerialName(usernameField)
    override val username: Username? = null
) : SupergroupChat
