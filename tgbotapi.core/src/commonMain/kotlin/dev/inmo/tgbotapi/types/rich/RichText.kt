package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

/**
 * Represents a rich formatted text. It can be either a plain text ([RichTextPlain]), a group of rich texts
 * ([RichTextGroup]) or any of [RichTextEntity] subtypes.
 *
 * @see <a href="https://core.telegram.org/bots/api#richtext">RichText</a>
 */
@Serializable(RichTextSerializer::class)
@ClassCastsIncluded
sealed interface RichText

/**
 * A plain (non-formatted) part of a [RichText]. Serialized as a bare JSON string.
 */
@Serializable
data class RichTextPlain(
    val text: String
) : RichText

/**
 * A group of [RichText]s. Serialized as a JSON array.
 */
@Serializable
data class RichTextGroup(
    val parts: List<RichText>
) : RichText

/**
 * Any typed (formatted) part of a [RichText]. Serialized as a JSON object with the [type] discriminator.
 */
@Serializable(RichTextEntitySerializer::class)
sealed interface RichTextEntity : RichText {
    val type: String

    val markdown: String
    val html: String
}

object RichTextSerializer : KSerializer<RichText> {
    override val descriptor: SerialDescriptor = JsonElement.serializer().descriptor

    private fun fromJson(json: Json, element: JsonElement): RichText = when (element) {
        is JsonArray -> RichTextGroup(element.map { fromJson(json, it) })
        is JsonObject -> json.decodeFromJsonElement(RichTextEntitySerializer, element)
        is JsonPrimitive -> RichTextPlain(element.content)
    }

    private fun toJson(json: Json, value: RichText): JsonElement = when (value) {
        is RichTextPlain -> JsonPrimitive(value.text)
        is RichTextGroup -> JsonArray(value.parts.map { toJson(json, it) })
        is RichTextEntity -> json.encodeToJsonElement(RichTextEntitySerializer, value)
    }

    override fun deserialize(decoder: Decoder): RichText {
        val input = decoder as JsonDecoder
        return fromJson(input.json, input.decodeJsonElement())
    }

    override fun serialize(encoder: Encoder, value: RichText) {
        val output = encoder as JsonEncoder
        output.encodeJsonElement(toJson(output.json, value))
    }
}

object RichTextEntitySerializer : JsonContentPolymorphicSerializer<RichTextEntity>(RichTextEntity::class) {
    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<RichTextEntity> {
        return when (val type = element.jsonObject[typeField]?.jsonPrimitive?.content) {
            RichTextBold.TYPE -> RichTextBold.serializer()
            RichTextItalic.TYPE -> RichTextItalic.serializer()
            RichTextUnderline.TYPE -> RichTextUnderline.serializer()
            RichTextStrikethrough.TYPE -> RichTextStrikethrough.serializer()
            RichTextSpoiler.TYPE -> RichTextSpoiler.serializer()
            RichTextDateTime.TYPE -> RichTextDateTime.serializer()
            RichTextTextMention.TYPE -> RichTextTextMention.serializer()
            RichTextSubscript.TYPE -> RichTextSubscript.serializer()
            RichTextSuperscript.TYPE -> RichTextSuperscript.serializer()
            RichTextMarked.TYPE -> RichTextMarked.serializer()
            RichTextCode.TYPE -> RichTextCode.serializer()
            RichTextCustomEmoji.TYPE -> RichTextCustomEmoji.serializer()
            RichTextMathematicalExpression.TYPE -> RichTextMathematicalExpression.serializer()
            RichTextUrl.TYPE -> RichTextUrl.serializer()
            RichTextEmailAddress.TYPE -> RichTextEmailAddress.serializer()
            RichTextPhoneNumber.TYPE -> RichTextPhoneNumber.serializer()
            RichTextBankCardNumber.TYPE -> RichTextBankCardNumber.serializer()
            RichTextMention.TYPE -> RichTextMention.serializer()
            RichTextHashtag.TYPE -> RichTextHashtag.serializer()
            RichTextCashtag.TYPE -> RichTextCashtag.serializer()
            RichTextBotCommand.TYPE -> RichTextBotCommand.serializer()
            RichTextAnchor.TYPE -> RichTextAnchor.serializer()
            RichTextAnchorLink.TYPE -> RichTextAnchorLink.serializer()
            RichTextReference.TYPE -> RichTextReference.serializer()
            RichTextReferenceLink.TYPE -> RichTextReferenceLink.serializer()
            else -> error("Unknown RichTextEntity type: $type")
        }
    }
}
