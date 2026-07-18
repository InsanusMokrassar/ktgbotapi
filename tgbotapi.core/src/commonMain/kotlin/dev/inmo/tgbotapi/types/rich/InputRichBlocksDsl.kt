package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.TelegramMediaVoiceNote

/**
 * Builder of [InputRichBlockListItem]s used inside [InputRichBlocksBuilder.list].
 */
@RichTextDsl
class InputRichBlockListBuilder {
    private val items = mutableListOf<InputRichBlockListItem>()

    fun item(
        hasCheckbox: Boolean? = null,
        isChecked: Boolean? = null,
        value: Int? = null,
        labelType: String? = null,
        block: InputRichBlocksBuilder.() -> Unit
    ) {
        items.add(InputRichBlockListItem(buildInputRichBlocks(block), hasCheckbox, isChecked, value, labelType))
    }

    fun item(text: String) {
        items.add(InputRichBlockListItem(listOf(InputRichBlockParagraph(RichTextPlain(text)))))
    }

    fun build(): List<InputRichBlockListItem> = items.toList()
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

    fun preformatted(text: String, language: String? = null) = add(InputRichBlockPreformatted(RichTextPlain(text), language))

    fun footer(text: String) = add(InputRichBlockFooter(RichTextPlain(text)))
    fun footer(block: RichTextBuilder.() -> Unit) = add(InputRichBlockFooter(buildRichText(block)))

    fun divider() = add(InputRichBlockDivider())

    fun mathematicalExpression(expression: String) = add(InputRichBlockMathematicalExpression(expression))

    fun anchor(name: String) = add(InputRichBlockAnchor(name))

    fun thinking(text: String) = add(InputRichBlockThinking(RichTextPlain(text)))
    fun thinking(block: RichTextBuilder.() -> Unit) = add(InputRichBlockThinking(buildRichText(block)))

    fun list(block: InputRichBlockListBuilder.() -> Unit) = add(InputRichBlockList(InputRichBlockListBuilder().apply(block).build()))

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
        cells: List<List<RichBlockTableCell>>,
        isBordered: Boolean? = null,
        isStriped: Boolean? = null,
        caption: RichText? = null
    ) = add(InputRichBlockTable(cells, isBordered, isStriped, caption))

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
