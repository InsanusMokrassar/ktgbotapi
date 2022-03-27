package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.types.MessageEntity.textsources.*
import kotlin.test.assertTrue

const val testText = "It (is?) is simple hello world with #tag and @mention"
const val formattedV2Text = "It \\(is?\\) *_is_ ~__simple__~* ||hello world|| with \\#tag and @mention"
const val formattedHtmlText = "It (is?) <b><i>is</i> <s><u>simple</u></s></b> <span class=\"tg-spoiler\">hello world</span> with #tag and @mention"
internal val testTextEntities = listOf(
    RawMessageEntity(
        "bold",
        9,
        9
    ),
    RawMessageEntity(
        "italic",
        9,
        2
    ),
    RawMessageEntity(
        "strikethrough",
        12,
        6
    ),
    RawMessageEntity(
        "underline",
        12,
        6
    ),
    RawMessageEntity(
        "spoiler",
        19,
        11
    ),
    RawMessageEntity(
        "hashtag",
        36,
        4
    ),
    RawMessageEntity(
        "mention",
        45,
        8
    )
)

fun TextSourcesList.testTextSources() {
    assertTrue (first() is RegularTextSource)
    assertTrue (get(1) is BoldTextSource)
    assertTrue (get(2) is RegularTextSource)
    assertTrue (get(3) is SpoilerTextSource)
    assertTrue (get(4) is RegularTextSource)
    assertTrue (get(5) is HashTagTextSource)
    assertTrue (get(6) is RegularTextSource)
    assertTrue (get(7) is MentionTextSource)

    val boldSource = get(1) as BoldTextSource
    assertTrue (boldSource.subsources.first() is ItalicTextSource)
    assertTrue (boldSource.subsources[1] is RegularTextSource)
    assertTrue (boldSource.subsources[2] is StrikethroughTextSource)
    assertTrue ((boldSource.subsources[2] as StrikethroughTextSource).subsources.first() is UnderlineTextSource)
}
