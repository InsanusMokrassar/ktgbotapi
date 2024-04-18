package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(usersField)
    val users: List<PreviewUser>
) : ChatSharedRequest {
    val userIds: List<UserId> by lazy {
        users.map { it.id }
    }
    val userId: UserId
        get() = userIds.first()
    constructor(
        requestId: RequestId,
        user: PreviewUser
    ) : this(requestId, listOf(user))
    override val chatId: ChatId
        get() = userId
}
