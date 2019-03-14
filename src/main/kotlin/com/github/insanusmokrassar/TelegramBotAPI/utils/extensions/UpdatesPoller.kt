package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.utils.mediaGroupId
import kotlinx.coroutines.*
import java.util.concurrent.Executors

class UpdatesPoller(
    private val executor: RequestsExecutor,
    private val requestsDelayMillis: Long = 1000,
    private val scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    private val allowedUpdates: List<String>? = null,
    private val block: UpdateReceiver<Any>
) {
    private var lastHandledUpdate: UpdateIdentifier = 0L
    private val mediaGroup: MutableList<BaseMessageUpdate> = mutableListOf()

    private var pollerJob: Job? = null

    private suspend fun sendToBlock(data: Any) {
        block(data)
        lastHandledUpdate = when (data) {
            is Update -> data.updateId
            is List<*> -> (data.last() as? Update) ?.updateId ?: throw IllegalStateException(
                "Found non-updates oriented list"
            )
            else -> throw IllegalStateException(
                "Unknown type of data"
            )
        }
    }

    private suspend fun pushMediaGroupUpdate(mediaGroupUpdate: BaseMessageUpdate? = null) {
        val inputMediaGroupId = (mediaGroupUpdate ?.data as? MediaGroupMessage) ?.mediaGroupId
        if (mediaGroup.isNotEmpty() && inputMediaGroupId ?.equals(mediaGroup.mediaGroupId) != true) {
            sendToBlock(listOf(*mediaGroup.toTypedArray()))
            mediaGroup.clear()
        }
        inputMediaGroupId ?.let {
            mediaGroup.add(mediaGroupUpdate)
        }
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
        updates.forEach { update ->
            (update as? BaseMessageUpdate) ?.let {
                pushMediaGroupUpdate(it)
            } ?:let {
                pushMediaGroupUpdate()
                sendToBlock(update)
            }
        }

        pushMediaGroupUpdate()
    }

    fun start(): Job {
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