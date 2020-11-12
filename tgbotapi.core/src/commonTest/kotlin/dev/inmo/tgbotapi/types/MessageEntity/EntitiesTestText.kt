package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.CommonAbstracts.TextPart
import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import kotlin.test.assertTrue

const val testText = "It is simple hello world with #tag and @mention"
const val formattedV2Text = "It *_is_ ~__simple__~* hello world with \\#tag and @mention"
const val formattedHtmlText = "It <b><i>is</i> <s><u>simple</u></s></b> hello world with #tag and @mention"
internal val testTextEntities = listOf(
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
    ),
    RawMessageEntity(
        "hashtag",
        30,
        4
    ),
    RawMessageEntity(
        "mention",
        39,
        6
    )
)

fun List<TextPart>.testTextParts() {
    assertTrue (first().source is RegularTextSource)
    assertTrue (get(1).source is BoldTextSource)
    assertTrue (get(2).source is RegularTextSource)
    assertTrue (get(3).source is HashTagTextSource)
    assertTrue (get(4).source is RegularTextSource)
    assertTrue (get(5).source is MentionTextSource)

    val boldSource = get(1).source as BoldTextSource
    assertTrue (boldSource.subsources.first() is ItalicTextSource)
    assertTrue (boldSource.subsources[1] is RegularTextSource)
    assertTrue (boldSource.subsources[2] is StrikethroughTextSource)
    assertTrue ((boldSource.subsources[2] as StrikethroughTextSource).subsources.first() is UnderlineTextSource)
}
