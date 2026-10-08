package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.idField
import dev.inmo.tgbotapi.types.media.RichMessageMemberTelegramMedia
import dev.inmo.tgbotapi.types.mediaField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

private val inputRichMessageMediaIdRegex = Regex("[A-Za-z0-9_-]{1,64}")

/**
 * Describes a media referenced from [InputRichMessage.html]/[InputRichMessage.markdown] via `tg://photo?id=`,
 * `tg://video?id=`, `tg://document?id=` and `tg://audio?id=` links.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessagemedia">InputRichMessageMedia</a>
 */
@Serializable
data class InputRichMessageMedia(
    @SerialName(idField)
    val id: String,
    @SerialName(mediaField)
    val media: RichMessageMemberTelegramMedia
) {
    init {
        require(id.matches(inputRichMessageMediaIdRegex)) {
            "id of InputRichMessageMedia must be 1-64 characters long and contain only latin letters, digits, underscores and hyphens, but was \"$id\""
        }
    }
}
