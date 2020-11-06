package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.justTextSources
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.utils.*
import kotlin.test.*

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
