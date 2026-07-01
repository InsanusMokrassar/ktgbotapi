package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.isRtlField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Rich formatted message.
 *
 * @see <a href="https://core.telegram.org/bots/api#richmessage">RichMessage</a>
 */
@Serializable
data class RichTextInfo(
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
    @SerialName(isRtlField)
    val isRtl: Boolean? = null
)
