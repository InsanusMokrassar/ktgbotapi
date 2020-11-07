package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.TextSource
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.CommonAbstracts.plus
import dev.inmo.tgbotapi.utils.internal.*
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
        val sources: List<TextSource> = regular("It ") +
            bold(italic("is") +
                " " +
                strikethrough(underline("simple"))) +
                " hello world with " +
                hashtag("tag") +
                " and " +
                mention("mention")
        sources.toTextParts().testTextParts()

        assertEquals(formattedV2Text, sources.toMarkdownV2Texts().first())
        assertEquals(formattedHtmlText, sources.toHtmlTexts().first())
    }
}
