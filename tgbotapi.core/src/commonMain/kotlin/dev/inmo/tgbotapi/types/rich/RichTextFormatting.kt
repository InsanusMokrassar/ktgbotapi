package dev.inmo.tgbotapi.types.rich

/**
 * Characters which have a special meaning in the
 * [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style) and must be escaped with a backslash
 * to be represented literally.
 */
private val richMarkdownSpecialCharacters = setOf(
    '\\', '`', '*', '_', '~', '|', '[', ']', '(', ')', '<', '>', '#', '=', '!', '$'
)

/**
 * Escapes all the [richMarkdownSpecialCharacters] of the receiver with a backslash so that the resulting string is
 * represented literally in the [Rich Markdown style](https://core.telegram.org/bots/api#rich-markdown-style).
 */
fun String.escapeRichMarkdown(): String = buildString {
    for (character in this@escapeRichMarkdown) {
        if (character in richMarkdownSpecialCharacters) {
            append('\\')
        }
        append(character)
    }
}

/**
 * Plain (unformatted) text of this [RichText]. For [RichTextEntity]s without an inner [RichText] it falls back to the
 * most meaningful textual representation: alternative text for custom emojis, the expression for mathematical
 * expressions and an empty string for anchors.
 */
val RichText.source: String
    get() = when (this) {
        is RichTextPlain -> text
        is RichTextGroup -> parts.joinToString(separator = "") { it.source }
        is RichTextCustomEmoji -> alternativeText
        is RichTextMathematicalExpression -> expression
        is RichTextAnchor -> ""
        is RichTextBold -> text.source
        is RichTextItalic -> text.source
        is RichTextUnderline -> text.source
        is RichTextStrikethrough -> text.source
        is RichTextSpoiler -> text.source
        is RichTextSubscript -> text.source
        is RichTextSuperscript -> text.source
        is RichTextMarked -> text.source
        is RichTextCode -> text.source
        is RichTextDateTime -> text.source
        is RichTextTextMention -> text.source
        is RichTextUrl -> text.source
        is RichTextEmailAddress -> text.source
        is RichTextPhoneNumber -> text.source
        is RichTextBankCardNumber -> text.source
        is RichTextMention -> text.source
        is RichTextHashtag -> text.source
        is RichTextCashtag -> text.source
        is RichTextBotCommand -> text.source
        is RichTextAnchorLink -> text.source
        is RichTextReference -> text.source
        is RichTextReferenceLink -> text.source
    }
