package com.github.insanusmokrassar.TelegramBotAPI.types.chat

import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.PrivateChat
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PrivateChatImpl(
    @SerialName(idField)
    override val id: ChatId,
    @SerialName(usernameField)
    override val username: Username? = null,
    @SerialName(firstNameField)
    override val firstName: String = "",
    @SerialName(lastNameField)
    override val lastName: String = ""
) : PrivateChat
