package dev.inmo.tgbotapi.types.rich

/**
 * Builds the [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) source string for this list
 * of [RichBlock]s. The resulting string may be passed to [InputRichMessageMarkdown].
 *
 * Media blocks ([RichBlockPhoto], [RichBlockVideo], [RichBlockAudio], [RichBlockVoiceNote] and [RichBlockAnimation]) are
 * rendered using the Telegram file_id as the media source. Telegram only accepts HTTP(S) URLs for rich media, so for
 * those blocks the output is a faithful structural representation rather than a directly sendable message.
 */
fun List<RichBlock>.toRichMarkdown(): String = joinToString(separator = "\n\n") { it.markdown }

/**
 * Builds the [Rich HTML style](https://core.telegram.org/bots/api#rich-html-style) source string for this list of
 * [RichBlock]s. The resulting string may be passed to [InputRichMessageHTML]. See [toRichMarkdown] for the media note.
 */
fun List<RichBlock>.toRichHtml(): String = joinToString(separator = "\n") { it.html }

/**
 * [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) source of all the [RichTextInfo.blocks].
 */
val RichTextInfo.markdown: String
    get() = blocks.toRichMarkdown()

/**
 * [Rich HTML style](https://core.telegram.org/bots/api#rich-html-style) source of all the [RichTextInfo.blocks].
 */
val RichTextInfo.html: String
    get() = blocks.toRichHtml()

/**
 * [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) source of this single [RichBlock].
 */
val RichBlock.markdown: String
    get() = when (this) {
        is RichBlockParagraph -> text.markdown
        is RichBlockSectionHeading -> "#".repeat(size) + " " + text.markdown
        is RichBlockPreformatted -> "```" + (language ?: "") + "\n" + text.source + "\n```"
        is RichBlockFooter -> "<footer>${text.markdown}</footer>"
        is RichBlockDivider -> "---"
        is RichBlockMathematicalExpression -> "\$\$" + expression + "\$\$"
        is RichBlockAnchor -> "<a name=\"$name\"></a>"
        is RichBlockList -> richBlockListMarkdown(this)
        is RichBlockBlockQuotation -> richBlockQuotationMarkdown(blocks, credit)
        is RichBlockPullQuotation -> "<aside>${text.markdown}${creditCiteMarkdown(credit)}</aside>"
        is RichBlockCollage -> richMediaContainerMarkdown("tg-collage", blocks, caption)
        is RichBlockSlideshow -> richMediaContainerMarkdown("tg-slideshow", blocks, caption)
        is RichBlockTable -> richBlockTableMarkdown(this)
        is RichBlockDetails -> "<details${richOpenAttribute(isOpen)}><summary>${summary.markdown}</summary>\n\n${blocks.toRichMarkdown()}\n\n</details>"
        is RichBlockMap -> richBlockMapMarkdown(this)
        is RichBlockAnimation -> richMediaMarkdown(animation.fileId.fileId, caption)
        is RichBlockAudio -> richMediaMarkdown(audio.fileId.fileId, caption)
        is RichBlockPhoto -> richMediaMarkdown(photo.fileId.fileId, caption)
        is RichBlockVideo -> richMediaMarkdown(video.fileId.fileId, caption)
        is RichBlockVoiceNote -> richMediaMarkdown(voiceNote.fileId.fileId, caption)
        is RichBlockThinking -> "<tg-thinking>${text.markdown}</tg-thinking>"
    }

/**
 * [Rich HTML style](https://core.telegram.org/bots/api#rich-html-style) source of this single [RichBlock].
 */
val RichBlock.html: String
    get() = when (this) {
        is RichBlockParagraph -> "<p>${text.html}</p>"
        is RichBlockSectionHeading -> "<h$size>${text.html}</h$size>"
        is RichBlockPreformatted -> language?.let { "<pre><code class=\"language-$it\">${text.html}</code></pre>" } ?: "<pre>${text.html}</pre>"
        is RichBlockFooter -> "<footer>${text.html}</footer>"
        is RichBlockDivider -> "<hr/>"
        is RichBlockMathematicalExpression -> "<tg-math-block>$expression</tg-math-block>"
        is RichBlockAnchor -> "<a name=\"$name\"></a>"
        is RichBlockList -> richBlockListHtml(this)
        is RichBlockBlockQuotation -> "<blockquote>${blocks.toRichHtml()}${creditCiteHtml(credit)}</blockquote>"
        is RichBlockPullQuotation -> "<aside>${text.html}${creditCiteHtml(credit)}</aside>"
        is RichBlockCollage -> richMediaContainerHtml("tg-collage", blocks, caption)
        is RichBlockSlideshow -> richMediaContainerHtml("tg-slideshow", blocks, caption)
        is RichBlockTable -> richBlockTableHtml(this)
        is RichBlockDetails -> "<details${richOpenAttribute(isOpen)}><summary>${summary.html}</summary>${blocks.toRichHtml()}</details>"
        is RichBlockMap -> richBlockMapHtml(this)
        is RichBlockAnimation -> richMediaHtml("video", animation.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)
        is RichBlockAudio -> richMediaHtml("audio", audio.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)
        is RichBlockPhoto -> richMediaHtml("img", photo.fileId.fileId, hasSpoiler == true, selfClosing = true, caption = caption)
        is RichBlockVideo -> richMediaHtml("video", video.fileId.fileId, hasSpoiler == true, selfClosing = false, caption = caption)
        is RichBlockVoiceNote -> richMediaHtml("audio", voiceNote.fileId.fileId, spoiler = false, selfClosing = false, caption = caption)
        is RichBlockThinking -> "<tg-thinking>${text.html}</tg-thinking>"
    }

private fun richOpenAttribute(isOpen: Boolean?): String = if (isOpen == true) " open" else ""

private fun creditCiteMarkdown(credit: RichText?): String = credit?.let { "<cite>${it.markdown}</cite>" } ?: ""

private fun creditCiteHtml(credit: RichText?): String = credit?.let { "<cite>${it.html}</cite>" } ?: ""

private fun richBlockListMarkdown(list: RichBlockList): String =
    list.items.mapIndexed { index, item ->
        val marker = when {
            item.hasCheckbox == true -> if (item.isChecked == true) "- [x] " else "- [ ] "
            item.labelType != null -> "${item.value ?: (index + 1)}. "
            else -> "- "
        }
        item.blocks.toRichMarkdown().lineSequence().mapIndexed { lineIndex, line ->
            if (lineIndex == 0) "$marker$line" else "  $line"
        }.joinToString(separator = "\n")
    }.joinToString(separator = "\n")

private fun richBlockListHtml(list: RichBlockList): String {
    val ordered = list.items.any { it.labelType != null }
    val tag = if (ordered) "ol" else "ul"
    val items = list.items.joinToString(separator = "") { item ->
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
    return "<$tag>$items</$tag>"
}

private fun richBlockQuotationMarkdown(blocks: List<RichBlock>, credit: RichText?): String {
    val quoted = blocks.toRichMarkdown().lineSequence().joinToString(separator = "\n") { line ->
        if (line.isEmpty()) ">" else "> $line"
    }
    return quoted + (credit?.let { "\n> ${creditCiteMarkdown(it)}" } ?: "")
}

private fun richMediaContainerMarkdown(tag: String, blocks: List<RichBlock>, caption: RichBlockCaption?): String {
    val media = blocks.joinToString(separator = "\n") { it.markdown }
    val captionPart = caption?.let { "\n<figcaption>${it.text.markdown}${creditCiteMarkdown(it.credit)}</figcaption>" } ?: ""
    return "<$tag>\n\n$media$captionPart\n\n</$tag>"
}

private fun richMediaContainerHtml(tag: String, blocks: List<RichBlock>, caption: RichBlockCaption?): String {
    val media = blocks.joinToString(separator = "") { it.html }
    val captionPart = caption?.let { "<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption>" } ?: ""
    return "<$tag>$media$captionPart</$tag>"
}

private fun richMediaMarkdown(source: String, caption: RichBlockCaption?): String =
    caption?.let { "![](" + source + " \"" + it.text.source + "\")" } ?: "![]($source)"

private fun richMediaHtml(tag: String, source: String, spoiler: Boolean, selfClosing: Boolean, caption: RichBlockCaption?): String {
    val spoilerAttribute = if (spoiler) " tg-spoiler" else ""
    val element = if (selfClosing) "<$tag src=\"$source\"$spoilerAttribute/>" else "<$tag src=\"$source\"$spoilerAttribute></$tag>"
    return caption?.let { "<figure>$element<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption></figure>" } ?: element
}

private fun richBlockMapMarkdown(map: RichBlockMap): String {
    val element = "<tg-map lat=\"${map.location.latitude}\" long=\"${map.location.longitude}\" zoom=\"${map.zoom}\"/>"
    return map.caption?.let { "<figure>$element<figcaption>${it.text.markdown}${creditCiteMarkdown(it.credit)}</figcaption></figure>" } ?: element
}

private fun richBlockMapHtml(map: RichBlockMap): String {
    val element = "<tg-map lat=\"${map.location.latitude}\" long=\"${map.location.longitude}\" zoom=\"${map.zoom}\"/>"
    return map.caption?.let { "<figure>$element<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption></figure>" } ?: element
}

private fun richBlockTableMarkdown(table: RichBlockTable): String {
    if (table.cells.isEmpty()) return ""
    fun renderRow(row: List<RichBlockTableCell>): String =
        row.joinToString(separator = " | ", prefix = "| ", postfix = " |") { it.text?.markdown ?: "" }
    fun alignment(cell: RichBlockTableCell): String = when (cell.align) {
        "left" -> ":---"
        "center" -> ":--:"
        "right" -> "---:"
        else -> "---"
    }
    val header = table.cells.first()
    val lines = mutableListOf(
        renderRow(header),
        header.joinToString(separator = " | ", prefix = "| ", postfix = " |") { alignment(it) }
    )
    table.cells.drop(1).forEach { lines.add(renderRow(it)) }
    return lines.joinToString(separator = "\n")
}

private fun richBlockTableHtml(table: RichBlockTable): String {
    val attributes = buildString {
        if (table.isBordered == true) append(" bordered")
        if (table.isStriped == true) append(" striped")
    }
    val caption = table.caption?.let { "<caption>${it.html}</caption>" } ?: ""
    val rows = table.cells.joinToString(separator = "") { row ->
        val cells = row.joinToString(separator = "") { cell ->
            val tag = if (cell.isHeader == true) "th" else "td"
            val cellAttributes = buildString {
                cell.colspan?.let { append(" colspan=\"$it\"") }
                cell.rowspan?.let { append(" rowspan=\"$it\"") }
                append(" align=\"${cell.align}\"")
                append(" valign=\"${cell.valign}\"")
            }
            "<$tag$cellAttributes>${cell.text?.html ?: ""}</$tag>"
        }
        "<tr>$cells</tr>"
    }
    return "<table$attributes>$caption$rows</table>"
}
