package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.hasCheckboxField
import dev.inmo.tgbotapi.types.isCheckedField
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.valueField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * An item of an [InputRichBlockList].
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblocklistitem">InputRichBlockListItem</a>
 */
@Serializable
data class InputRichBlockListItem(
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>,
    @SerialName(hasCheckboxField)
    val hasCheckbox: Boolean? = null,
    @SerialName(isCheckedField)
    val isChecked: Boolean? = null,
    @SerialName(valueField)
    val value: Int? = null,
    /**
     * For ordered lists, the type of the item label; must be one of "a", "A", "i", "I" or "1".
     */
    @SerialName(typeField)
    val labelType: String? = null
)
