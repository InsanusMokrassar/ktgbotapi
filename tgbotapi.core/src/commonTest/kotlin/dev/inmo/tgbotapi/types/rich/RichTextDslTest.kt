package dev.inmo.tgbotapi.types.rich

import kotlin.test.Test
import kotlin.test.assertEquals

class RichTextDslTest {
    @Test
    fun buildsRichTextGroup() {
        val richText = buildRichText {
            plain("a ")
            bold("b")
            italic {
                plain("c")
                bold("d")
            }
        }
        assertEquals(
            RichTextGroup(
                listOf(
                    RichTextPlain("a "),
                    RichTextBold(RichTextPlain("b")),
                    RichTextItalic(RichTextGroup(listOf(RichTextPlain("c"), RichTextBold(RichTextPlain("d")))))
                )
            ),
            richText
        )
    }

    @Test
    fun singlePartUnwraps() {
        assertEquals(RichTextBold(RichTextPlain("x")), buildRichText { bold("x") })
    }

    @Test
    fun rendersMarkdown() {
        assertEquals("a **b**", buildRichText { plain("a "); bold("b") }.markdown)
    }

    @Test
    fun buildsBlocks() {
        val blocks = buildRichBlocks {
            heading(1, "Title")
            paragraph {
                plain("Hello ")
                bold("world")
            }
            divider()
            list {
                item("1", "first")
                item("2", labelType = "1") { paragraph("second") }
            }
            blockQuotation {
                paragraph("quoted")
            }
        }
        assertEquals(5, blocks.size)
        assertEquals(RichBlockSectionHeading(RichTextPlain("Title"), 1), blocks[0])
        assertEquals(
            RichBlockParagraph(RichTextGroup(listOf(RichTextPlain("Hello "), RichTextBold(RichTextPlain("world"))))),
            blocks[1]
        )
        assertEquals(RichBlockDivider(), blocks[2])
        val list = blocks[3] as RichBlockList
        assertEquals(2, list.items.size)
        assertEquals(RichBlockListItem("1", listOf(RichBlockParagraph(RichTextPlain("first")))), list.items[0])
        assertEquals(RichBlockBlockQuotation(listOf(RichBlockParagraph(RichTextPlain("quoted")))), blocks[4])
    }

    @Test
    fun buildsRichTextInfo() {
        val info = buildRichTextInfo(isRtl = true) {
            paragraph("p")
        }
        assertEquals(RichTextInfo(listOf(RichBlockParagraph(RichTextPlain("p"))), true), info)
        assertEquals("p", info.markdown)
    }
}
