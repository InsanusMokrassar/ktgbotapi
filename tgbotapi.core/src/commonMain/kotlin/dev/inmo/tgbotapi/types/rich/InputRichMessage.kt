package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.requests.abstracts.MultipartFile
import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.htmlField
import dev.inmo.tgbotapi.types.isRtlField
import dev.inmo.tgbotapi.types.markdownField
import dev.inmo.tgbotapi.types.media.CoveredTelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.media.ThumbedTelegramMedia
import dev.inmo.tgbotapi.types.mediaField
import dev.inmo.tgbotapi.types.skipEntityDetectionField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Describes a rich message to be sent. Exactly one of the fields [html], [markdown] or [blocks] must be used. Use the
 * [InputRichMessageHTML], [InputRichMessageMarkdown] and [InputRichMessageBlocks] factories to build an instance.
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
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>? = null,
    @SerialName(mediaField)
    val media: List<InputRichMessageMedia>? = null,
    @SerialName(isRtlField)
    val isRtl: Boolean? = null,
    @SerialName(skipEntityDetectionField)
    val skipEntityDetection: Boolean? = null
) {
    init {
        require(listOfNotNull(html, markdown, blocks).size == 1) {
            "Exactly one of the fields html, markdown or blocks must be used in InputRichMessage"
        }
        require(media == null || blocks == null) {
            "The field media of InputRichMessage can be used only together with html or markdown, not blocks"
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
    skipEntityDetection: Boolean? = null,
    media: List<InputRichMessageMedia>? = null
): InputRichMessage = InputRichMessage(
    html = html,
    markdown = null,
    blocks = null,
    media = media,
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
    skipEntityDetection: Boolean? = null,
    media: List<InputRichMessageMedia>? = null
): InputRichMessage = InputRichMessage(
    html = null,
    markdown = markdown,
    blocks = null,
    media = media,
    isRtl = isRtl,
    skipEntityDetection = skipEntityDetection
)

/**
 * Creates an [InputRichMessage] with the content described using a list of [InputRichBlock]s.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichmessage">InputRichMessage</a>
 */
fun InputRichMessageBlocks(
    blocks: List<InputRichBlock>,
    isRtl: Boolean? = null,
    skipEntityDetection: Boolean? = null
): InputRichMessage = InputRichMessage(
    html = null,
    markdown = null,
    blocks = blocks,
    media = null,
    isRtl = isRtl,
    skipEntityDetection = skipEntityDetection
)

/**
 * Files ([MultipartFile]) referenced by this [InputRichMessage]: the standalone [InputRichMessage.media] entries and any
 * media block nested (recursively) in [InputRichMessage.blocks], keyed by [MultipartFile.fileId].
 */
internal val InputRichMessage.multipartFiles: Map<String, MultipartFile>
    get() {
        val mediaFiles = media.orEmpty().flatMap { it.media.multipartFiles() }
        val blockFiles = blocks.orEmpty().flatMap { it.multipartFiles() }
        return (mediaFiles + blockFiles).associateBy { it.fileId }
    }

private fun TelegramMedia.multipartFiles(): List<MultipartFile> = listOfNotNull(
    file as? MultipartFile,
    if (this is ThumbedTelegramMedia) {
        thumb as? MultipartFile
    } else {
        null
    },
    if (this is CoveredTelegramMedia) {
        cover as? MultipartFile
    } else {
        null
    }
)

private fun InputRichBlock.multipartFiles(): List<MultipartFile> {
    val ownFiles = (this as? InputRichBlockMedia) ?.media ?.multipartFiles().orEmpty()
    return ownFiles + subBlocks.flatMap { it.multipartFiles() }
}
