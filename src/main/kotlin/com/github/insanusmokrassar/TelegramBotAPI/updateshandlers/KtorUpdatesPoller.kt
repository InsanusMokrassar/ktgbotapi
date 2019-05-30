package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.bot.Ktor.KtorRequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.bot.UpdatesPoller
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.DeleteWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdateReceiver
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.executeUnsafe
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import kotlinx.coroutines.*

fun KtorUpdatesPoller(
    token: String,
    timeoutSeconds: Int? = null,
    oneTimeUpdatesLimit: Int? = null,
    allowedUpdates: List<String> = ALL_UPDATES_LIST,
    exceptionsHandler: (Exception) -> Boolean = { true },
    updatesReceiver: UpdateReceiver<Update>
): KtorUpdatesPoller {
    val executor = KtorRequestsExecutor(
        token,
        HttpClient(
            CIO.create {
                timeoutSeconds ?.let { _ ->
                    val timeout = timeoutSeconds.toLong() * 1000
                    endpoint.apply {
                        keepAliveTime = timeout
                        connectTimeout = 1000
                    }
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

class KtorUpdatesPoller(
    private val executor: RequestsExecutor,
    private val timeoutSeconds: Int? = null,
    private val oneTimeUpdatesLimit: Int? = null,
    private val allowedUpdates: List<String> = ALL_UPDATES_LIST,
    private val exceptionsHandler: (Exception) -> Boolean = { it.printStackTrace(); true },
    private val updatesReceiver: UpdateReceiver<Update>
) : UpdatesPoller {
    private var lastHandledUpdate: UpdateIdentifier = 0L
    private val mediaGroup: MutableList<BaseMessageUpdate> = mutableListOf()

    private var pollerJob: Job? = null

    private suspend fun sendToBlock(data: Update) {
        updatesReceiver(data)
        lastHandledUpdate = data.updateId
    }

    private suspend fun pushMediaGroupUpdate(update: BaseMessageUpdate? = null) {
        val inputMediaGroupId = (update ?.data as? MediaGroupMessage) ?.mediaGroupId
        if (mediaGroup.isNotEmpty() && inputMediaGroupId ?.equals(mediaGroup.mediaGroupId) != true) {
            mediaGroup.sortBy { it.updateId }
            mediaGroup.convertWithMediaGroupUpdates().forEach {
                sendToBlock(it)
            }
            mediaGroup.clear()
        }
        inputMediaGroupId ?.let {
            mediaGroup.add(update)
        } ?: sendToBlock(update ?: return)
    }

    private suspend fun getUpdates(): List<Update> {
        return executor.execute(
            GetUpdates(
                lastHandledUpdate + 1, // incremented because offset counted from 1 when updates id from 0
                oneTimeUpdatesLimit,
                timeoutSeconds,
                allowedUpdates
            )
        ).map {
            it.asUpdate
        }
    }

    private suspend fun handleUpdates(updates: List<Update>) {
        for (update in updates) {
            (update as? BaseMessageUpdate) ?.let {
                if (it.data is MediaGroupMessage) {
                    pushMediaGroupUpdate(it)
                } else {
                    null
                }
            } ?:let {
                pushMediaGroupUpdate()
                sendToBlock(update)
            }
        }

        pushMediaGroupUpdate()
    }

    @Synchronized
    override fun start(scope: CoroutineScope) {
        pollerJob ?: scope.launch {
            executor.executeUnsafe(DeleteWebhook())
            while (isActive) {
                try {
                    val updates = getUpdates()
                    handleUpdates(updates)
                } catch (e: Exception) {
                    if (exceptionsHandler(e)) {
                        continue
                    } else {
                        close()
                        break
                    }
                }
            }
        }.also {
            pollerJob = it
        }
    }

    @Synchronized
    override fun close() {
        pollerJob ?.cancel()
    }
}