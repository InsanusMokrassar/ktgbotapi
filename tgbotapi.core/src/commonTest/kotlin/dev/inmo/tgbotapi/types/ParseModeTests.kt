package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.TestsJsonFormat
import dev.inmo.tgbotapi.types.ParseMode.*
import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class ParseModeTests {
    @Serializable
    data class Example(
        @Serializable(ParseModeSerializer::class)
        val mode: ParseMode
    )

    @Test
    fun `Markdown_parse_mode_correctly_serializing_and_deserializing`() {
        val example = Example(Markdown)

        val stringified = TestsJsonFormat.encodeToString(Example.serializer(), example)
        assertEquals("{\"mode\":\"Markdown\"}", stringified)

        val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
        assertEquals(example, deserialized)
    }

    @Test
    fun `HTML_parse_mode_correctly_serializing_and_deserializing`() {
        val example = Example(HTML)

        val stringified = TestsJsonFormat.encodeToString(Example.serializer(), example)
        assertEquals("{\"mode\":\"HTML\"}", stringified)

        val deserialized = TestsJsonFormat.decodeFromString(Example.serializer(), stringified)
        assertEquals(example, deserialized)
    }
}
