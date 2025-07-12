@file:Suppress("SERIALIZER_TYPE_INCOMPATIBLE")

package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal const val parseModeField = "parse_mode"

@Serializable(ParseModeSerializer::class)
sealed interface ParseMode {
    val parseModeName: String
}

@Serializable(ParseModeSerializer::class)
object MarkdownParseMode : ParseMode {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "Markdown"
}

@Serializable(ParseModeSerializer::class)
object MarkdownV2ParseMode : ParseMode {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "MarkdownV2"
}
@Serializable(ParseModeSerializer::class)
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
object ParseModeSerializer : KSerializer<ParseMode> {
    override val descriptor: SerialDescriptor = String.serializer().descriptor
    override fun deserialize(decoder: Decoder): ParseMode {
        return when (decoder.decodeString()) {
            MarkdownParseMode.parseModeName -> Markdown
            MarkdownV2ParseMode.parseModeName -> MarkdownV2
            HTMLParseMode.parseModeName -> HTML
            else -> throw IllegalArgumentException("Unknown parse mode")
        }
    }

    override fun serialize(encoder: Encoder, value: ParseMode) {
        encoder.encodeString(value.parseModeName)
    }
}
