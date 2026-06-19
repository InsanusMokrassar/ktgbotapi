package dev.inmo.tgbotapi.types.media

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.urlField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an HTTP link to be sent. Can be used as [InputPollOptionMedia].
 *
 * @see <a href="https://core.telegram.org/bots/api#inputmedialink">InputMediaLink</a>
 */
@Serializable
data class TelegramMediaLink(
    @SerialName(urlField)
    val url: String,
) : InputPollOptionMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "link"
    }
}
