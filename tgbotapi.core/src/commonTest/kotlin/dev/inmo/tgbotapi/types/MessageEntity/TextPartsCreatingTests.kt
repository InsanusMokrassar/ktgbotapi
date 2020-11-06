package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.justTextSources
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

        val textParts = entities.asTextParts(text)

        assertTrue (textParts.first().source is RegularTextSource)
        assertTrue (textParts[1].source is BoldTextSource)
        assertTrue (textParts[2].source is RegularTextSource)

        val boldSource = textParts[1].source as BoldTextSource
        assertTrue (boldSource.textSources.first() is ItalicTextSource)
        assertTrue (boldSource.textSources[1] is RegularTextSource)
        assertTrue (boldSource.textSources[2] is StrikethroughTextSource)
        assertTrue ((boldSource.textSources[2] as StrikethroughTextSource).textSources.first() is UnderlineTextSource)

        assertEquals(
            formattedV2Text,
            textParts.justTextSources().toMarkdownV2Texts().first()
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

        val textParts = entities.asTextParts(text)

        assertTrue (textParts.first().source is RegularTextSource)
        assertTrue (textParts[1].source is BoldTextSource)
        assertTrue (textParts[2].source is RegularTextSource)

        val boldSource = textParts[1].source as BoldTextSource
        assertTrue (boldSource.textSources.first() is ItalicTextSource)
        assertTrue (boldSource.textSources[1] is RegularTextSource)
        assertTrue (boldSource.textSources[2] is StrikethroughTextSource)
        assertTrue ((boldSource.textSources[2] as StrikethroughTextSource).textSources.first() is UnderlineTextSource)

        assertEquals(
            formattedHtmlText,
            textParts.justTextSources().toHtmlTexts().first()
        )
    }
}
