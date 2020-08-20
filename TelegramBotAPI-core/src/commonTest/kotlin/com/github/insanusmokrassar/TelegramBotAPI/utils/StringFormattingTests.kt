package com.github.insanusmokrassar.TelegramBotAPI.utils

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
}
