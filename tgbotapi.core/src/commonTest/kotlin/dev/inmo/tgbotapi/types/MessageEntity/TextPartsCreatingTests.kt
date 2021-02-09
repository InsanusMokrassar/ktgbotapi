package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.justTextSources
import dev.inmo.tgbotapi.extensions.utils.formatting.toHtmlTexts
import dev.inmo.tgbotapi.extensions.utils.formatting.toMarkdownV2Texts
import kotlin.test.Test
import kotlin.test.assertEquals

class TextPartsCreatingTests {
    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreating() {
        val textParts = testTextEntities.asTextParts(testText)
        textParts.testTextParts()

        assertEquals(
            formattedV2Text,
            textParts.justTextSources().toMarkdownV2Texts().first()
        )
    }

    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreatingInHtml() {
        val textParts = testTextEntities.asTextParts(testText)
        textParts.testTextParts()

        assertEquals(
            formattedHtmlText,
            textParts.justTextSources().toHtmlTexts().first()
        )
    }
}
