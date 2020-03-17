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
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.executeUnsafe
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

@Deprecated("Deprecated due to more simple way to get updates using TelegramBotAPI-extensions-api")
fun KtorUpdatesPoller(
    telegramAPIUrlsKeeper: TelegramAPIUrlsKeeper,
    engine: HttpClientEngine,
    timeoutSeconds: Int? = null,
    oneTimeUpdatesLimit: Int? = null,
    allowedUpdates: List<String> = ALL_UPDATES_LIST,
    exceptionsHandler: (Exception) -> Boolean = { true },
    updatesReceiver: UpdateReceiver<Update>
): KtorUpdatesPoller {
    val executor = KtorRequestsExecutor(
        telegramAPIUrlsKeeper,
        HttpClient(engine)
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

@Deprecated("Deprecated due to more simple way to get updates using TelegramBotAPI-extensions-api")
class KtorUpdatesPoller(
    private val executor: RequestsExecutor,
    private val timeoutSeconds: Int? = null,
    private val oneTimeUpdatesLimit: Int? = null,
    private val allowedUpdates: List<String> = ALL_UPDATES_LIST,
    private val exceptionsHandler: (Exception) -> Boolean = { true },
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
        )
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

    private val startStopScope = CoroutineScope(Dispatchers.Default)
    private val startStopQueue = Channel<CoroutineScope?>(2)
    private val startStopJob = startStopScope.launch {
        for (scope in startStopQueue) {
            scope ?.also {
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
            } ?: also {
                startStopQueue.close()
                pollerJob ?.cancel()
                startStopScope.cancel()
                return@launch
            }
        }
    }
    override fun start(scope: CoroutineScope) {
        startStopQueue.offer(scope)
    }

    override fun close() {
        startStopQueue.offer(null)
    }
}