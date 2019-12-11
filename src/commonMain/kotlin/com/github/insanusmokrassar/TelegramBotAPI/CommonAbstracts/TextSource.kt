package com.github.insanusmokrassar.TelegramBotAPI.CommonAbstracts

interface TextSource {
    val asMarkdownSource: String
    val asHtmlSource: String
}