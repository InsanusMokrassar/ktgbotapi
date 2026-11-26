package dev.inmo.tgbotapi.types

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * To send to a user in a known chat, pass the chat identifier and `receiverUserId` to the send method.
 * To reply to a button press, pass `EphemeralMessageParameters(callbackQueryId = id)` as the destination.
 * The callback overload requires [callbackQueryId] and omits both the chat identifier and [receiverUserId].
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
