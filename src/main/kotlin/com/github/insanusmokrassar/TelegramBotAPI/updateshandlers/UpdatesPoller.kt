package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.requests.webhook.DeleteWebhook
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.utils.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdateReceiver
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.executeUnsafe
import kotlinx.coroutines.*
import java.util.concurrent.Executors

class UpdatesPoller(
    private val executor: RequestsExecutor,
    private val requestsDelayMillis: Long = 1000,
    private val scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    private val allowedUpdates: List<String>? = null,
    private val block: UpdateReceiver<Update>
) {
    private var lastHandledUpdate: UpdateIdentifier = 0L
    private val mediaGroup: MutableList<BaseMessageUpdate> = mutableListOf()

    private var pollerJob: Job? = null

    private suspend fun sendToBlock(data: Update) {
        block(data)
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
                allowed_updates = allowedUpdates
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

    suspend fun start(): Job {
        executor.executeUnsafe(DeleteWebhook())
        return pollerJob ?: scope.launch {
            while (isActive) {
                delay(requestsDelayMillis)
                try {
                    val updates = getUpdates()
                    handleUpdates(updates)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }.also {
            pollerJob = it
        }
    }

    suspend fun stop() {
        pollerJob ?.cancelAndJoin()
    }
}