package com.github.insanusmokrassar.TelegramBotAPI.bot

import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper

abstract class BaseRequestsExecutor(
    protected val telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper
) : RequestsExecutor
