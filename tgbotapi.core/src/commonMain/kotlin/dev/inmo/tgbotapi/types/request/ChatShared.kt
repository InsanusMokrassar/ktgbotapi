package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.files.PhotoFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(chatIdField)
    override val chatId: ChatId,
    @SerialName(titleField)
    val title: String? = null,
    @SerialName(usernameField)
    val username: Username? = null,
    @SerialName(photoField)
    val photo: PhotoFile? = null
) : ChatSharedRequest
