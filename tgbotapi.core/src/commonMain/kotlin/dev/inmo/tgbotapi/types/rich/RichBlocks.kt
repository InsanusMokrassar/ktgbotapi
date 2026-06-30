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

    override val markdown: String
        get() = text.markdown
    override val html: String
        get() = "<p>${text.html}</p>"

    companion object {
        const val TYPE = "paragraph"
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

    override val markdown: String
        get() = "#".repeat(size) + " " + text.markdown
    override val html: String
        get() = "<h$size>${text.html}</h$size>"

    companion object {
        const val TYPE = "heading"
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

    override val markdown: String
        get() = "```" + (language ?: "") + "\n" + text.source + "\n```"
    override val html: String
        get() = language?.let { "<pre><code class=\"language-$it\">${text.html}</code></pre>" } ?: "<pre>${text.html}</pre>"

    companion object {
        const val TYPE = "pre"
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

    override val markdown: String
        get() = "<footer>${text.markdown}</footer>"
    override val html: String
        get() = "<footer>${text.html}</footer>"

    companion object {
        const val TYPE = "footer"
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

    override val markdown: String
        get() = "---"
    override val html: String
        get() = "<hr/>"

    override fun equals(other: Any?): Boolean = other is RichBlockDivider

    override fun hashCode(): Int = TYPE.hashCode()

    override fun toString(): String = "RichBlockDivider"

    companion object {
        const val TYPE = "divider"
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

    override val markdown: String
        get() = "\$\$" + expression + "\$\$"
    override val html: String
        get() = "<tg-math-block>$expression</tg-math-block>"

    companion object {
        const val TYPE = "mathematical_expression"
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

    override val markdown: String
        get() = "<a name=\"$name\"></a>"
    override val html: String
        get() = "<a name=\"$name\"></a>"

    companion object {
        const val TYPE = "anchor"
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

    override val markdown: String
        get() = richBlockListMarkdown(this)
    override val html: String
        get() = richBlockListHtml(this)

    companion object {
        const val TYPE = "list"
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

    override val markdown: String
        get() = richBlockQuotationMarkdown(blocks, credit)
    override val html: String
        get() = "<blockquote>${blocks.toRichHtml()}${creditCiteHtml(credit)}</blockquote>"

    companion object {
        const val TYPE = "blockquote"
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

    override val markdown: String
        get() = "<aside>${text.markdown}${creditCiteMarkdown(credit)}</aside>"
    override val html: String
        get() = "<aside>${text.html}${creditCiteHtml(credit)}</aside>"

    companion object {
        const val TYPE = "pullquote"
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

    override val markdown: String
        get() = richMediaContainerMarkdown("tg-collage", blocks, caption)
    override val html: String
        get() = richMediaContainerHtml("tg-collage", blocks, caption)

    companion object {
        const val TYPE = "collage"
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

    override val markdown: String
        get() = richMediaContainerMarkdown("tg-slideshow", blocks, caption)
    override val html: String
        get() = richMediaContainerHtml("tg-slideshow", blocks, caption)

    companion object {
        const val TYPE = "slideshow"
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

    override val markdown: String
        get() = richBlockTableMarkdown(this)
    override val html: String
        get() = richBlockTableHtml(this)

    companion object {
        const val TYPE = "table"
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

    override val markdown: String
        get() = "<details${richOpenAttribute(isOpen)}><summary>${summary.markdown}</summary>\n\n${blocks.toRichMarkdown()}\n\n</details>"
    override val html: String
        get() = "<details${richOpenAttribute(isOpen)}><summary>${summary.html}</summary>${blocks.toRichHtml()}</details>"

    companion object {
        const val TYPE = "details"
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

    override val markdown: String
        get() = richBlockMapMarkdown(this)
    override val html: String
        get() = richBlockMapHtml(this)

    companion object {
        const val TYPE = "map"
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

    override val markdown: String
        get() = richMediaMarkdown(animation.fileId.fileId, caption)
    override val html: String
        get() = richMediaHtml("video", animation.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)

    companion object {
        const val TYPE = "animation"
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

    override val markdown: String
        get() = richMediaMarkdown(audio.fileId.fileId, caption)
    override val html: String
        get() = richMediaHtml("audio", audio.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)

    companion object {
        const val TYPE = "audio"
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

    override val markdown: String
        get() = richMediaMarkdown(photo.fileId.fileId, caption)
    override val html: String
        get() = richMediaHtml("img", photo.fileId.fileId, hasSpoiler == true, selfClosing = true, caption = caption)

    companion object {
        const val TYPE = "photo"
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

    override val markdown: String
        get() = richMediaMarkdown(video.fileId.fileId, caption)
    override val html: String
        get() = richMediaHtml("video", video.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)

    companion object {
        const val TYPE = "video"
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

    override val markdown: String
        get() = richMediaMarkdown(voiceNote.fileId.fileId, caption)
    override val html: String
        get() = richMediaHtml("audio", voiceNote.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)

    companion object {
        const val TYPE = "voice_note"
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

    override val markdown: String
        get() = "<tg-thinking>${text.markdown}</tg-thinking>"
    override val html: String
        get() = "<tg-thinking>${text.html}</tg-thinking>"

    companion object {
        const val TYPE = "thinking"
    }
}
