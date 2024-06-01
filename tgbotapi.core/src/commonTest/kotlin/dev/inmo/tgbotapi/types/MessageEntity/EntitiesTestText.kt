package dev.inmo.tgbotapi.types.MessageEntity

import dev.inmo.tgbotapi.types.message.RawMessageEntity
import dev.inmo.tgbotapi.types.message.asTextSources
import dev.inmo.tgbotapi.types.message.textsources.*
import dev.inmo.tgbotapi.types.message.toRawMessageEntities
import dev.inmo.tgbotapi.utils.extensions.makeSourceString
import kotlin.test.assertEquals
import kotlin.test.assertTrue

const val testText = "It (is?) is simple hello world with #tag and @mention. Start of blockquote: Block quotation started\n" +
        "Block quotation continued\n" +
        "The last line of the block quotation\n" +
        ". Start of expandable blockquote: Block quotation started\n" +
        "Block quotation continued\n" +
        "The last line of the block quotation"
const val formattedV2Text = "It \\(is?\\) *_is_ ~__simple__~* ||hello world|| with \\#tag and @mention\\. Start of blockquote: >Block quotation started\n>Block quotation continued\n>The last line of the block quotation\n\\. Start of expandable blockquote: **>Block quotation started\n>Block quotation continued\n>The last line of the block quotation||"
const val formattedHtmlText = "It (is?) <b><i>is</i> <s><u>simple</u></s></b> <span class=\"tg-spoiler\">hello world</span> with #tag and @mention. Start of blockquote: <blockquote>Block quotation started\nBlock quotation continued\nThe last line of the block quotation</blockquote>\n. Start of expandable blockquote: <blockquote expandable>Block quotation started\nBlock quotation continued\nThe last line of the block quotation</blockquote>"
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
    ),
    RawMessageEntity(
        "blockquote",
        76,
        86
    ),
    RawMessageEntity(
        "expandable_blockquote",
        197,
        86
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
    assertTrue (get(8) is RegularTextSource)
    assertTrue (get(9) is BlockquoteTextSource)
    assertTrue (get(10) is RegularTextSource)
    assertTrue (get(11) is ExpandableBlockquoteTextSource)

    val boldSource = get(1) as BoldTextSource
    assertTrue (boldSource.subsources.first() is ItalicTextSource)
    assertTrue (boldSource.subsources[1] is RegularTextSource)
    assertTrue (boldSource.subsources[2] is StrikethroughTextSource)
    assertTrue ((boldSource.subsources[2] as StrikethroughTextSource).subsources.first() is UnderlineTextSource)

    val blockquoteSource = get(9) as BlockquoteTextSource
    assertTrue (blockquoteSource.subsources.first() is RegularTextSource)

    val expandableBlockquoteSource = get(11) as ExpandableBlockquoteTextSource
    assertTrue (expandableBlockquoteSource.subsources.first() is RegularTextSource)

    assertEquals(this, toRawMessageEntities().asTextSources(makeSourceString()))
}
