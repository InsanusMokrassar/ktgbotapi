package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.utils.TelegramAPIUrlsKeeper
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdateReceiver
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.engine.cio.endpoint
import io.ktor.util.KtorExperimentalAPI

@KtorExperimentalAPI
fun KtorUpdatesPoller(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    timeoutSeconds: Int? = null,
    oneTimeUpdatesLimit: Int? = null,
    allowedUpdates: List<String> = ALL_UPDATES_LIST,
    exceptionsHandler: (Exception) -> Boolean = { true },
    updatesReceiver: UpdateReceiver<Update>
): KtorUpdatesPoller {
    val executor = KtorRequestsExecutor(
        telegramAPIUrlsKeeper,
        HttpClient(
            CIO.create {
                endpoint {
                    timeoutSeconds ?.times(1000) ?.also { timeOutMillis ->
                        keepAliveTime = timeOutMillis.toLong()
                    }
                    connectTimeout = 1000
                }
            }
        )
    )

    return KtorUpdatesPoller(
        executor,
        timeoutSeconds,
        oneTimeUpdatesLimit,
        allowedUpdates,
        exceptionsHandler,
        updatesReceiver
    )
}
