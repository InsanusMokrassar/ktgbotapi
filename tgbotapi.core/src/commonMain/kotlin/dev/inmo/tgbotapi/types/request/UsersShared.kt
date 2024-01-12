package dev.inmo.tgbotapi.types.request

import dev.inmo.tgbotapi.types.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersShared(
    @SerialName(requestIdField)
    override val requestId: RequestId,
    @SerialName(userIdsField)
    val userIds: List<UserId>
) : ChatSharedRequest {
    val userId: UserId
        get() = userIds.first()
    constructor(
        requestId: RequestId,
        userId: UserId
    ) : this(requestId, listOf(userId))
    override val chatId: ChatId
        get() = userId
}
