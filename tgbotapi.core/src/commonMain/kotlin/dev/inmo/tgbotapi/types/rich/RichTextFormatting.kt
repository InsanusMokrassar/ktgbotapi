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
