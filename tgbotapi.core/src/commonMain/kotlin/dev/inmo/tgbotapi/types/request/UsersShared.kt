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
    val users: List<SharedUser>
) : ChatSharedRequest {
    val userIds: List<UserId> by lazy {
        users.map { it.id }
    }
    val userId: UserId
        get() = userIds.first()
    constructor(
        requestId: RequestId,
        user: SharedUser
    ) : this(requestId, listOf(user))
    constructor(
        requestId: RequestId,
        userIds: List<UserId>
    ) : this(requestId, userIds.map { SharedUser(it) })
    constructor(
        requestId: RequestId,
        userId: UserId
    ) : this(requestId, SharedUser(userId))
    override val chatId: ChatId
        get() = userId
}
