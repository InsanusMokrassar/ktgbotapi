package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

fun String.toMarkdown(): String {
    return replace(
        "*",
        "\\*"
    ).replace(
        "_",
        "\\_"
    )
}
