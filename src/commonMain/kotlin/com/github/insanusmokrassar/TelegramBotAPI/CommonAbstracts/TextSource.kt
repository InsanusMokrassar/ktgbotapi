package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

interface TextSource {
    val rawSource: String
    val asMarkdownSource: String
    val asHtmlSource: String
}