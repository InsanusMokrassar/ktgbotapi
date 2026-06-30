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

internal fun richOpenAttribute(isOpen: Boolean?): String = if (isOpen == true) " open" else ""

internal fun creditCiteMarkdown(credit: RichText?): String = credit?.let { "<cite>${it.markdown}</cite>" } ?: ""

internal fun creditCiteHtml(credit: RichText?): String = credit?.let { "<cite>${it.html}</cite>" } ?: ""

internal fun richBlockListMarkdown(list: RichBlockList): String =
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

internal fun richBlockListHtml(list: RichBlockList): String {
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

internal fun richBlockQuotationMarkdown(blocks: List<RichBlock>, credit: RichText?): String {
    val quoted = blocks.toRichMarkdown().lineSequence().joinToString(separator = "\n") { line ->
        if (line.isEmpty()) ">" else "> $line"
    }
    return quoted + (credit?.let { "\n> ${creditCiteMarkdown(it)}" } ?: "")
}

internal fun richMediaContainerMarkdown(tag: String, blocks: List<RichBlock>, caption: RichBlockCaption?): String {
    val media = blocks.joinToString(separator = "\n") { it.markdown }
    val captionPart = caption?.let { "\n<figcaption>${it.text.markdown}${creditCiteMarkdown(it.credit)}</figcaption>" } ?: ""
    return "<$tag>\n\n$media$captionPart\n\n</$tag>"
}

internal fun richMediaContainerHtml(tag: String, blocks: List<RichBlock>, caption: RichBlockCaption?): String {
    val media = blocks.joinToString(separator = "") { it.html }
    val captionPart = caption?.let { "<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption>" } ?: ""
    return "<$tag>$media$captionPart</$tag>"
}

internal fun richMediaMarkdown(source: String, caption: RichBlockCaption?): String =
    caption?.let { "![](" + source + " \"" + it.text.source + "\")" } ?: "![]($source)"

internal fun richMediaHtml(tag: String, source: String, spoiler: Boolean, selfClosing: Boolean, caption: RichBlockCaption?): String {
    val spoilerAttribute = if (spoiler) " tg-spoiler" else ""
    val element = if (selfClosing) "<$tag src=\"$source\"$spoilerAttribute/>" else "<$tag src=\"$source\"$spoilerAttribute></$tag>"
    return caption?.let { "<figure>$element<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption></figure>" } ?: element
}

internal fun richBlockMapMarkdown(map: RichBlockMap): String {
    val element = "<tg-map lat=\"${map.location.latitude}\" long=\"${map.location.longitude}\" zoom=\"${map.zoom}\"/>"
    return map.caption?.let { "<figure>$element<figcaption>${it.text.markdown}${creditCiteMarkdown(it.credit)}</figcaption></figure>" } ?: element
}

internal fun richBlockMapHtml(map: RichBlockMap): String {
    val element = "<tg-map lat=\"${map.location.latitude}\" long=\"${map.location.longitude}\" zoom=\"${map.zoom}\"/>"
    return map.caption?.let { "<figure>$element<figcaption>${it.text.html}${creditCiteHtml(it.credit)}</figcaption></figure>" } ?: element
}

internal fun richBlockTableMarkdown(table: RichBlockTable): String {
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

internal fun richBlockTableHtml(table: RichBlockTable): String {
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
