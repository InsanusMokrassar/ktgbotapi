package dev.inmo.tgbotapi.utils

const val telegramBotAPIDefaultUrl = "https://api.telegram.org"

class TelegramAPIUrlsKeeper(
    token: String,
    hostUrl: String = telegramBotAPIDefaultUrl
) {
    val commonAPIUrl = "$hostUrl/bot$token"
    val fileBaseUrl = "$hostUrl/file/bot$token"
}
