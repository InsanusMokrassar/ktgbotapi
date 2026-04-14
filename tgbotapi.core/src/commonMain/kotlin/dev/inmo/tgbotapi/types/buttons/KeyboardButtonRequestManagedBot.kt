package dev.inmo.tgbotapi.types.buttons

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.request.RequestId
import dev.inmo.tgbotapi.utils.serializers.UsernameAtLessSerializer
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
    @Serializable(UsernameAtLessSerializer::class)
    val suggestedUsername: Username? = null
)

/**
 * A type alias for the `KeyboardButtonRequestManagedBot` class representing the parameters
 * for managing a bot request in a keyboard button.
 *
 * This type encapsulates properties such as:
 * - A unique request identifier (`requestId`).
 * - Suggested name and username for the bot (`suggestedName` and `suggestedUsername`).
 */
typealias RequestManagedBotParameters = KeyboardButtonRequestManagedBot
