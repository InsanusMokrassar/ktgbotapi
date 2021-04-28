package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.extensions.utils.formatting.toHtmlTexts
import dev.inmo.tgbotapi.extensions.utils.formatting.toMarkdownV2Texts
import kotlin.test.Test
import kotlin.test.assertEquals

class TextPartsCreatingTests {
    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreating() {
        val textSources = testTextEntities.asTextSources(testText)
        textSources.testTextSources()

        assertEquals(
            formattedV2Text,
            textSources.toMarkdownV2Texts().first()
        )
    }

    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreatingInHtml() {
        val textSources = testTextEntities.asTextSources(testText)
        textSources.testTextSources()

        assertEquals(
            formattedHtmlText,
            textSources.toHtmlTexts().first()
        )
    }
}
