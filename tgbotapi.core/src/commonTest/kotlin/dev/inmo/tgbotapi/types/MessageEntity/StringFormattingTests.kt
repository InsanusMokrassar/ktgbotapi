package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.extensions.utils.formatting.*
import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.*
import kotlin.test.*

class StringFormattingTests {
    @Test
    fun testThatPreEscapingWorksCorrectly() {
        val originalHelloWorld =
            """
                fun main() {
                    println("Hello world")
                }
            """.replace("                ", "")
        val helloWorldLanguage = "kotlin"
        assertEquals(
            "<pre><code class=\"language-$helloWorldLanguage\">$originalHelloWorld</code></pre>",
            originalHelloWorld.preHTML(
                helloWorldLanguage,
            ),
        )
        assertEquals(
            "<pre>$originalHelloWorld</pre>",
            originalHelloWorld.preHTML(),
        )
        assertEquals(
            "```$helloWorldLanguage\n$originalHelloWorld\n```",
            originalHelloWorld.preMarkdown(
                helloWorldLanguage,
            ),
        )
        assertEquals(
            "```\n$originalHelloWorld\n```",
            originalHelloWorld.preMarkdown(),
        )
    }

    @Test
    fun testThatCreatingOfStringWithSimpleDSLWorksCorrectly() {
        val sources: TextSourcesList =
            regularTextSource("It (is?) ") +
                boldTextSource(
                    italicTextSource("is") +
                        " " +
                        strikethroughTextSource(underlineTextSource("simple")),
                ) +
                " " +
                spoilerTextSource("hello world") +
                " with " +
                hashtagTextSource("tag@sample") +
                " and " +
                mentionTextSource("mention") +
                ". Start of blockquote: " +
                blockquoteTextSource(
                    "Block quotation started\n" +
                        "Block quotation continued\n" +
                        "The last line of the block quotation",
                ) +
                "\n. Start of expandable blockquote: " +
                expandableBlockquoteTextSource(
                    "Block quotation started\n" +
                        "Block quotation continued\n" +
                        "The last line of the block quotation",
                )
        sources.testTextSources()

        assertEquals(formattedV2Text, sources.toMarkdownV2Texts().first())
        assertEquals(formattedHtmlText, sources.toHtmlTexts().first())
    }

    @Test
    fun testForRepeatingWordsInOneSentenceWithTheSecondOneFormatted() {
        val sourceText = "link link"
        val messageEntities =
            listOf(
                RawMessageEntity("bold", 5, 4),
                RawMessageEntity("text_link", 6, 2, "google.com"),
            )
        val textSources = messageEntities.asTextSources(sourceText)
        val (regular, bold) = textSources
        assertTrue(regular is RegularTextSource)
        assertTrue(bold is BoldTextSource)
        assertTrue(regular.source == "link ")
        assertTrue(bold.source == "link")
        assertTrue((bold.subsources[0] as? RegularTextSource) ?.source == "l")
        assertTrue((bold.subsources[1] as? TextLinkTextSource) ?.source == "in")
        assertTrue((bold.subsources[1] as? TextLinkTextSource) ?.url == "google.com")
        assertTrue((bold.subsources[2] as? RegularTextSource) ?.source == "k")
        assertTrue(bold.subsources.size == 3)
        assertTrue(textSources.size == 2)
    }

    @Test
    fun testForRepeatingWordsInOneSentenceWithTheSecondOneFormattedInsideOfFormatting() {
        val sourceText = "text"
        val messageEntities =
            listOf(
                RawMessageEntity("bold", 0, 4),
                RawMessageEntity("text_link", 3, 1, "google.com"),
            )
        val textSources = messageEntities.asTextSources(sourceText)
        val (bold) = textSources
        assertTrue(bold is BoldTextSource)
        assertTrue(bold.source == "text")
        assertTrue((bold.subsources[0] as? RegularTextSource) ?.source == "tex")
        assertTrue((bold.subsources[1] as? TextLinkTextSource) ?.source == "t")
        assertTrue((bold.subsources[1] as? TextLinkTextSource) ?.url == "google.com")
        assertTrue(bold.subsources.size == 2)
        assertTrue(textSources.size == 1)
    }
}
