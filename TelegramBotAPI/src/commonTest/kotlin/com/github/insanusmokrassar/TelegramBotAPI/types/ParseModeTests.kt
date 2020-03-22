package com.github.insanusmokrassar.TelegramBotAPI.types

import com.github.insanusmokrassar.TelegramBotAPI.TestsJsonFormat
import com.github.insanusmokrassar.TelegramBotAPI.types.ParseMode.*
import kotlinx.serialization.ImplicitReflectionSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

@ImplicitReflectionSerializer
class ParseModeTests {
    @Serializable
    data class Example(
        val mode: ParseMode
    )

    @Test
    fun `Markdown parse mode correctly serializing and deserializing`() {
        val example = Example(Markdown)

        val stringified = TestsJsonFormat.stringify(Example.serializer(), example)
        assertEquals("{\"mode\":\"Markdown\"}", stringified)

        val deserialized = TestsJsonFormat.parse(Example.serializer(), stringified)
        assertEquals(example, deserialized)
    }

    @Test
    fun `HTML parse mode correctly serializing and deserializing`() {
        val example = Example(HTML)

        val stringified = TestsJsonFormat.stringify(Example.serializer(), example)
        assertEquals("{\"mode\":\"HTML\"}", stringified)

        val deserialized = TestsJsonFormat.parse(Example.serializer(), stringified)
        assertEquals(example, deserialized)
    }
}
