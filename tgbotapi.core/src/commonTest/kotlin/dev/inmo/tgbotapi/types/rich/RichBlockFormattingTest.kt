package dev.inmo.tgbotapi.types.rich

import kotlin.test.Test
import kotlin.test.assertEquals

class RichBlockFormattingTest {
    @Test
    fun paragraph() {
        val block = RichBlockParagraph(RichTextPlain("Hello"))
        assertEquals("Hello", block.markdown)
        assertEquals("<p>Hello</p>", block.html)
    }

    @Test
    fun heading() {
        val block = RichBlockSectionHeading(RichTextPlain("Title"), 2)
        assertEquals("## Title", block.markdown)
        assertEquals("<h2>Title</h2>", block.html)
    }

    @Test
    fun divider() {
        val block = RichBlockDivider()
        assertEquals("---", block.markdown)
        assertEquals("<hr/>", block.html)
    }

    @Test
    fun footer() {
        val block = RichBlockFooter(RichTextPlain("f"))
        assertEquals("<footer>f</footer>", block.markdown)
        assertEquals("<footer>f</footer>", block.html)
    }

    @Test
    fun preformatted() {
        val withLanguage = RichBlockPreformatted(RichTextPlain("code"), "kotlin")
        assertEquals("```kotlin\ncode\n```", withLanguage.markdown)
        assertEquals("<pre><code class=\"language-kotlin\">code</code></pre>", withLanguage.html)

        val withoutLanguage = RichBlockPreformatted(RichTextPlain("c"))
        assertEquals("```\nc\n```", withoutLanguage.markdown)
        assertEquals("<pre>c</pre>", withoutLanguage.html)
    }

    @Test
    fun mathematicalExpression() {
        val block = RichBlockMathematicalExpression("E=mc^2")
        assertEquals("\$\$E=mc^2\$\$", block.markdown)
        assertEquals("<tg-math-block>E=mc^2</tg-math-block>", block.html)
    }

    @Test
    fun anchor() {
        val block = RichBlockAnchor("top")
        assertEquals("<a name=\"top\"></a>", block.markdown)
        assertEquals("<a name=\"top\"></a>", block.html)
    }

    @Test
    fun bulletList() {
        val block = RichBlockList(listOf(RichBlockListItem("-", listOf(RichBlockParagraph(RichTextPlain("one"))))))
        assertEquals("- one", block.markdown)
        assertEquals("<ul><li><p>one</p></li></ul>", block.html)
    }

    @Test
    fun orderedList() {
        val block = RichBlockList(
            listOf(RichBlockListItem("1", listOf(RichBlockParagraph(RichTextPlain("one"))), labelType = "1"))
        )
        assertEquals("1. one", block.markdown)
        assertEquals("<ol><li type=\"1\"><p>one</p></li></ol>", block.html)
    }

    @Test
    fun taskList() {
        val block = RichBlockList(
            listOf(
                RichBlockListItem(
                    "x",
                    listOf(RichBlockParagraph(RichTextPlain("done"))),
                    hasCheckbox = true,
                    isChecked = true
                )
            )
        )
        assertEquals("- [x] done", block.markdown)
        assertEquals("<ul><li><input type=\"checkbox\" checked><p>done</p></li></ul>", block.html)
    }

    @Test
    fun blockQuotation() {
        val block = RichBlockBlockQuotation(listOf(RichBlockParagraph(RichTextPlain("q"))))
        assertEquals("> q", block.markdown)
        assertEquals("<blockquote><p>q</p></blockquote>", block.html)
    }

    @Test
    fun details() {
        val block = RichBlockDetails(RichTextPlain("sum"), listOf(RichBlockParagraph(RichTextPlain("body"))), isOpen = true)
        assertEquals("<details open><summary>sum</summary>\n\nbody\n\n</details>", block.markdown)
        assertEquals("<details open><summary>sum</summary><p>body</p></details>", block.html)
    }

    @Test
    fun table() {
        val block = RichBlockTable(
            listOf(
                listOf(
                    RichBlockTableCell(text = RichTextPlain("H1"), isHeader = true, align = "left", valign = "top"),
                    RichBlockTableCell(text = RichTextPlain("H2"), isHeader = true, align = "center", valign = "top")
                ),
                listOf(
                    RichBlockTableCell(text = RichTextPlain("a"), align = "left", valign = "top"),
                    RichBlockTableCell(text = RichTextPlain("b"), align = "center", valign = "top")
                )
            )
        )
        assertEquals("| H1 | H2 |\n| :--- | :--: |\n| a | b |", block.markdown)
        assertEquals(
            "<table><tr><th align=\"left\" valign=\"top\">H1</th><th align=\"center\" valign=\"top\">H2</th></tr>" +
                "<tr><td align=\"left\" valign=\"top\">a</td><td align=\"center\" valign=\"top\">b</td></tr></table>",
            block.html
        )
    }

    @Test
    fun listOfBlocksJoinsBlocks() {
        val blocks = listOf(RichBlockParagraph(RichTextPlain("a")), RichBlockDivider())
        assertEquals("a\n\n---", blocks.toRichMarkdown())
        assertEquals("<p>a</p>\n<hr/>", blocks.toRichHtml())
    }

    @Test
    fun richTextInfoDelegatesToBlocks() {
        val info = RichTextInfo(listOf(RichBlockParagraph(RichTextPlain("p")), RichBlockDivider()))
        assertEquals("p\n\n---", info.markdown)
        assertEquals("<p>p</p>\n<hr/>", info.html)
    }
}
