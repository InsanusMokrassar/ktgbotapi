package dev.inmo.tgbotapi.utils.extensions

fun String.toMarkdown(): String {
    return replace(
        "*",
        "\\*",
    ).replace(
        "_",
        "\\_",
    ).replace(
        "`",
        "\\`",
    ).replace(
        "[",
        "\\[",
    )
}

private val markdownV2LinkEscapes = setOf(')', '\\')
private val markdownV2PreAndCodeEscapes = setOf('`', '\\')
private val markdownV2CommonEscapes = setOf(
    '_',
    '*',
    '[', ']',
    '(', ')',
    '~',
    '`',
    '>',
    '#',
    '+', '-', '=',
    '|',
    '{', '}',
    '.', '!',
)

private fun String.escapeMarkdownV2(escapeCharacters: Iterable<Char>): String = map {
    if (it in escapeCharacters) {
        "\\$it"
    } else {
        "$it"
    }
}.joinToString("")

fun String.escapeMarkdownV2Link() = escapeMarkdownV2(markdownV2LinkEscapes)

fun String.escapeMarkdownV2PreAndCode() = escapeMarkdownV2(markdownV2PreAndCodeEscapes)

fun String.escapeMarkdownV2Common() = escapeMarkdownV2(markdownV2CommonEscapes)

fun String.toHtml(): String = replace(
    "<",
    "&lt;",
).replace(
    ">",
    "&gt;",
).replace(
    "&",
    "&amp;",
)
