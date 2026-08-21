package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.TelegramMediaVoiceNote

/**
 * Builder of [InputRichBlockListItem.Ordered] items used inside [InputRichBlocksBuilder.orderedList].
 */
@RichTextDsl
class InputRichBlockOrderedListBuilder {
    private val items = mutableListOf<InputRichBlockListItem.Ordered>()

    fun item(
        value: Int,
        labelType: LabelType = LabelType.Decimals,
        hasCheckbox: Boolean? = null,
        isChecked: Boolean? = null,
        block: InputRichBlocksBuilder.() -> Unit
    ) {
        items.add(InputRichBlockListItem.Ordered(buildInputRichBlocks(block), value, labelType, hasCheckbox, isChecked))
    }

    fun build(): List<InputRichBlockListItem.Ordered> = items.toList()
}

/**
 * Builder of [InputRichBlockListItem.Unordered] items used inside [InputRichBlocksBuilder.unorderedList].
 */
@RichTextDsl
class InputRichBlockUnorderedListBuilder {
    private val items = mutableListOf<InputRichBlockListItem.Unordered>()

    fun item(
        hasCheckbox: Boolean? = null,
        isChecked: Boolean? = null,
        block: InputRichBlocksBuilder.() -> Unit
    ) {
        items.add(InputRichBlockListItem.Unordered(buildInputRichBlocks(block), hasCheckbox, isChecked))
    }

    fun item(text: String) {
        items.add(InputRichBlockListItem.Unordered(listOf(InputRichBlockParagraph(RichTextPlain(text)))))
    }

    fun build(): List<InputRichBlockListItem.Unordered> = items.toList()
}

/** Builder of [RichBlockTableCell]s used inside [InputRichBlockTableBuilder.row]. */
@RichTextDsl
class InputRichBlockTableRowBuilder {
    private val cells = mutableListOf<RichBlockTableCell>()

    fun cell(
        align: RichBlockTableCellAlign,
        valign: RichBlockTableCellVAlign,
        colspan: Int? = null,
        rowspan: Int? = null,
        block: RichTextBuilder.() -> Unit
    ) {
        cells.add(RichBlockTableCell.Regular(buildRichText(block), colspan, rowspan, align, valign))
    }

    fun headerCell(
        align: RichBlockTableCellAlign,
        valign: RichBlockTableCellVAlign,
        colspan: Int? = null,
        rowspan: Int? = null,
        block: RichTextBuilder.() -> Unit
    ) {
        cells.add(RichBlockTableCell.Header(buildRichText(block), colspan, rowspan, align, valign))
    }

    fun build(): List<RichBlockTableCell> = cells.toList()
}

/** Builder of table rows used inside [InputRichBlocksBuilder.table]. */
@RichTextDsl
class InputRichBlockTableBuilder {
    private val rows = mutableListOf<List<RichBlockTableCell>>()

    fun row(block: InputRichBlockTableRowBuilder.() -> Unit) {
        rows.add(InputRichBlockTableRowBuilder().apply(block).build())
    }

    fun build(): List<List<RichBlockTableCell>> = rows.toList()
}

/**
 * Builder of a [List] of [InputRichBlock]s - the root of the rich message input DSL. Text-bearing and container blocks
 * have their own DSL functions; media blocks (photo, video, animation, audio, voice note, collage, slideshow, table,
 * map) can be appended with the matching convenience function or with [add] / unary plus.
 */
@RichTextDsl
class InputRichBlocksBuilder {
    private val blocks = mutableListOf<InputRichBlock>()

    /** Appends an already built [InputRichBlock]. */
    fun add(block: InputRichBlock) {
        blocks.add(block)
    }

    /** Appends an already built [InputRichBlock]. */
    operator fun InputRichBlock.unaryPlus() = add(this)

    fun paragraph(text: String) = add(InputRichBlockParagraph(RichTextPlain(text)))
    fun paragraph(block: RichTextBuilder.() -> Unit) = add(InputRichBlockParagraph(buildRichText(block)))

    fun heading(text: String, level: Int) = add(InputRichBlockSectionHeading(RichTextPlain(text), level))
    fun heading(level: Int, block: RichTextBuilder.() -> Unit) = add(InputRichBlockSectionHeading(buildRichText(block), level))

    fun h1(text: String) = heading(text, 1)
    fun h1(block: RichTextBuilder.() -> Unit) = heading(1, block)
    fun h2(text: String) = heading(text, 2)
    fun h2(block: RichTextBuilder.() -> Unit) = heading(2, block)
    fun h3(text: String) = heading(text, 3)
    fun h3(block: RichTextBuilder.() -> Unit) = heading(3, block)
    fun h4(text: String) = heading(text, 4)
    fun h4(block: RichTextBuilder.() -> Unit) = heading(4, block)
    fun h5(text: String) = heading(text, 5)
    fun h5(block: RichTextBuilder.() -> Unit) = heading(5, block)
    fun h6(text: String) = heading(text, 6)
    fun h6(block: RichTextBuilder.() -> Unit) = heading(6, block)

    fun preformatted(text: String, language: String? = null) = add(InputRichBlockPreformatted(RichTextPlain(text), language))

    fun footer(text: String) = add(InputRichBlockFooter(RichTextPlain(text)))
    fun footer(block: RichTextBuilder.() -> Unit) = add(InputRichBlockFooter(buildRichText(block)))

    fun divider() = add(InputRichBlockDivider())

    fun mathematicalExpression(expression: String) = add(InputRichBlockMathematicalExpression(expression))

    fun anchor(name: String) = add(InputRichBlockAnchor(name))

    fun thinking(text: String) = add(InputRichBlockThinking(RichTextPlain(text)))
    fun thinking(block: RichTextBuilder.() -> Unit) = add(InputRichBlockThinking(buildRichText(block)))

    fun orderedList(block: InputRichBlockOrderedListBuilder.() -> Unit) =
        add(InputRichBlockList(InputRichBlockOrderedListBuilder().apply(block).build()))

    fun unorderedList(block: InputRichBlockUnorderedListBuilder.() -> Unit) =
        add(InputRichBlockList(InputRichBlockUnorderedListBuilder().apply(block).build()))

    fun blockQuotation(credit: RichText? = null, block: InputRichBlocksBuilder.() -> Unit) =
        add(InputRichBlockBlockQuotation(buildInputRichBlocks(block), credit))

    fun pullQuotation(credit: RichText? = null, block: RichTextBuilder.() -> Unit) =
        add(InputRichBlockPullQuotation(buildRichText(block), credit))

    fun details(summary: RichText, isOpen: Boolean? = null, block: InputRichBlocksBuilder.() -> Unit) =
        add(InputRichBlockDetails(summary, buildInputRichBlocks(block), isOpen))

    fun details(summary: String, isOpen: Boolean? = null, block: InputRichBlocksBuilder.() -> Unit) =
        details(RichTextPlain(summary), isOpen, block)

    fun photo(photo: TelegramMediaPhoto, caption: RichBlockCaption? = null) = add(InputRichBlockPhoto(photo, caption))

    fun video(video: TelegramMediaVideo, caption: RichBlockCaption? = null) = add(InputRichBlockVideo(video, caption))

    fun animation(animation: TelegramMediaAnimation, caption: RichBlockCaption? = null) =
        add(InputRichBlockAnimation(animation, caption))

    fun audio(audio: TelegramMediaAudio, caption: RichBlockCaption? = null) = add(InputRichBlockAudio(audio, caption))

    fun voiceNote(voiceNote: TelegramMediaVoiceNote, caption: RichBlockCaption? = null) =
        add(InputRichBlockVoiceNote(voiceNote, caption))

    fun collage(caption: RichBlockCaption? = null, block: InputRichBlocksBuilder.() -> Unit) =
        add(InputRichBlockCollage(buildInputRichBlocks(block), caption))

    fun slideshow(caption: RichBlockCaption? = null, block: InputRichBlocksBuilder.() -> Unit) =
        add(InputRichBlockSlideshow(buildInputRichBlocks(block), caption))

    fun table(
        isBordered: Boolean? = null,
        isStriped: Boolean? = null,
        caption: RichText? = null,
        block: InputRichBlockTableBuilder.() -> Unit
    ) = add(InputRichBlockTable(InputRichBlockTableBuilder().apply(block).build(), isBordered, isStriped, caption))

    fun map(
        location: StaticLocation,
        zoom: Int,
        width: Int,
        height: Int,
        caption: RichBlockCaption? = null
    ) = add(InputRichBlockMap(location, zoom, width, height, caption))

    fun build(): List<InputRichBlock> = blocks.toList()
}

/** Builds a [List] of [InputRichBlock]s using the [InputRichBlocksBuilder] DSL. */
fun buildInputRichBlocks(block: InputRichBlocksBuilder.() -> Unit): List<InputRichBlock> = InputRichBlocksBuilder().apply(block).build()

/** Builds an [InputRichMessage] with `blocks` built using the [InputRichBlocksBuilder] DSL. */
fun InputRichMessageBlocks(
    isRtl: Boolean? = null,
    skipEntityDetection: Boolean? = null,
    block: InputRichBlocksBuilder.() -> Unit
): InputRichMessage = InputRichMessageBlocks(buildInputRichBlocks(block), isRtl, skipEntityDetection)
