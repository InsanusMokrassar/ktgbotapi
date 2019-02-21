package com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode

import kotlinx.serialization.*

internal const val parseModeField = "parse_mode"

@Serializable(ParseModeSerializerObject::class)
sealed class ParseMode {
    abstract val parseModeName: String
}

@Serializable(ParseModeSerializerObject::class)
object MarkdownParseMode : ParseMode() {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "Markdown"
}

@Serializable(ParseModeSerializerObject::class)
object HTMLParseMode : ParseMode() {
    @Serializable
    @SerialName(parseModeField)
    override val parseModeName: String = "HTML"
}

@Serializer(ParseMode::class)
internal class ParseModeSerializerObject: KSerializer<ParseMode> {
    override fun deserialize(decoder: Decoder): ParseMode {
        val mode = decoder.decodeString()
        return when (mode) {
            MarkdownParseMode.parseModeName -> MarkdownParseMode
            HTMLParseMode.parseModeName -> HTMLParseMode
            else -> throw IllegalArgumentException("Unknown parse mode")
        }
    }

    override fun serialize(encoder: Encoder, obj: ParseMode) {
        encoder.encodeString(obj.parseModeName)
    }
}
