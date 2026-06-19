package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.rich.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RichMessageSerializationTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun decodesRichMessageWithMixedRichText() {
        val source = """
            {
              "blocks": [
                {
                  "type": "paragraph",
                  "text": ["Hello ", {"type": "bold", "text": "world"}, "!"]
                },
                {"type": "heading", "text": "Title", "size": 1},
                {"type": "divider"},
                {"type": "list", "items": [
                  {"label": "1", "blocks": [{"type": "paragraph", "text": "first"}]}
                ]}
              ],
              "is_rtl": false
            }
        """.trimIndent()

        val message = json.decodeFromString(RichMessage.serializer(), source)
        assertEquals(4, message.blocks.size)

        val paragraph = message.blocks[0] as RichBlockParagraph
        val group = paragraph.text as RichTextGroup
        assertEquals(RichTextPlain("Hello "), group.parts[0])
        assertEquals(RichTextBold(RichTextPlain("world")), group.parts[1])
        assertEquals(RichTextPlain("!"), group.parts[2])

        val heading = message.blocks[1] as RichBlockSectionHeading
        assertEquals(RichTextPlain("Title"), heading.text)
        assertEquals(1, heading.size)

        assertTrue(message.blocks[2] is RichBlockDivider)

        val list = message.blocks[3] as RichBlockList
        assertEquals("1", list.items[0].label)
    }

    @Test
    fun roundTripsRichMessage() {
        val message = RichMessage(
            blocks = listOf(
                RichBlockParagraph(
                    RichTextGroup(
                        listOf(
                            RichTextPlain("a "),
                            RichTextItalic(RichTextPlain("b")),
                            RichTextUrl(RichTextPlain("link"), "https://example.org")
                        )
                    )
                ),
                RichBlockDivider()
            )
        )

        val encoded = json.encodeToString(RichMessage.serializer(), message)
        val decoded = json.decodeFromString(RichMessage.serializer(), encoded)
        assertEquals(message, decoded)
    }
}
