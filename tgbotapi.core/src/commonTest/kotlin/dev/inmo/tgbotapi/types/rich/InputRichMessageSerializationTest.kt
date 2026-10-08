package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.requests.abstracts.asMultipartFile
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
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
                listOf(RichBlockTableCell.Header(RichTextPlain("h1"), align = RichBlockTableCellAlign.Left, valign = RichBlockTableCellVAlign.Top))
            )
        )
        val message = InputRichMessageBlocks(blocks = listOf(table))

        val tableJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray[0].jsonObject
        val cell = tableJson["cells"]!!.jsonArray[0].jsonArray[0].jsonObject
        assertEquals("left", cell["align"]?.jsonPrimitive?.content)
        assertEquals("top", cell["valign"]?.jsonPrimitive?.content)
        assertEquals(true, cell["is_header"]?.jsonPrimitive?.boolean)
    }

    @Test
    fun encodesCompactTablesButtonsAndDocuments() {
        val document = TelegramMediaDocument("content".encodeToByteArray().asMultipartFile("document.txt"))
        val message = InputRichMessageBlocks(
            blocks = listOf(
                InputRichBlockTable(emptyList(), isCompact = true),
                InputRichBlockButtons(listOf(RichMessageButton.Disabled(RichTextPlain("Disabled")))),
                InputRichBlockDocument(document)
            )
        )
        val blocks = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["blocks"]!!.jsonArray
        assertEquals(true, blocks[0].jsonObject["is_compact"]?.jsonPrimitive?.boolean)
        assertEquals("buttons", blocks[1].jsonObject["type"]?.jsonPrimitive?.content)
        assertEquals("document", blocks[2].jsonObject["type"]?.jsonPrimitive?.content)
        assertEquals(document.file, message.multipartFiles.values.single())
    }

    @Test
    fun limitsInputButtonRowsToOneThroughEightButtons() {
        assertFailsWith<IllegalArgumentException> { InputRichBlockButtons(emptyList()) }
        assertFailsWith<IllegalArgumentException> { InputRichBlockButtons(List(9) { RichMessageButton.Disabled(RichTextPlain("x")) }) }
    }

    @Test
    fun serializesTableCellVariantsWithSharedFlatShape() {
        val header: RichBlockTableCell = RichBlockTableCell.Header(RichTextPlain("h"), align = RichBlockTableCellAlign.Left, valign = RichBlockTableCellVAlign.Top)
        val regular: RichBlockTableCell = RichBlockTableCell.Regular(RichTextPlain("v"), align = RichBlockTableCellAlign.Right, valign = RichBlockTableCellVAlign.Bottom)

        val headerElement = json.encodeToJsonElement(RichBlockTableCell.Serializer, header).jsonObject
        val regularElement = json.encodeToJsonElement(RichBlockTableCell.Serializer, regular).jsonObject
        assertEquals(true, headerElement["is_header"]?.jsonPrimitive?.boolean)
        assertTrue("is_header" !in regularElement)
        assertEquals(header, json.decodeFromJsonElement(RichBlockTableCell.Serializer, headerElement))
        assertEquals(regular, json.decodeFromJsonElement(RichBlockTableCell.Serializer, regularElement))
        assertTrue(json.decodeFromString(RichBlockTableCell.Serializer, "{\"align\":\"left\",\"valign\":\"top\",\"is_header\":false}") is RichBlockTableCell.Regular)
    }

    @Test
    fun serializesEveryTableCellAlignmentAsItsName() {
        val alignments = listOf(
            RichBlockTableCellAlign.Left,
            RichBlockTableCellAlign.Center,
            RichBlockTableCellAlign.Right
        )

        alignments.forEach { alignment ->
            val encoded = json.encodeToString(RichBlockTableCellAlign.Serializer, alignment)
            assertEquals("\"${alignment.name}\"", encoded)
            assertEquals(alignment, json.decodeFromString(RichBlockTableCellAlign.Serializer, encoded))
        }
    }

    @Test
    fun rejectsUnknownTableCellAlignment() {
        assertFailsWith<SerializationException> {
            json.decodeFromString(RichBlockTableCellAlign.Serializer, "\"justify\"")
        }
    }

    @Test
    fun serializesEveryTableCellVerticalAlignmentAsItsName() {
        val alignments = listOf(
            RichBlockTableCellVAlign.Top,
            RichBlockTableCellVAlign.Middle,
            RichBlockTableCellVAlign.Bottom
        )

        alignments.forEach { alignment ->
            val encoded = json.encodeToString(RichBlockTableCellVAlign.Serializer, alignment)
            assertEquals("\"${alignment.name}\"", encoded)
            assertEquals(alignment, json.decodeFromString(RichBlockTableCellVAlign.Serializer, encoded))
        }
    }

    @Test
    fun rejectsUnknownTableCellVerticalAlignment() {
        assertFailsWith<SerializationException> {
            json.decodeFromString(RichBlockTableCellVAlign.Serializer, "\"baseline\"")
        }
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
    fun encodesDocumentMediaReferences() {
        val message = InputRichMessageMarkdown(
            markdown = "See [document](tg://document?id=doc)",
            media = listOf(InputRichMessageMedia("doc", TelegramMediaDocument(FileId("document_file_id"))))
        )
        val mediaJson = json.encodeToJsonElement(InputRichMessage.serializer(), message).jsonObject["media"]!!.jsonArray.single().jsonObject
        assertEquals("document", mediaJson["media"]!!.jsonObject["type"]?.jsonPrimitive?.content)
        assertEquals("document_file_id", mediaJson["media"]!!.jsonObject["media"]?.jsonPrimitive?.content)
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
