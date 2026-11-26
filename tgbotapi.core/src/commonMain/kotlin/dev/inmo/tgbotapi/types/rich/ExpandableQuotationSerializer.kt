package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.htmlField
import dev.inmo.tgbotapi.types.markdownField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.typeField
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.*

/** Older stored messages carry one rich text instead of an array of blocks. */
internal abstract class ExpandableQuotationSerializer<T : Any>(serializer: KSerializer<T>) :
    JsonTransformingSerializer<T>(serializer) {
    override fun transformDeserialize(element: JsonElement): JsonElement {
        val fields = element as? JsonObject ?: return element
        val text = fields[textField] ?: return fields
        val blocks = fields[blocksField] ?: buildJsonArray {
            add(buildJsonObject {
                put(typeField, RichBlockParagraph.TYPE)
                put(textField, text)
            })
        }
        // Bot API 10.3 objects saved with encodeDefaults also contain cached renderings.
        return JsonObject(fields - textField - htmlField - markdownField + (blocksField to blocks))
    }
}

@OptIn(ExperimentalSerializationApi::class)
internal object ReceivedExpandableQuotationSerializer :
    ExpandableQuotationSerializer<RichBlockExpandableBlockQuotation>(RichBlockExpandableBlockQuotation.generatedSerializer())

@OptIn(ExperimentalSerializationApi::class)
internal object InputExpandableQuotationSerializer :
    ExpandableQuotationSerializer<InputRichBlockExpandableBlockQuotation>(InputRichBlockExpandableBlockQuotation.generatedSerializer())
