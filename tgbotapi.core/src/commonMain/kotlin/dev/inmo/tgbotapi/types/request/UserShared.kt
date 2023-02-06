package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.requestIdField
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(userIdField)
    val userId: UserId
) : ChatSharedRequest {
    override val chatId: ChatId
        get() = userId
}
