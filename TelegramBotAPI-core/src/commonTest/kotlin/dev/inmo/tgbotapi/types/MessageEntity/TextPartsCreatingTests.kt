package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import dev.inmo.tgbotapi.utils.*
import kotlin.test.*

class TextPartsCreatingTests {
    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreating() {
        val text = "It is simple hello world"
        val formattedV2Text = "It *_is_ ~__simple__~* hello world"
        val entities = listOf(
            RawMessageEntity(
                "bold",
                3,
                9
            ),
            RawMessageEntity(
                "italic",
                3,
                2
            ),
            RawMessageEntity(
                "strikethrough",
                6,
                6
            ),
            RawMessageEntity(
                "underline",
                6,
                6
            )
        )

        val textParts = createTextPart(text, entities)


        assertTrue (
            textParts.first().source is BoldTextSource
        )

        val boldSource = textParts.first().source as BoldTextSource
        assertTrue (
            boldSource.textParts.first().source is ItalicTextSource
        )
        assertTrue (
            boldSource.textParts[1].source is RegularTextSource
        )
        assertTrue (
            boldSource.textParts[2].source is StrikethroughTextSource
        )
        assertTrue (
            (boldSource.textParts[2].source as StrikethroughTextSource).textParts.first().source is UnderlineTextSource
        )


        val fullTextParts = text.fullListOfSubSource(textParts)

        assertTrue(
            fullTextParts.first().source is RegularTextSource
        )
        assertTrue(
            fullTextParts[1].source is BoldTextSource
        )
        assertTrue(
            fullTextParts[2].source is RegularTextSource
        )

        assertEquals(
            formattedV2Text,
            createMarkdownV2Text(fullTextParts.map { it.source }).first()
        )
    }

    @Test
    fun testThatTextWithMultilevelPartsCorrectlyCreatingInHtml() {
        val text = "It is simple hello world"
        val formattedHtmlText = "It <b><i>is</i> <s><u>simple</u></s></b> hello world"
        val entities = listOf(
            RawMessageEntity(
                "bold",
                3,
                9
            ),
            RawMessageEntity(
                "italic",
                3,
                2
            ),
            RawMessageEntity(
                "strikethrough",
                6,
                6
            ),
            RawMessageEntity(
                "underline",
                6,
                6
            )
        )

        val textParts = createTextPart(text, entities)


        assertTrue (
            textParts.first().source is BoldTextSource
        )

        val boldSource = textParts.first().source as BoldTextSource
        assertTrue (
            boldSource.textParts.first().source is ItalicTextSource
        )
        assertTrue (
            boldSource.textParts[1].source is RegularTextSource
        )
        assertTrue (
            boldSource.textParts[2].source is StrikethroughTextSource
        )
        assertTrue (
            (boldSource.textParts[2].source as StrikethroughTextSource).textParts.first().source is UnderlineTextSource
        )


        val fullTextParts = text.fullListOfSubSource(textParts)

        assertTrue(
            fullTextParts.first().source is RegularTextSource
        )
        assertTrue(
            fullTextParts[1].source is BoldTextSource
        )
        assertTrue(
            fullTextParts[2].source is RegularTextSource
        )

        assertEquals(
            formattedHtmlText,
            createHtmlText(fullTextParts.map { it.source }).first()
        )
    }
}
