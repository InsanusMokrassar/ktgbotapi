package dev.inmo.tgbotapi.types.ParseMode

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal const val parseModeField = "parse_mode"

@Serializable(ParseModeSerializerObject::class)
sealed interface ParseMode {
    val parseModeName: String
}

@Serializable(ParseModeSerializerObject::class)
object MarkdownParseMode : ParseMode {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "Markdown"
}

@Serializable(ParseModeSerializerObject::class)
object MarkdownV2ParseMode : ParseMode {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "MarkdownV2"
}
@Serializable(ParseModeSerializerObject::class)
object HTMLParseMode : ParseMode {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "HTML"
}

typealias Markdown = MarkdownParseMode
typealias MarkdownV2 = MarkdownV2ParseMode
typealias HTML = HTMLParseMode

/**
 * This variable respects to default parse mode used in places like next:
 *
 * * [dev.inmo.tgbotapi.types.message.content.TextContent.createResends]
 * *
 */
var defaultParseMode: ParseMode = HTML

@RiskFeature
object ParseModeSerializerObject : KSerializer<ParseMode> {
    override val descriptor: SerialDescriptor = String.serializer().descriptor
    override fun deserialize(decoder: Decoder): ParseMode {
        return when (decoder.decodeString()) {
            Markdown.parseModeName -> Markdown
            MarkdownV2.parseModeName -> MarkdownV2
            HTML.parseModeName -> HTML
            else -> throw IllegalArgumentException("Unknown parse mode")
        }
    }

    override fun serialize(encoder: Encoder, value: ParseMode) {
        encoder.encodeString(value.parseModeName)
    }
}
