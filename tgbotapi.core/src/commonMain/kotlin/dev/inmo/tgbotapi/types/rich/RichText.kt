package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.utils.extensions.toHtml
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
sealed interface RichText {
    /**
     * Plain (unformatted) text of this [RichText]. For [RichTextEntity]s without an inner [RichText] it falls back to
     * the most meaningful textual representation: alternative text for custom emojis, the expression for mathematical
     * expressions and an empty string for anchors.
     */
    val rawText: String

    /**
     * [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) representation of this [RichText].
     */
    val markdown: String

    /**
     * [Rich HTML style](https://core.telegram.org/bots/api#rich-html-style) representation of this [RichText].
     */
    val html: String

    /** Whether this text is permitted as [RichMessageButton] text. */
    val isValidRichMessageButtonText: Boolean
}

/**
 * A plain (non-formatted) part of a [RichText]. Serialized as a bare JSON string.
 */
@Serializable
data class RichTextPlain(
    val text: String
) : RichText {
    override val rawText: String = text
    override val markdown: String = markdown(text)
    override val html: String = html(text)
    override val isValidRichMessageButtonText: Boolean = true

    companion object {
        fun markdown(text: String): String = text.escapeRichMarkdown()
        fun html(text: String): String = text.toHtml()
    }
}

/**
 * A group of [RichText]s. Serialized as a JSON array.
 */
@Serializable
data class RichTextGroup(
    val parts: List<RichText>
) : RichText {
    override val rawText: String = parts.joinToString(separator = "") { it.rawText }
    override val markdown: String = markdown(parts)
    override val html: String = html(parts)
    override val isValidRichMessageButtonText: Boolean = parts.all { it.isValidRichMessageButtonText }

    companion object {
        fun markdown(parts: List<RichText>): String = parts.joinToString(separator = "") { it.markdown }
        fun html(parts: List<RichText>): String = parts.joinToString(separator = "") { it.html }
    }
}

/**
 * Any typed (formatted) part of a [RichText]. Serialized as a JSON object with the [type] discriminator.
 */
@Serializable(RichTextEntitySerializer::class)
sealed interface RichTextEntity : RichText {
    val type: String

    override val markdown: String
    override val html: String
    override val isValidRichMessageButtonText: Boolean
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
            RichTextButton.TYPE -> RichTextButton.serializer()
            RichTextAnchor.TYPE -> RichTextAnchor.serializer()
            RichTextAnchorLink.TYPE -> RichTextAnchorLink.serializer()
            RichTextReference.TYPE -> RichTextReference.serializer()
            RichTextReferenceLink.TYPE -> RichTextReferenceLink.serializer()
            else -> error("Unknown RichTextEntity type: $type")
        }
    }
}
