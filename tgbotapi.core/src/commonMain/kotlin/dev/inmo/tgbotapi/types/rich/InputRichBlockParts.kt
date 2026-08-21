package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.hasCheckboxField
import dev.inmo.tgbotapi.types.isCheckedField
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.valueField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

/**
 * An item of an [InputRichBlockList]. Ordered items have a non-null [InputRichBlockListItem.Ordered.value] and
 * [InputRichBlockListItem.Ordered.labelType]; unordered items omit both fields from the Bot API payload.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblocklistitem">InputRichBlockListItem</a>
 */
@Serializable(InputRichBlockListItem.Serializer::class)
sealed interface InputRichBlockListItem {
    val blocks: List<InputRichBlock>
    val hasCheckbox: Boolean?
    val isChecked: Boolean?

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data class Ordered(
        @SerialName(blocksField)
        override val blocks: List<InputRichBlock>,
        @SerialName(valueField)
        val value: Int,
        @SerialName(typeField)
        val labelType: LabelType = LabelType.Decimals,
        @SerialName(hasCheckboxField)
        override val hasCheckbox: Boolean? = null,
        @SerialName(isCheckedField)
        override val isChecked: Boolean? = null
    ) : InputRichBlockListItem

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(Serializer::class)
    data class Unordered(
        @SerialName(blocksField)
        override val blocks: List<InputRichBlock>,
        @SerialName(hasCheckboxField)
        override val hasCheckbox: Boolean? = null,
        @SerialName(isCheckedField)
        override val isChecked: Boolean? = null
    ) : InputRichBlockListItem

    object Serializer : KSerializer<InputRichBlockListItem> {
        @Serializable
        private data class Surrogate(
            @SerialName(blocksField)
            val blocks: List<InputRichBlock>,
            @SerialName(hasCheckboxField)
            val hasCheckbox: Boolean? = null,
            @SerialName(isCheckedField)
            val isChecked: Boolean? = null,
            @SerialName(valueField)
            val value: Int? = null,
            @SerialName(typeField)
            val labelType: LabelType? = null
        )

        override val descriptor: SerialDescriptor = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): InputRichBlockListItem {
            val surrogate = decoder.decodeSerializableValue(Surrogate.serializer())
            return when {
                surrogate.value != null && surrogate.labelType != null -> Ordered(
                    surrogate.blocks,
                    surrogate.value,
                    surrogate.labelType,
                    surrogate.hasCheckbox,
                    surrogate.isChecked
                )
                surrogate.value == null && surrogate.labelType == null -> Unordered(
                    surrogate.blocks,
                    surrogate.hasCheckbox,
                    surrogate.isChecked
                )
                else -> throw SerializationException("Ordered InputRichBlockListItem requires both value and type")
            }
        }

        override fun serialize(encoder: Encoder, value: InputRichBlockListItem) {
            val surrogate = when (value) {
                is Ordered -> Surrogate(
                    value.blocks,
                    value.hasCheckbox,
                    value.isChecked,
                    value.value,
                    value.labelType
                )
                is Unordered -> Surrogate(value.blocks, value.hasCheckbox, value.isChecked)
            }
            encoder.encodeSerializableValue(Surrogate.serializer(), surrogate)
        }
    }
}
