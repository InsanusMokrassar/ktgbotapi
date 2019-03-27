package com.github.insanusmokrassar.TelegramBotAPI.types.MessageEntity

abstract class LinkMessageEntity(
    val linkTitle: String,
    val link: String
) : MessageEntity {

    override val asMarkdownSource: String by lazy {
        "[$linkTitle]($link)"
    }
    override val asHtmlSource: String by lazy {
        "<a href=\"$link\">$linkTitle</a>"
    }
}
