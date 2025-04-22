package dev.inmo.tgbotapi.bot

import dev.inmo.tgbotapi.utils.TelegramAPIUrlsKeeper

abstract class BaseRequestsExecutor(
    protected val telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
) : RequestsExecutor
