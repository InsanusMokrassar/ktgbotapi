package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(usersField)
    val users: List<SharedUser>,
) : ChatSharedRequest {
    val userIds: List<UserId> by lazy {
        users.map { it.id }
    }
    val userId: UserId
        get() = userIds.first()
    constructor(
        requestId: RequestId,
        user: SharedUser,
    ) : this(requestId, listOf(user))

    override val chatId: ChatId
        get() = userId

    companion object {
        operator fun invoke(
            requestId: RequestId,
            userIds: List<UserId>,
        ) = UsersShared(requestId, userIds.map { SharedUser(it) })

        operator fun invoke(
            requestId: RequestId,
            userId: UserId,
        ) = UsersShared(requestId, SharedUser(userId))
    }
}
