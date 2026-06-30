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

internal fun creditCiteMarkdown(credit: RichText?): String = credit?.let { "<cite>${it.markdown}</cite>" } ?: ""

internal fun creditCiteHtml(credit: RichText?): String = credit?.let { "<cite>${it.html}</cite>" } ?: ""

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
