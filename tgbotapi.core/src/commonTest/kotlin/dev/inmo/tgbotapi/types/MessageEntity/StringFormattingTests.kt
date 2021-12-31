package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.extensions.utils.formatting.*
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import kotlin.test.Test
import kotlin.test.assertEquals

class StringFormattingTests {
    @Test
    fun testThatPreEscapingWorksCorrectly() {
        val originalHelloWorld = """
                fun main() {
                    println("Hello world")
                }
            """.replace("                ", "")
        val helloWorldLanguage = "kotlin"
        assertEquals(
            "<pre><code class=\"language-$helloWorldLanguage\">$originalHelloWorld</code></pre>",
            originalHelloWorld.preHTML(
                helloWorldLanguage
            )
        )
        assertEquals(
            "<pre>$originalHelloWorld</pre>",
            originalHelloWorld.preHTML()
        )
        assertEquals(
            "```$helloWorldLanguage\n$originalHelloWorld\n```",
            originalHelloWorld.preMarkdown(
                helloWorldLanguage
            )
        )
        assertEquals(
            "```\n$originalHelloWorld\n```",
            originalHelloWorld.preMarkdown()
        )
    }

    @Test
    fun testThatCreatingOfStringWithSimpleDSLWorksCorrectly() {
        val sources: TextSourcesList = regular("It ") +
            bold(italic("is") +
                " " +
                strikethrough(underline("simple"))) +
                " " +
                spoiler("hello world") +
                " with " +
                hashtag("tag") +
                " and " +
                mention("mention")
        sources.testTextSources()

        assertEquals(formattedV2Text, sources.toMarkdownV2Texts().first())
        assertEquals(formattedHtmlText, sources.toHtmlTexts().first())
    }
}
