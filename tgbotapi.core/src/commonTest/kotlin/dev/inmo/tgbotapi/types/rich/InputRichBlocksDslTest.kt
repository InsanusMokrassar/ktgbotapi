package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.requests.abstracts.FileId
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import kotlin.test.Test
import kotlin.test.assertEquals

class InputRichBlocksDslTest {
    @Test
    fun buildsTextAndContainerBlocks() {
        val blocks = buildInputRichBlocks {
            heading("Title", 1)
            paragraph {
                plain("Hello ")
                bold("world")
            }
            divider()
            list {
                item("first")
                item(labelType = "1") { paragraph("second") }
            }
            blockQuotation {
                paragraph("quoted")
            }
        }

        assertEquals(5, blocks.size)
        assertEquals(InputRichBlockSectionHeading(RichTextPlain("Title"), 1), blocks[0])
        assertEquals(
            InputRichBlockParagraph(RichTextGroup(listOf(RichTextPlain("Hello "), RichTextBold(RichTextPlain("world"))))),
            blocks[1]
        )
        assertEquals(InputRichBlockDivider(), blocks[2])

        val list = blocks[3] as InputRichBlockList
        assertEquals(2, list.items.size)
        assertEquals(InputRichBlockListItem(listOf(InputRichBlockParagraph(RichTextPlain("first")))), list.items[0])
        assertEquals("1", list.items[1].labelType)

        assertEquals(InputRichBlockBlockQuotation(listOf(InputRichBlockParagraph(RichTextPlain("quoted")))), blocks[4])
    }

    @Test
    fun buildsMediaBlocks() {
        val photo = TelegramMediaPhoto(FileId("photo_file_id"))
        val blocks = buildInputRichBlocks {
            photo(photo)
        }
        assertEquals(listOf(InputRichBlockPhoto(photo)), blocks)
    }

    @Test
    fun buildsInputRichMessageBlocks() {
        val message = InputRichMessageBlocks(isRtl = true) {
            paragraph("p")
        }
        assertEquals(InputRichMessageBlocks(listOf(InputRichBlockParagraph(RichTextPlain("p"))), true), message)
    }
}
