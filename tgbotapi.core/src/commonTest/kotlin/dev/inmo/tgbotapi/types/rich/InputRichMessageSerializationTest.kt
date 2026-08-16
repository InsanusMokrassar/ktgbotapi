package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVoiceNote
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.int
import kotlinx.serialization.json.boolean
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class InputRichMessageSerializationTest {
    private val json = Json { encodeDefaults = true }

    @Test
    fun encodesTextAndContainerBlocks() {
        val message = InputRichMessageBlocks(
            blocks = listOf(
                InputRichBlockParagraph(RichTextPlain("Hello")),
                InputRichBlockSectionHeading(RichTextPlain("Title"), 2),
                InputRichBlockDivider(),
                InputRichBlockList(
                    listOf(
                        InputRichBlockListItem.Ordered(
                            listOf(InputRichBlockParagraph(RichTextPlain("first"))),
                            value = 1
                        )
                    )
                )
            )
        )

        val blocksJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray

        val paragraph = blocksJson[0].jsonObject
        assertEquals("paragraph", paragraph["type"]?.jsonPrimitive?.content)

        val heading = blocksJson[1].jsonObject
        assertEquals("heading", heading["type"]?.jsonPrimitive?.content)
        assertEquals(2, heading["size"]?.jsonPrimitive?.int)

        val divider = blocksJson[2].jsonObject
        assertEquals("divider", divider["type"]?.jsonPrimitive?.content)
        assertEquals(1, divider.size)

        val list = blocksJson[3].jsonObject
        assertEquals("list", list["type"]?.jsonPrimitive?.content)
        val item = list["items"]!!.jsonArray[0].jsonObject
        assertTrue("label" !in item)
        assertEquals("1", item["type"]?.jsonPrimitive?.content)
    }

    @Test
    fun encodesTableReusingReceivedCellType() {
        val table = InputRichBlockTable(
            cells = listOf(
                listOf(RichBlockTableCell(RichTextPlain("h1"), isHeader = true, align = "left", valign = "top"))
            )
        )
        val message = InputRichMessageBlocks(blocks = listOf(table))

        val tableJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray[0].jsonObject
        val cell = tableJson["cells"]!!.jsonArray[0].jsonArray[0].jsonObject
        assertEquals("left", cell["align"]?.jsonPrimitive?.content)
        assertEquals(true, cell["is_header"]?.jsonPrimitive?.boolean)
    }

    @Test
    fun encodesPhotoBlockMedia() {
        val photoBlock = InputRichBlockPhoto(TelegramMediaPhoto(FileId("photo_file_id")))
        val message = InputRichMessageBlocks(blocks = listOf(photoBlock))

        val blockJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray[0].jsonObject
        assertEquals("photo", blockJson["type"]?.jsonPrimitive?.content)
        val photoJson = blockJson["photo"]!!.jsonObject
        assertEquals("photo", photoJson["type"]?.jsonPrimitive?.content)
        assertEquals("photo_file_id", photoJson["media"]?.jsonPrimitive?.content)
    }

    @Test
    fun encodesVoiceNoteBlock() {
        val voiceBlock = InputRichBlockVoiceNote(TelegramMediaVoiceNote(FileId("voice_file_id"), duration = 12))
        val message = InputRichMessageBlocks(blocks = listOf(voiceBlock))

        val blockJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray[0].jsonObject
        assertEquals("voice_note", blockJson["type"]?.jsonPrimitive?.content)
        val voiceJson = blockJson["voice_note"]!!.jsonObject
        assertEquals("voice_note", voiceJson["type"]?.jsonPrimitive?.content)
        assertEquals("voice_file_id", voiceJson["media"]?.jsonPrimitive?.content)
        assertEquals(12, voiceJson["duration"]?.jsonPrimitive?.int)
    }

    @Test
    fun encodesMarkdownWithMedia() {
        val message = InputRichMessageMarkdown(
            markdown = "See ![photo](tg://photo?id=abc)",
            media = listOf(InputRichMessageMedia("abc", TelegramMediaPhoto(FileId("photo_file_id"))))
        )

        val element = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject
        assertEquals("See ![photo](tg://photo?id=abc)", element["markdown"]?.jsonPrimitive?.content)
        val mediaJson = element["media"]!!.jsonArray[0].jsonObject
        assertEquals("abc", mediaJson["id"]?.jsonPrimitive?.content)
        assertEquals("photo_file_id", mediaJson["media"]!!.jsonObject["media"]?.jsonPrimitive?.content)
    }

    @Test
    fun roundTripsListOfInputRichBlocks() {
        val blocks: List<InputRichBlock> = listOf(
            InputRichBlockParagraph(RichTextGroup(listOf(RichTextPlain("a "), RichTextBold(RichTextPlain("b"))))),
            InputRichBlockDivider(),
            InputRichBlockList(
                listOf(
                    InputRichBlockListItem.Unordered(
                        listOf(InputRichBlockParagraph(RichTextPlain("item"))),
                        hasCheckbox = true,
                        isChecked = true
                    )
                )
            )
        )

        val serializer = ListSerializer(InputRichBlockSerializer)
        val encoded = json.encodeToString(serializer, blocks)
        val decoded = json.decodeFromString(serializer, encoded)
        assertEquals(blocks, decoded)
    }

    @Test
    fun serializesAndDeserializesListItemVariantsWithFlatShape() {
        val ordered: InputRichBlockListItem = InputRichBlockListItem.Ordered(
            blocks = emptyList(),
            value = 4,
            labelType = LabelType.RomanUppercase
        )
        val unordered: InputRichBlockListItem = InputRichBlockListItem.Unordered(blocks = emptyList())

        val orderedElement = json.encodeToJsonElement(InputRichBlockListItem.Serializer, ordered).jsonObject
        assertEquals(4, orderedElement["value"]?.jsonPrimitive?.int)
        assertEquals("I", orderedElement["type"]?.jsonPrimitive?.content)
        assertEquals(ordered, json.decodeFromJsonElement(InputRichBlockListItem.Serializer, orderedElement))
        assertEquals(unordered, json.decodeFromString(InputRichBlockListItem.Serializer, "{\"blocks\":[]}"))
    }

    @Test
    fun serializesEveryLabelTypeAsItsSymbol() {
        val labelTypes = listOf(
            LabelType.LettersUppercase,
            LabelType.LettersLowercase,
            LabelType.RomanUppercase,
            LabelType.RomanLowercase,
            LabelType.Decimals
        )

        labelTypes.forEach { labelType ->
            val encoded = json.encodeToString(LabelType.Serializer, labelType)
            assertEquals("\"${labelType.typeSymbol}\"", encoded)
            assertEquals(labelType, json.decodeFromString(LabelType.Serializer, encoded))
        }
    }

    @Test
    fun rejectsIncompleteOrderedListItemsAndUnknownLabelTypes() {
        assertFailsWith<SerializationException> {
            json.decodeFromString(InputRichBlockListItem.Serializer, "{\"blocks\":[],\"value\":1}")
        }
        assertFailsWith<SerializationException> {
            json.decodeFromString(InputRichBlockListItem.Serializer, "{\"blocks\":[],\"type\":\"1\"}")
        }
        assertFailsWith<SerializationException> {
            json.decodeFromString(LabelType.Serializer, "\"?\"")
        }
    }

    @Test
    fun requiresExactlyOneOfHtmlMarkdownOrBlocks() {
        assertFailsWith<IllegalArgumentException> {
            InputRichMessage(html = null, markdown = null, blocks = null)
        }
        assertFailsWith<IllegalArgumentException> {
            InputRichMessage(html = "<b>hi</b>", markdown = "**hi**", blocks = null)
        }
        assertFailsWith<IllegalArgumentException> {
            InputRichMessage(
                html = "<b>hi</b>",
                markdown = null,
                blocks = listOf(InputRichBlockParagraph(RichTextPlain("hi")))
            )
        }
    }

    @Test
    fun requiresMediaOnlyWithoutBlocks() {
        assertFailsWith<IllegalArgumentException> {
            InputRichMessage(
                html = null,
                markdown = null,
                blocks = listOf(InputRichBlockParagraph(RichTextPlain("hi"))),
                media = listOf(InputRichMessageMedia("abc", TelegramMediaPhoto(FileId("photo_file_id"))))
            )
        }
    }

    @Test
    fun requiresValidMediaId() {
        assertFailsWith<IllegalArgumentException> {
            InputRichMessageMedia("", TelegramMediaPhoto(FileId("photo_file_id")))
        }
        assertFailsWith<IllegalArgumentException> {
            InputRichMessageMedia("has space", TelegramMediaPhoto(FileId("photo_file_id")))
        }
        assertFailsWith<IllegalArgumentException> {
            InputRichMessageMedia("x".repeat(65), TelegramMediaPhoto(FileId("photo_file_id")))
        }
    }
}
