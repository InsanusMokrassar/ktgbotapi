package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Parameters defining an ephemeral message recipient and optional callback-query replacement behavior.
 */
@Serializable
data class EphemeralMessageParameters(
    @SerialName(receiverUserIdField)
    val receiverUserId: UserId,
    @SerialName(callbackQueryIdField)
    val callbackQueryId: CallbackQueryId? = null,
    @SerialName(replaceCallbackQueryMessageField)
    val replaceCallbackQueryMessage: Boolean? = null
)
