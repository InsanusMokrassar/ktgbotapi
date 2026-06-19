package dev.inmo.tgbotapi.types.InlineQueries.InputMessageContent

import dev.inmo.tgbotapi.types.richMessageField
import dev.inmo.tgbotapi.types.rich.InputRichMessage
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents the content of a rich message to be sent as the result of an inline query.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessagecontent">InputRichMessageContent</a>
 */
@Serializable
data class InputRichMessageContent(
    @SerialName(richMessageField)
    val richMessage: InputRichMessage
) : InputMessageContent
