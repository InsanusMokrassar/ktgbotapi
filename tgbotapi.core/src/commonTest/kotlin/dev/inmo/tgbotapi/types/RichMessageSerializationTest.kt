package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.types.rich.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails
import kotlin.test.assertFailsWith
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

        val message = json.decodeFromString(RichTextInfo.serializer(), source)
        assertEquals(4, message.blocks.size)

        val paragraph = message.blocks[0] as RichBlockParagraph
        val group = paragraph.text as RichTextGroup
        assertEquals(RichTextPlain("Hello "), group.parts[0])
        assertEquals(RichTextBold(RichTextPlain("world")), group.parts[1])
        assertEquals(RichTextPlain("!"), group.parts[2])

        val heading = message.blocks[1] as RichBlockSectionHeading
        assertEquals(RichTextPlain("Title"), heading.text)
        assertEquals(1, heading.level)

        assertTrue(message.blocks[2] is RichBlockDivider)

        val list = message.blocks[3] as RichBlockList
        assertEquals("1", list.items[0].label)
    }

    @Test
    fun roundTripsRichMessage() {
        val message = RichTextInfo(
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

        val encoded = json.encodeToString(RichTextInfo.serializer(), message)
        val decoded = json.decodeFromString(RichTextInfo.serializer(), encoded)
        assertEquals(message, decoded)
    }

    @Test
    fun serializesRichMessageButtonsWithFlatActionShape() {
        val button: RichMessageButton = RichMessageButton.CallbackData(
            text = RichTextPlain("Press"),
            callbackData = "callback",
            style = RichMessageButtonStyle.Link
        )
        val encoded = json.encodeToString(RichMessageButtonSerializer, button)
        val element = json.parseToJsonElement(encoded).jsonObject
        assertEquals("callback", element["callback_data"]?.jsonPrimitive?.content)
        assertTrue("url" !in element)
        assertEquals(button, json.decodeFromString(RichMessageButtonSerializer, encoded))
        assertEquals(
            RichMessageButton.Disabled(RichTextPlain("Disabled")),
            json.decodeFromString(RichMessageButtonSerializer, "{\"text\":\"Disabled\",\"disabled\":{}}")
        )
    }

    @Test
    fun rejectsRichMessageButtonsWithZeroOrMultipleActions() {
        kotlin.test.assertFailsWith<kotlinx.serialization.SerializationException> {
            json.decodeFromString(RichMessageButtonSerializer, "{\"text\":\"none\"}")
        }
        kotlin.test.assertFailsWith<kotlinx.serialization.SerializationException> {
            json.decodeFromString(RichMessageButtonSerializer, "{\"text\":\"multiple\",\"url\":\"https://t.me\",\"disabled\":{}}")
        }
    }

    @Test
    fun validatesRichMessageButtonTextContract() {
        val plain = RichTextPlain("plain")
        val customEmoji = RichTextCustomEmoji(CustomEmojiId("emoji"), "emoji")
        val dateTime = RichTextDateTime(RichTextPlain("date"), TelegramDate(1L), "wDT")
        val validTexts = listOf(
            plain,
            customEmoji,
            dateTime,
            RichTextGroup(listOf(plain, customEmoji, dateTime, RichTextGroup(listOf(plain))))
        )

        validTexts.forEach { text ->
            assertEquals(true, text.isValidRichMessageButtonText)
            assertEquals(text, RichMessageButton.Disabled(text).text)
        }

        val invalidTexts = listOf(
            RichTextBold(plain),
            RichTextUrl(plain, "https://example.org"),
            RichTextGroup(listOf(plain, RichTextBold(plain))),
            RichTextDateTime(RichTextBold(plain), TelegramDate(1L), "wDT")
        )
        invalidTexts.forEach { text ->
            assertEquals(false, text.isValidRichMessageButtonText)
            assertFailsWith<IllegalArgumentException> { RichMessageButton.Disabled(text) }
        }

        assertFails {
            json.decodeFromString(
                RichMessageButtonSerializer,
                "{\"text\":{\"type\":\"bold\",\"text\":\"invalid\"},\"disabled\":{}}"
            )
        }
    }

    @Test
    fun rejectsBotUsernameInRichMessageLoginUrlButtons() {
        assertFailsWith<IllegalArgumentException> {
            RichMessageButton.LoginUrl(
                RichTextPlain("Login"),
                LoginURL("https://example.org", botUsername = "example_bot")
            )
        }

        assertFails {
            json.decodeFromString(
                RichMessageButtonSerializer,
                "{\"text\":\"Login\",\"login_url\":{\"url\":\"https://example.org\",\"bot_username\":\"example_bot\"}}"
            )
        }
    }

    @Test
    fun decodesNewRichTextAndBlockVariants() {
        val text = json.decodeFromString(RichTextSerializer, "{\"type\":\"button\",\"button\":{\"text\":\"Press\",\"disabled\":{}}}")
        assertEquals(RichTextButton(RichMessageButton.Disabled(RichTextPlain("Press"))), text)
        val blocks = json.decodeFromString(
            kotlinx.serialization.builtins.ListSerializer(RichBlockSerializer),
            "[{\"type\":\"buttons\",\"buttons\":[{\"text\":\"Press\",\"disabled\":{}}],\"align\":\"center\"},{\"type\":\"expandable_blockquote\",\"text\":\"quote\"}]"
        )
        assertEquals(RichBlockButtons(listOf(RichMessageButton.Disabled(RichTextPlain("Press"))), RichBlockButtonAlignment.Center), blocks[0])
        assertEquals(RichBlockExpandableBlockQuotation(RichTextPlain("quote")), blocks[1])
    }
}
