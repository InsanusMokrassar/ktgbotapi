package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.media.PollMedia
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Represents an HTTP link.
 *
 * @see <a href="https://core.telegram.org/bots/api#link">Link</a>
 */
@Serializable
data class Link(
    @SerialName(urlField)
    val url: String
) : PollMedia
