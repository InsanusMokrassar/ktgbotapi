package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.request.RequestId
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @param requestId Signed 32-bit identifier of the request. Must be unique within the message
 * @param suggestedName Suggested name for the bot
 * @param suggestedUsername Suggested username for the bot
 */
@Serializable
data class KeyboardButtonRequestManagedBot(
    @SerialName(requestIdField)
    val requestId: RequestId,
    @SerialName(suggestedNameField)
    val suggestedName: String? = null,
    @SerialName(suggestedUsernameField)
    val suggestedUsername: Username? = null
)

