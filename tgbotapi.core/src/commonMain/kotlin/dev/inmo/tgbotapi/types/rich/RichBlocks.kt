package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.animationField
import dev.inmo.tgbotapi.types.audioField
import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.captionField
import dev.inmo.tgbotapi.types.cellsField
import dev.inmo.tgbotapi.types.creditField
import dev.inmo.tgbotapi.types.expressionField
import dev.inmo.tgbotapi.types.files.AnimationFile
import dev.inmo.tgbotapi.types.files.AudioFile
import dev.inmo.tgbotapi.types.files.PhotoFile
import dev.inmo.tgbotapi.types.files.VideoFile
import dev.inmo.tgbotapi.types.files.VoiceFile
import dev.inmo.tgbotapi.types.hasSpoilerField
import dev.inmo.tgbotapi.types.heightField
import dev.inmo.tgbotapi.types.isBorderedField
import dev.inmo.tgbotapi.types.isOpenField
import dev.inmo.tgbotapi.types.isStripedField
import dev.inmo.tgbotapi.types.itemsField
import dev.inmo.tgbotapi.types.languageField
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.locationField
import dev.inmo.tgbotapi.types.nameField
import dev.inmo.tgbotapi.types.photoField
import dev.inmo.tgbotapi.types.sizeField
import dev.inmo.tgbotapi.types.summaryField
import dev.inmo.tgbotapi.types.textField
import dev.inmo.tgbotapi.types.typeField
import dev.inmo.tgbotapi.types.videoField
import dev.inmo.tgbotapi.types.voiceNoteField
import dev.inmo.tgbotapi.types.widthField
import dev.inmo.tgbotapi.types.zoomField
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * A text paragraph.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockparagraph">RichBlockParagraph</a>
 */
@Serializable
data class RichBlockParagraph(
    @SerialName(textField)
    val text: RichText
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text)
    override val html: String = html(text)

    companion object {
        const val TYPE = "paragraph"
        fun markdown(text: RichText): String = text.markdown
        fun html(text: RichText): String = "<p>${text.html}</p>"
    }
}

/**
 * A section heading.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblocksectionheading">RichBlockSectionHeading</a>
 */
@Serializable
data class RichBlockSectionHeading(
    @SerialName(textField)
    val text: RichText,
    /**
     * Relative size of the text font; 1-6, 1 is the largest, 6 is the smallest.
     */
    @SerialName(sizeField)
    val size: Int
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text, size)
    override val html: String = html(text, size)

    companion object {
        const val TYPE = "heading"
        fun markdown(text: RichText, size: Int): String = "#".repeat(size) + " " + text.markdown
        fun html(text: RichText, size: Int): String = "<h$size>${text.html}</h$size>"
    }
}

/**
 * A preformatted text block.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockpreformatted">RichBlockPreformatted</a>
 */
@Serializable
data class RichBlockPreformatted(
    @SerialName(textField)
    val text: RichText,
    @SerialName(languageField)
    val language: String? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text, language)
    override val html: String = html(text, language)

    companion object {
        const val TYPE = "pre"
        fun markdown(text: RichText, language: String?): String = "```" + (language ?: "") + "\n" + text.rawText + "\n```"
        fun html(text: RichText, language: String?): String =
            language?.let { "<pre><code class=\"language-$it\">${text.html}</code></pre>" } ?: "<pre>${text.html}</pre>"
    }
}

/**
 * A footer.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockfooter">RichBlockFooter</a>
 */
@Serializable
data class RichBlockFooter(
    @SerialName(textField)
    val text: RichText
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text)
    override val html: String = html(text)

    companion object {
        const val TYPE = "footer"
        fun markdown(text: RichText): String = "<footer>${text.markdown}</footer>"
        fun html(text: RichText): String = "<footer>${text.html}</footer>"
    }
}

/**
 * A divider.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockdivider">RichBlockDivider</a>
 */
@Serializable
class RichBlockDivider : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown()
    override val html: String = html()

    override fun equals(other: Any?): Boolean = other is RichBlockDivider

    override fun hashCode(): Int = TYPE.hashCode()

    override fun toString(): String = "RichBlockDivider"

    companion object {
        const val TYPE = "divider"
        fun markdown(): String = "---"
        fun html(): String = "<hr/>"
    }
}

/**
 * A block with a mathematical expression in LaTeX format.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockmathematicalexpression">RichBlockMathematicalExpression</a>
 */
@Serializable
data class RichBlockMathematicalExpression(
    @SerialName(expressionField)
    val expression: String
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(expression)
    override val html: String = html(expression)

    companion object {
        const val TYPE = "mathematical_expression"
        fun markdown(expression: String): String = "\$\$" + expression + "\$\$"
        fun html(expression: String): String = "<tg-math-block>$expression</tg-math-block>"
    }
}

/**
 * A block with an anchor.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockanchor">RichBlockAnchor</a>
 */
@Serializable
data class RichBlockAnchor(
    @SerialName(nameField)
    val name: String
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(name)
    override val html: String = html(name)

    companion object {
        const val TYPE = "anchor"
        fun markdown(name: String): String = "<a name=\"$name\"></a>"
        fun html(name: String): String = "<a name=\"$name\"></a>"
    }
}

/**
 * A list of blocks.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblocklist">RichBlockList</a>
 */
@Serializable
data class RichBlockList(
    @SerialName(itemsField)
    val items: List<RichBlockListItem>
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(items)
    override val html: String = html(items)

    companion object {
        const val TYPE = "list"
        fun markdown(items: List<RichBlockListItem>): String =
            items.mapIndexed { index, item ->
                val marker = when {
                    item.hasCheckbox == true -> if (item.isChecked == true) "- [x] " else "- [ ] "
                    item.labelType != null -> "${item.value ?: (index + 1)}. "
                    else -> "- "
                }
                item.blocks.toRichMarkdown().lineSequence().mapIndexed { lineIndex, line ->
                    if (lineIndex == 0) "$marker$line" else "  $line"
                }.joinToString(separator = "\n")
            }.joinToString(separator = "\n")

        fun html(items: List<RichBlockListItem>): String {
            val ordered = items.any { it.labelType != null }
            val tag = if (ordered) "ol" else "ul"
            val renderedItems = items.joinToString(separator = "") { item ->
                val attributes = buildString {
                    item.value?.let { append(" value=\"$it\"") }
                    item.labelType?.let { append(" type=\"$it\"") }
                }
                val checkbox = if (item.hasCheckbox == true) {
                    "<input type=\"checkbox\"${if (item.isChecked == true) " checked" else ""}>"
                } else {
                    ""
                }
                "<li$attributes>$checkbox${item.blocks.toRichHtml()}</li>"
            }
            return "<$tag>$renderedItems</$tag>"
        }
    }
}

/**
 * A block quotation.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockblockquotation">RichBlockBlockQuotation</a>
 */
@Serializable
data class RichBlockBlockQuotation(
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
    @SerialName(creditField)
    val credit: RichText? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(blocks, credit)
    override val html: String = html(blocks, credit)

    companion object {
        const val TYPE = "blockquote"
        fun markdown(blocks: List<RichBlock>, credit: RichText?): String {
            val quoted = blocks.toRichMarkdown().lineSequence().joinToString(separator = "\n") { line ->
                if (line.isEmpty()) ">" else "> $line"
            }
            return quoted + (credit?.let { "\n> ${creditCiteMarkdown(it)}" } ?: "")
        }

        fun html(blocks: List<RichBlock>, credit: RichText?): String =
            "<blockquote>${blocks.toRichHtml()}${creditCiteHtml(credit)}</blockquote>"
    }
}

/**
 * A quotation with centered text.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockpullquotation">RichBlockPullQuotation</a>
 */
@Serializable
data class RichBlockPullQuotation(
    @SerialName(textField)
    val text: RichText,
    @SerialName(creditField)
    val credit: RichText? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text, credit)
    override val html: String = html(text, credit)

    companion object {
        const val TYPE = "pullquote"
        fun markdown(text: RichText, credit: RichText?): String = "<aside>${text.markdown}${creditCiteMarkdown(credit)}</aside>"
        fun html(text: RichText, credit: RichText?): String = "<aside>${text.html}${creditCiteHtml(credit)}</aside>"
    }
}

/**
 * A collage.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockcollage">RichBlockCollage</a>
 */
@Serializable
data class RichBlockCollage(
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(blocks, caption)
    override val html: String = html(blocks, caption)

    companion object {
        const val TYPE = "collage"
        fun markdown(blocks: List<RichBlock>, caption: RichBlockCaption?): String =
            richMediaContainerMarkdown("tg-collage", blocks, caption)
        fun html(blocks: List<RichBlock>, caption: RichBlockCaption?): String =
            richMediaContainerHtml("tg-collage", blocks, caption)
    }
}

/**
 * A slideshow.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockslideshow">RichBlockSlideshow</a>
 */
@Serializable
data class RichBlockSlideshow(
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(blocks, caption)
    override val html: String = html(blocks, caption)

    companion object {
        const val TYPE = "slideshow"
        fun markdown(blocks: List<RichBlock>, caption: RichBlockCaption?): String =
            richMediaContainerMarkdown("tg-slideshow", blocks, caption)
        fun html(blocks: List<RichBlock>, caption: RichBlockCaption?): String =
            richMediaContainerHtml("tg-slideshow", blocks, caption)
    }
}

/**
 * A table.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblocktable">RichBlockTable</a>
 */
@Serializable
data class RichBlockTable(
    @SerialName(cellsField)
    val cells: List<List<RichBlockTableCell>>,
    @SerialName(isBorderedField)
    val isBordered: Boolean? = null,
    @SerialName(isStripedField)
    val isStriped: Boolean? = null,
    @SerialName(captionField)
    val caption: RichText? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(cells)
    override val html: String = html(cells, isBordered, isStriped, caption)

    companion object {
        const val TYPE = "table"
        fun markdown(cells: List<List<RichBlockTableCell>>): String {
            if (cells.isEmpty()) return ""
            fun renderRow(row: List<RichBlockTableCell>): String =
                row.joinToString(separator = " | ", prefix = "| ", postfix = " |") { it.text?.markdown ?: "" }
            fun alignment(cell: RichBlockTableCell): String = when (cell.align) {
                "left" -> ":---"
                "center" -> ":--:"
                "right" -> "---:"
                else -> "---"
            }
            val header = cells.first()
            val lines = mutableListOf(
                renderRow(header),
                header.joinToString(separator = " | ", prefix = "| ", postfix = " |") { alignment(it) }
            )
            cells.drop(1).forEach { lines.add(renderRow(it)) }
            return lines.joinToString(separator = "\n")
        }

        fun html(cells: List<List<RichBlockTableCell>>, isBordered: Boolean?, isStriped: Boolean?, caption: RichText?): String {
            val attributes = buildString {
                if (isBordered == true) append(" bordered")
                if (isStriped == true) append(" striped")
            }
            val captionPart = caption?.let { "<caption>${it.html}</caption>" } ?: ""
            val rows = cells.joinToString(separator = "") { row ->
                val renderedCells = row.joinToString(separator = "") { cell ->
                    val tag = if (cell.isHeader == true) "th" else "td"
                    val cellAttributes = buildString {
                        cell.colspan?.let { append(" colspan=\"$it\"") }
                        cell.rowspan?.let { append(" rowspan=\"$it\"") }
                        append(" align=\"${cell.align}\"")
                        append(" valign=\"${cell.valign}\"")
                    }
                    "<$tag$cellAttributes>${cell.text?.html ?: ""}</$tag>"
                }
                "<tr>$renderedCells</tr>"
            }
            return "<table$attributes>$captionPart$rows</table>"
        }
    }
}

/**
 * An expandable block for details disclosure.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockdetails">RichBlockDetails</a>
 */
@Serializable
data class RichBlockDetails(
    @SerialName(summaryField)
    val summary: RichText,
    @SerialName(blocksField)
    val blocks: List<RichBlock>,
    @SerialName(isOpenField)
    val isOpen: Boolean? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(summary, blocks, isOpen)
    override val html: String = html(summary, blocks, isOpen)

    companion object {
        const val TYPE = "details"
        fun markdown(summary: RichText, blocks: List<RichBlock>, isOpen: Boolean?): String {
            val open = if (isOpen == true) " open" else ""
            return "<details$open><summary>${summary.markdown}</summary>\n\n${blocks.toRichMarkdown()}\n\n</details>"
        }

        fun html(summary: RichText, blocks: List<RichBlock>, isOpen: Boolean?): String {
            val open = if (isOpen == true) " open" else ""
            return "<details$open><summary>${summary.html}</summary>${blocks.toRichHtml()}</details>"
        }
    }
}

/**
 * A block with a map.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockmap">RichBlockMap</a>
 */
@Serializable
data class RichBlockMap(
    @SerialName(locationField)
    val location: StaticLocation,
    /**
     * Map zoom level; 13-20.
     */
    @SerialName(zoomField)
    val zoom: Int,
    @SerialName(widthField)
    val width: Int,
    @SerialName(heightField)
    val height: Int,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(location, zoom, caption)
    override val html: String = html(location, zoom, caption)

    companion object {
        const val TYPE = "map"
        fun markdown(location: StaticLocation, zoom: Int, caption: RichBlockCaption?): String {
            val element = "<tg-map lat=\"${location.latitude}\" long=\"${location.longitude}\" zoom=\"$zoom\"/>"
            return caption?.let { "<figure>$element<figcaption>${it.text.markdown}${creditCiteMarkdown(it.credit)}</figcaption></figure>" } ?: element
        }

        fun html(location: StaticLocation, zoom: Int, caption: RichBlockCaption?): String {
            val element = "<tg-map lat=\"${location.latitude}\" long=\"${location.longitude}\" zoom=\"$zoom\"/>"
            return caption?.let { "<figure>$element<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption></figure>" } ?: element
        }
    }
}

/**
 * A block with an animation.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockanimation">RichBlockAnimation</a>
 */
@Serializable
data class RichBlockAnimation(
    @SerialName(animationField)
    val animation: AnimationFile,
    @SerialName(hasSpoilerField)
    val hasSpoiler: Boolean? = null,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(animation, caption)
    override val html: String = html(animation, hasSpoiler, caption)

    companion object {
        const val TYPE = "animation"
        fun markdown(animation: AnimationFile, caption: RichBlockCaption?): String =
            richMediaMarkdown(animation.fileId.fileId, caption)
        fun html(animation: AnimationFile, hasSpoiler: Boolean?, caption: RichBlockCaption?): String =
            richMediaHtml("video", animation.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)
    }
}

/**
 * A block with a music file.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockaudio">RichBlockAudio</a>
 */
@Serializable
data class RichBlockAudio(
    @SerialName(audioField)
    val audio: AudioFile,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(audio, caption)
    override val html: String = html(audio, caption)

    companion object {
        const val TYPE = "audio"
        fun markdown(audio: AudioFile, caption: RichBlockCaption?): String =
            richMediaMarkdown(audio.fileId.fileId, caption)
        fun html(audio: AudioFile, caption: RichBlockCaption?): String =
            richMediaHtml("audio", audio.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)
    }
}

/**
 * A block with a photo.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockphoto">RichBlockPhoto</a>
 */
@Serializable
data class RichBlockPhoto(
    @SerialName(photoField)
    val photo: PhotoFile,
    @SerialName(hasSpoilerField)
    val hasSpoiler: Boolean? = null,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(photo, caption)
    override val html: String = html(photo, hasSpoiler, caption)

    companion object {
        const val TYPE = "photo"
        fun markdown(photo: PhotoFile, caption: RichBlockCaption?): String =
            richMediaMarkdown(photo.fileId.fileId, caption)
        fun html(photo: PhotoFile, hasSpoiler: Boolean?, caption: RichBlockCaption?): String =
            richMediaHtml("img", photo.fileId.fileId, hasSpoiler == true, selfClosing = true, caption = caption)
    }
}

/**
 * A block with a video.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockvideo">RichBlockVideo</a>
 */
@Serializable
data class RichBlockVideo(
    @SerialName(videoField)
    val video: VideoFile,
    @SerialName(hasSpoilerField)
    val hasSpoiler: Boolean? = null,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(video, caption)
    override val html: String = html(video, hasSpoiler, caption)

    companion object {
        const val TYPE = "video"
        fun markdown(video: VideoFile, caption: RichBlockCaption?): String =
            richMediaMarkdown(video.fileId.fileId, caption)
        fun html(video: VideoFile, hasSpoiler: Boolean?, caption: RichBlockCaption?): String =
            richMediaHtml("video", video.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)
    }
}

/**
 * A block with a voice note.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockvoicenote">RichBlockVoiceNote</a>
 */
@Serializable
data class RichBlockVoiceNote(
    @SerialName(voiceNoteField)
    val voiceNote: VoiceFile,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(voiceNote, caption)
    override val html: String = html(voiceNote, caption)

    companion object {
        const val TYPE = "voice_note"
        fun markdown(voiceNote: VoiceFile, caption: RichBlockCaption?): String =
            richMediaMarkdown(voiceNote.fileId.fileId, caption)
        fun html(voiceNote: VoiceFile, caption: RichBlockCaption?): String =
            richMediaHtml("audio", voiceNote.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)
    }
}

/**
 * A block with a "Thinking…" placeholder. May be used only in sendRichMessageDraft.
 *
 * @see <a href="https://core.telegram.org/bots/api#richblockthinking">RichBlockThinking</a>
 */
@Serializable
data class RichBlockThinking(
    @SerialName(textField)
    val text: RichText
) : RichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val markdown: String = markdown(text)
    override val html: String = html(text)

    companion object {
        const val TYPE = "thinking"
        fun markdown(text: RichText): String = "<tg-thinking>${text.markdown}</tg-thinking>"
        fun html(text: RichText): String = "<tg-thinking>${text.html}</tg-thinking>"
    }
}
