package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper

abstract class BaseRequestsExecutor(
    protected val telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
) : RequestsExecutor {
    @Deprecated("Deprecated due to new TelegramAPIUrlKeeper API", ReplaceWith("telegramAPIUrlsKeeper.commonAPIUrl"))
    protected val baseUrl: String
        get() = telegramAPIUrlsKeeper.commonAPIUrl

    @Deprecated("Deprecated due to new TelegramAPIUrlKeeper API")
    constructor(
        token: String,
        hostUrl: String = "https://api.telegram.org"
    ) : this (TelegramAPIUrlsKeeper(token, hostUrl))
}