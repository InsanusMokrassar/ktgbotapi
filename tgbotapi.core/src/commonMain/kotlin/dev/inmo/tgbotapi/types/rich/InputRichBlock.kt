package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents a block in a rich formatted message to be sent.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblock">InputRichBlock</a>
 */
@Serializable(InputRichBlockSerializer::class)
@ClassCastsIncluded
sealed interface InputRichBlock {
    val type: String
}

@Serializable(InputRichBlockSerializer::class)
sealed interface InputRichBlockMedia : InputRichBlock {
    val media: TelegramMedia
    val caption: RichBlockCaption?
}

/**
 * The nested [InputRichBlock]s directly contained by this block, or an empty list for leaf blocks. Container blocks
 * ([InputRichBlockList] via its [InputRichBlockListItem.blocks], [InputRichBlockBlockQuotation], [InputRichBlockCollage],
 * [InputRichBlockSlideshow] and [InputRichBlockDetails]) expose their children here.
 */
val InputRichBlock.subBlocks: List<InputRichBlock>
    get() = when (this) {
        is InputRichBlockList -> items.flatMap { it.blocks }
        is InputRichBlockBlockQuotation -> blocks
        is InputRichBlockCollage -> blocks
        is InputRichBlockSlideshow -> blocks
        is InputRichBlockDetails -> blocks
        else -> emptyList()
    }

object InputRichBlockSerializer : JsonContentPolymorphicSerializer<InputRichBlock>(InputRichBlock::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<InputRichBlock> {
        return when (val type = element.jsonObject[typeField]?.jsonPrimitive?.content) {
            InputRichBlockParagraph.TYPE -> InputRichBlockParagraph.serializer()
            InputRichBlockSectionHeading.TYPE -> InputRichBlockSectionHeading.serializer()
            InputRichBlockPreformatted.TYPE -> InputRichBlockPreformatted.serializer()
            InputRichBlockFooter.TYPE -> InputRichBlockFooter.serializer()
            InputRichBlockDivider.TYPE -> InputRichBlockDivider.serializer()
            InputRichBlockMathematicalExpression.TYPE -> InputRichBlockMathematicalExpression.serializer()
            InputRichBlockAnchor.TYPE -> InputRichBlockAnchor.serializer()
            InputRichBlockList.TYPE -> InputRichBlockList.serializer()
            InputRichBlockBlockQuotation.TYPE -> InputRichBlockBlockQuotation.serializer()
            InputRichBlockExpandableBlockQuotation.TYPE -> InputRichBlockExpandableBlockQuotation.serializer()
            InputRichBlockPullQuotation.TYPE -> InputRichBlockPullQuotation.serializer()
            InputRichBlockCollage.TYPE -> InputRichBlockCollage.serializer()
            InputRichBlockSlideshow.TYPE -> InputRichBlockSlideshow.serializer()
            InputRichBlockTable.TYPE -> InputRichBlockTable.serializer()
            InputRichBlockDetails.TYPE -> InputRichBlockDetails.serializer()
            InputRichBlockMap.TYPE -> InputRichBlockMap.serializer()
            InputRichBlockButtons.TYPE -> InputRichBlockButtons.serializer()
            InputRichBlockAnimation.TYPE -> InputRichBlockAnimation.serializer()
            InputRichBlockAudio.TYPE -> InputRichBlockAudio.serializer()
            InputRichBlockDocument.TYPE -> InputRichBlockDocument.serializer()
            InputRichBlockPhoto.TYPE -> InputRichBlockPhoto.serializer()
            InputRichBlockVideo.TYPE -> InputRichBlockVideo.serializer()
            InputRichBlockVoiceNote.TYPE -> InputRichBlockVoiceNote.serializer()
            InputRichBlockThinking.TYPE -> InputRichBlockThinking.serializer()
            else -> error("Unknown InputRichBlock type: $type")
        }
    }
}
