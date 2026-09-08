package dev.inmo.tgbotapi.types.rich

import dev.inmo.tgbotapi.types.animationField
import dev.inmo.tgbotapi.types.alignField
import dev.inmo.tgbotapi.types.audioField
import dev.inmo.tgbotapi.types.blocksField
import dev.inmo.tgbotapi.types.captionField
import dev.inmo.tgbotapi.types.cellsField
import dev.inmo.tgbotapi.types.buttonsField
import dev.inmo.tgbotapi.types.creditField
import dev.inmo.tgbotapi.types.documentField
import dev.inmo.tgbotapi.types.expressionField
import dev.inmo.tgbotapi.types.heightField
import dev.inmo.tgbotapi.types.isBorderedField
import dev.inmo.tgbotapi.types.isCompactField
import dev.inmo.tgbotapi.types.isOpenField
import dev.inmo.tgbotapi.types.isStripedField
import dev.inmo.tgbotapi.types.itemsField
import dev.inmo.tgbotapi.types.languageField
import dev.inmo.tgbotapi.types.location.StaticLocation
import dev.inmo.tgbotapi.types.locationField
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.media.TelegramMediaAnimation
import dev.inmo.tgbotapi.types.media.TelegramMediaAudio
import dev.inmo.tgbotapi.types.media.TelegramMediaDocument
import dev.inmo.tgbotapi.types.media.TelegramMediaPhoto
import dev.inmo.tgbotapi.types.media.TelegramMediaVideo
import dev.inmo.tgbotapi.types.media.TelegramMediaVoiceNote
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
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockparagraph">InputRichBlockParagraph</a>
 */
@Serializable
data class InputRichBlockParagraph(
    @SerialName(textField)
    val text: RichText
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "paragraph"
    }
}

/**
 * A section heading.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblocksectionheading">InputRichBlockSectionHeading</a>
 */
@Serializable
data class InputRichBlockSectionHeading(
    @SerialName(textField)
    val text: RichText,
    /**
     * Relative size of the text font; 1-6, 1 is the largest, 6 is the smallest.
     */
    @SerialName(sizeField)
    val level: Int
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "heading"
    }
}

/**
 * A preformatted text block.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockpreformatted">InputRichBlockPreformatted</a>
 */
@Serializable
data class InputRichBlockPreformatted(
    @SerialName(textField)
    val text: RichText,
    @SerialName(languageField)
    val language: String? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "pre"
    }
}

/**
 * A footer.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockfooter">InputRichBlockFooter</a>
 */
@Serializable
data class InputRichBlockFooter(
    @SerialName(textField)
    val text: RichText
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "footer"
    }
}

/**
 * A divider.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockdivider">InputRichBlockDivider</a>
 */
@Serializable
class InputRichBlockDivider : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override fun equals(other: Any?): Boolean = other is InputRichBlockDivider

    override fun hashCode(): Int = TYPE.hashCode()

    override fun toString(): String = "InputRichBlockDivider"

    companion object {
        const val TYPE = "divider"
    }
}

/**
 * A block with a mathematical expression in LaTeX format.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockmathematicalexpression">InputRichBlockMathematicalExpression</a>
 */
@Serializable
data class InputRichBlockMathematicalExpression(
    @SerialName(expressionField)
    val expression: String
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "mathematical_expression"
    }
}

/**
 * A block with an anchor.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockanchor">InputRichBlockAnchor</a>
 */
@Serializable
data class InputRichBlockAnchor(
    @SerialName(nameField)
    val name: String
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "anchor"
    }
}

/**
 * A list of blocks.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblocklist">InputRichBlockList</a>
 */
@Serializable
data class InputRichBlockList(
    @SerialName(itemsField)
    val items: List<InputRichBlockListItem>
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "list"
    }
}

/**
 * A block quotation.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockblockquotation">InputRichBlockBlockQuotation</a>
 */
@Serializable
data class InputRichBlockBlockQuotation(
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>,
    @SerialName(creditField)
    val credit: RichText? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "blockquote"
    }
}

/** A block quotation that users can expand. */
@Serializable
data class InputRichBlockExpandableBlockQuotation(
    @SerialName(textField)
    val text: RichText,
    @SerialName(creditField)
    val credit: RichText? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "expandable_blockquote"
    }
}

/**
 * A quotation with centered text.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockpullquotation">InputRichBlockPullQuotation</a>
 */
@Serializable
data class InputRichBlockPullQuotation(
    @SerialName(textField)
    val text: RichText,
    @SerialName(creditField)
    val credit: RichText? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "pullquote"
    }
}

/**
 * A collage.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockcollage">InputRichBlockCollage</a>
 */
@Serializable
data class InputRichBlockCollage(
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "collage"
    }
}

/**
 * A slideshow.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockslideshow">InputRichBlockSlideshow</a>
 */
@Serializable
data class InputRichBlockSlideshow(
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "slideshow"
    }
}

/**
 * A table.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblocktable">InputRichBlockTable</a>
 */
@Serializable
data class InputRichBlockTable(
    @SerialName(cellsField)
    val cells: List<List<RichBlockTableCell>>,
    @SerialName(isBorderedField)
    val isBordered: Boolean? = null,
    @SerialName(isStripedField)
    val isStriped: Boolean? = null,
    @SerialName(captionField)
    val caption: RichText? = null,
    @SerialName(isCompactField)
    val isCompact: Boolean? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "table"
    }
}

/** A row of rich message buttons. */
@Serializable
data class InputRichBlockButtons(
    @SerialName(buttonsField)
    val buttons: List<RichMessageButton>,
    @SerialName(alignField)
    val align: RichBlockButtonAlignment? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    init {
        require(buttons.size in 1..8) { "InputRichBlockButtons requires from 1 to 8 buttons" }
    }

    companion object {
        const val TYPE = "buttons"
    }
}

/**
 * An expandable block for details disclosure.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockdetails">InputRichBlockDetails</a>
 */
@Serializable
data class InputRichBlockDetails(
    @SerialName(summaryField)
    val summary: RichText,
    @SerialName(blocksField)
    val blocks: List<InputRichBlock>,
    @SerialName(isOpenField)
    val isOpen: Boolean? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "details"
    }
}

/**
 * A block with a map.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockmap">InputRichBlockMap</a>
 */
@Serializable
data class InputRichBlockMap(
    @SerialName(locationField)
    val location: StaticLocation,
    /**
     * Map zoom level; 0-24.
     */
    @SerialName(zoomField)
    val zoom: Int,
    @SerialName(widthField)
    val width: Int,
    @SerialName(heightField)
    val height: Int,
    @SerialName(captionField)
    val caption: RichBlockCaption? = null
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "map"
    }
}

/**
 * A block with an animation.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockanimation">InputRichBlockAnimation</a>
 */
@Serializable
data class InputRichBlockAnimation(
    @SerialName(animationField)
    val animation: TelegramMediaAnimation,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val media: TelegramMedia
        get() = animation

    companion object {
        const val TYPE = "animation"
    }
}

/**
 * A block with a music file.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockaudio">InputRichBlockAudio</a>
 */
@Serializable
data class InputRichBlockAudio(
    @SerialName(audioField)
    val audio: TelegramMediaAudio,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val media: TelegramMedia
        get() = audio

    companion object {
        const val TYPE = "audio"
    }
}

/** A block with a general file. */
@Serializable
data class InputRichBlockDocument(
    @SerialName(documentField)
    val document: TelegramMediaDocument,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE
    override val media: TelegramMedia
        get() = document

    companion object {
        const val TYPE = "document"
    }
}

/**
 * A block with a photo.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockphoto">InputRichBlockPhoto</a>
 */
@Serializable
data class InputRichBlockPhoto(
    @SerialName(photoField)
    val photo: TelegramMediaPhoto,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val media: TelegramMedia
        get() = photo

    companion object {
        const val TYPE = "photo"
    }
}

/**
 * A block with a video.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockvideo">InputRichBlockVideo</a>
 */
@Serializable
data class InputRichBlockVideo(
    @SerialName(videoField)
    val video: TelegramMediaVideo,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val media: TelegramMedia
        get() = video

    companion object {
        const val TYPE = "video"
    }
}

/**
 * A block with a voice note.
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockvoicenote">InputRichBlockVoiceNote</a>
 */
@Serializable
data class InputRichBlockVoiceNote(
    @SerialName(voiceNoteField)
    val voiceNote: TelegramMediaVoiceNote,
    @SerialName(captionField)
    override val caption: RichBlockCaption? = null
) : InputRichBlockMedia {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    override val media: TelegramMedia
        get() = voiceNote

    companion object {
        const val TYPE = "voice_note"
    }
}

/**
 * A block with a "Thinking…" placeholder. May be used only in [dev.inmo.tgbotapi.requests.send.SendRichMessageDraft].
 *
 * @see <a href="https://core.telegram.org/bots/api#inputrichblockthinking">InputRichBlockThinking</a>
 */
@Serializable
data class InputRichBlockThinking(
    @SerialName(textField)
    val text: RichText
) : InputRichBlock {
    @EncodeDefault
    @SerialName(typeField)
    override val type: String = TYPE

    companion object {
        const val TYPE = "thinking"
    }
}
