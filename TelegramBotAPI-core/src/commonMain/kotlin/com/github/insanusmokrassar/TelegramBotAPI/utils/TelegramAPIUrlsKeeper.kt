package com.github.insanusmokrassar.TelegramBotAPI.utils

class TelegramAPIUrlsKeeper(
    token: String,
    hostUrl: String = "https://api.telegram.org"
) {
    val commonAPIUrl = "$hostUrl/bot$token"
    val fileBaseUrl = "$hostUrl/file/bot$token"
}
