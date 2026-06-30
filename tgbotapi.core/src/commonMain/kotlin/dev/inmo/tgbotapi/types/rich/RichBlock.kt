package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents a block in a rich formatted message.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblock">RichBlock</a>
 */
@Serializable(RichBlockSerializer::class)
@ClassCastsIncluded
sealed interface RichBlock {
    val type: String

    /**
     * [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) source of this single [RichBlock].
     */
    val markdown: String

    /**
     * [Rich HTML style](https://core.telegram.org/bots/api#rich-html-style) source of this single [RichBlock].
     */
    val html: String
}

object RichBlockSerializer : JsonContentPolymorphicSerializer<RichBlock>(RichBlock::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RichBlock> {
        return when (val type = element.jsonObject[typeField]?.jsonPrimitive?.content) {
            RichBlockParagraph.TYPE -> RichBlockParagraph.serializer()
            RichBlockSectionHeading.TYPE -> RichBlockSectionHeading.serializer()
            RichBlockPreformatted.TYPE -> RichBlockPreformatted.serializer()
            RichBlockFooter.TYPE -> RichBlockFooter.serializer()
            RichBlockDivider.TYPE -> RichBlockDivider.serializer()
            RichBlockMathematicalExpression.TYPE -> RichBlockMathematicalExpression.serializer()
            RichBlockAnchor.TYPE -> RichBlockAnchor.serializer()
            RichBlockList.TYPE -> RichBlockList.serializer()
            RichBlockBlockQuotation.TYPE -> RichBlockBlockQuotation.serializer()
            RichBlockPullQuotation.TYPE -> RichBlockPullQuotation.serializer()
            RichBlockCollage.TYPE -> RichBlockCollage.serializer()
            RichBlockSlideshow.TYPE -> RichBlockSlideshow.serializer()
            RichBlockTable.TYPE -> RichBlockTable.serializer()
            RichBlockDetails.TYPE -> RichBlockDetails.serializer()
            RichBlockMap.TYPE -> RichBlockMap.serializer()
            RichBlockAnimation.TYPE -> RichBlockAnimation.serializer()
            RichBlockAudio.TYPE -> RichBlockAudio.serializer()
            RichBlockPhoto.TYPE -> RichBlockPhoto.serializer()
            RichBlockVideo.TYPE -> RichBlockVideo.serializer()
            RichBlockVoiceNote.TYPE -> RichBlockVoiceNote.serializer()
            RichBlockThinking.TYPE -> RichBlockThinking.serializer()
            else -> error("Unknown RichBlock type: $type")
        }
    }
}
