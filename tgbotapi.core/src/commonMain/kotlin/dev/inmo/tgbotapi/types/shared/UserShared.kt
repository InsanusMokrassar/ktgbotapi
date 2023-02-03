package dev.inmo.tgbotapi.types.shared

import dev.inmo.tgbotapi.types.Identifier
import dev.inmo.tgbotapi.types.requestIdField
import dev.inmo.tgbotapi.types.userIdField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserShared(
    @SerialName(requestIdField)
    val requestId: Identifier,

    @SerialName(userIdField)
    val userId: Identifier,
)
