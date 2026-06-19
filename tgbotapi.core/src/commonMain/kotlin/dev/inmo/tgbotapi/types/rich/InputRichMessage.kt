package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.htmlField
import dev.inmo.tgbotapi.types.isRtlField
import dev.inmo.tgbotapi.types.markdownField
import dev.inmo.tgbotapi.types.skipEntityDetectionField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes a rich message to be sent. Exactly one of the fields [html] or [markdown] must be used. Use the
 * [InputRichMessageHTML] and [InputRichMessageMarkdown] factories to build an instance.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessage">InputRichMessage</a>
 */
@ConsistentCopyVisibility
@Serializable
data class InputRichMessage internal constructor(
    @SerialName(htmlField)
    val html: String? = null,
    @SerialName(markdownField)
    val markdown: String? = null,
    @SerialName(isRtlField)
    val isRtl: Boolean? = null,
    @SerialName(skipEntityDetectionField)
    val skipEntityDetection: Boolean? = null
) {
    init {
        require((html == null) != (markdown == null)) {
            "Exactly one of the fields html or markdown must be used in InputRichMessage"
        }
    }
}

/**
 * Creates an [InputRichMessage] with the content described using HTML formatting.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessage">InputRichMessage</a>
 */
fun InputRichMessageHTML(
    html: String,
    isRtl: Boolean? = null,
    skipEntityDetection: Boolean? = null
): InputRichMessage = InputRichMessage(
    html = html,
    markdown = null,
    isRtl = isRtl,
    skipEntityDetection = skipEntityDetection
)

/**
 * Creates an [InputRichMessage] with the content described using Markdown formatting.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessage">InputRichMessage</a>
 */
fun InputRichMessageMarkdown(
    markdown: String,
    isRtl: Boolean? = null,
    skipEntityDetection: Boolean? = null
): InputRichMessage = InputRichMessage(
    html = null,
    markdown = markdown,
    isRtl = isRtl,
    skipEntityDetection = skipEntityDetection
)
