package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.alignField
import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.colspanField
import dev.inmo.tgbotapi.types.creditField
import dev.inmo.tgbotapi.types.hasCheckboxField
import dev.inmo.tgbotapi.types.isCheckedField
import dev.inmo.tgbotapi.types.isHeaderField
import dev.inmo.tgbotapi.types.labelField
import dev.inmo.tgbotapi.types.rowspanField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.valignField
import dev.inmo.tgbotapi.types.valueField
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Caption of a rich formatted block.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockcaption">RichBlockCaption</a>
 */
@Serializable
data class RichBlockCaption(
    @SerialName(textField)
    val text: RichText,
    @SerialName(creditField)
    val credit: RichText? = null
)

/**
 * A cell in a [RichBlockTable].
 *
 * @see <a href="https://core.telegram.org/bots/api#richblocktablecell">RichBlockTableCell</a>
 */
@Serializable
data class RichBlockTableCell(
    @SerialName(textField)
    val text: RichText? = null,
    @SerialName(isHeaderField)
    val isHeader: Boolean? = null,
    @SerialName(colspanField)
    val colspan: Int? = null,
    @SerialName(rowspanField)
    val rowspan: Int? = null,
    @SerialName(alignField)
    val align: String,
    @SerialName(valignField)
    val valign: String
)

/**
 * An item of a [RichBlockList].
 *
 * @see <a href="https://core.telegram.org/bots/api#richblocklistitem">RichBlockListItem</a>
 */
@Serializable
data class RichBlockListItem(
    @SerialName(labelField)
    val label: String,
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
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
