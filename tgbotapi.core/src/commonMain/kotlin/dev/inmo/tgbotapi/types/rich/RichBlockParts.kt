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
import kotlinx.serialization.KSerializer
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
@Serializable(RichBlockTableCell.Serializer::class)
sealed interface RichBlockTableCell {
    val text: RichText?
    val colspan: Int?
    val rowspan: Int?
    val align: RichBlockTableCellAlign
    val valign: String

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data class Header(
        @SerialName(textField)
        override val text: RichText? = null,
        @SerialName(colspanField)
        override val colspan: Int? = null,
        @SerialName(rowspanField)
        override val rowspan: Int? = null,
        @SerialName(alignField)
        override val align: RichBlockTableCellAlign,
        @SerialName(valignField)
        override val valign: String
    ) : RichBlockTableCell

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data class Regular(
        @SerialName(textField)
        override val text: RichText? = null,
        @SerialName(colspanField)
        override val colspan: Int? = null,
        @SerialName(rowspanField)
        override val rowspan: Int? = null,
        @SerialName(alignField)
        override val align: RichBlockTableCellAlign,
        @SerialName(valignField)
        override val valign: String
    ) : RichBlockTableCell

    object Serializer : KSerializer<RichBlockTableCell> {
        @Serializable
        private data class Surrogate(
            @SerialName(textField)
            val text: RichText? = null,
            @SerialName(isHeaderField)
            @EncodeDefault(EncodeDefault.Mode.NEVER)
            val isHeader: Boolean? = null,
            @SerialName(colspanField)
            val colspan: Int? = null,
            @SerialName(rowspanField)
            val rowspan: Int? = null,
            @SerialName(alignField)
            val align: RichBlockTableCellAlign,
            @SerialName(valignField)
            val valign: String
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): RichBlockTableCell {
            val surrogate = decoder.decodeSerializableValue(Surrogate.serializer())
            return if (surrogate.isHeader == true) {
                Header(surrogate.text, surrogate.colspan, surrogate.rowspan, surrogate.align, surrogate.valign)
            } else {
                Regular(surrogate.text, surrogate.colspan, surrogate.rowspan, surrogate.align, surrogate.valign)
            }
        }

        override fun serialize(encoder: Encoder, value: RichBlockTableCell) {
            val surrogate = Surrogate(
                value.text,
                true.takeIf { value is Header },
                value.colspan,
                value.rowspan,
                value.align,
                value.valign
            )
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }
    }
}

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
