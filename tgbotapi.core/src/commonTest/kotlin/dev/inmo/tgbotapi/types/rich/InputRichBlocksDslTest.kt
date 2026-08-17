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
            unorderedList {
                item("first")
                item { paragraph("second") }
            }
            orderedList {
                item(value = 2) { paragraph("third") }
            }
            blockQuotation {
                paragraph("quoted")
            }
        }

        assertEquals(6, blocks.size)
        assertEquals(InputRichBlockSectionHeading(RichTextPlain("Title"), 1), blocks[0])
        assertEquals(
            InputRichBlockParagraph(RichTextGroup(listOf(RichTextPlain("Hello "), RichTextBold(RichTextPlain("world"))))),
            blocks[1]
        )
        assertEquals(InputRichBlockDivider(), blocks[2])

        val list = blocks[3] as InputRichBlockList
        assertEquals(2, list.items.size)
        assertEquals(InputRichBlockListItem.Unordered(listOf(InputRichBlockParagraph(RichTextPlain("first")))), list.items[0])
        assertEquals(InputRichBlockListItem.Unordered::class, list.items[1]::class)

        val orderedList = blocks[4] as InputRichBlockList
        assertEquals(LabelType.Decimals, (orderedList.items.single() as InputRichBlockListItem.Ordered).labelType)

        assertEquals(InputRichBlockBlockQuotation(listOf(InputRichBlockParagraph(RichTextPlain("quoted")))), blocks[5])
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

    @Test
    fun buildsHeadingShortcuts() {
        val blocks = buildInputRichBlocks {
            h1("h1")
            h2 { bold("h2") }
            h3("h3")
            h4 { plain("h4") }
            h5("h5")
            h6 { italic("h6") }
        }

        assertEquals((1..6).toList(), blocks.map { (it as InputRichBlockSectionHeading).level })
        assertEquals(RichTextPlain("h1"), (blocks[0] as InputRichBlockSectionHeading).text)
        assertEquals(RichTextBold(RichTextPlain("h2")), (blocks[1] as InputRichBlockSectionHeading).text)
    }

    @Test
    fun buildsTable() {
        val blocks = buildInputRichBlocks {
            table(isBordered = true, isStriped = false, caption = RichTextPlain("caption")) {
                row {
                    headerCell(align = RichBlockTableCellAlign.Left, valign = RichBlockTableCellVAlign.Top, colspan = 2) {
                        bold("heading")
                    }
                }
                row {
                    cell(align = RichBlockTableCellAlign.Center, valign = RichBlockTableCellVAlign.Middle, rowspan = 2) {
                        plain("value")
                    }
                }
            }
        }

        assertEquals(
            listOf(
                InputRichBlockTable(
                    cells = listOf(
                        listOf(
                            RichBlockTableCell.Header(
                                text = RichTextBold(RichTextPlain("heading")),
                                colspan = 2,
                                align = RichBlockTableCellAlign.Left,
                                valign = RichBlockTableCellVAlign.Top
                            )
                        ),
                        listOf(
                            RichBlockTableCell.Regular(
                                text = RichTextPlain("value"),
                                rowspan = 2,
                                align = RichBlockTableCellAlign.Center,
                                valign = RichBlockTableCellVAlign.Middle
                            )
                        )
                    ),
                    isBordered = true,
                    isStriped = false,
                    caption = RichTextPlain("caption")
                )
            ),
            blocks
        )
    }
}
