package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A [receiverUserId] targets a user in the specified chat. A [callbackQueryId] without a receiver
 * targets the user who pressed the button and allows the send method to omit the chat identifier.
 * Answer the callback query separately, including when [replaceCallbackQueryMessage] is enabled.
 *
 * @see <a href="https://core.telegram.org/bots/api#ephemeralmessageparameters">EphemeralMessageParameters</a>
 */
@Serializable
data class EphemeralMessageParameters(
    @SerialName(receiverUserIdField)
    val receiverUserId: UserId? = null,
    @SerialName(callbackQueryIdField)
    val callbackQueryId: CallbackQueryId? = null,
    @SerialName(replaceCallbackQueryMessageField)
    val replaceCallbackQueryMessage: Boolean? = null
) {
    init {
        require(receiverUserId != null || callbackQueryId != null) {
            "An ephemeral message requires a receiver user ID or a callback query ID"
        }
    }
}

internal fun requireSendMessageTarget(chatId: ChatIdentifier?, parameters: EphemeralMessageParameters?) {
    require(chatId != null || (parameters?.callbackQueryId != null && parameters.receiverUserId == null)) {
        "A chat ID is required unless an ephemeral message targets a callback query without a receiver user ID"
    }
}
