package com.github.insanusmokrassar.TelegramBotAPI.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.GetUpdates
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import com.github.insanusmokrassar.TelegramBotAPI.utils.mediaGroupId
import com.github.insanusmokrassar.TelegramBotAPI.utils.toMediaGroupUpdate
import kotlinx.coroutines.*
import java.util.concurrent.Executors

private val updatesPollerRequestExecutorCollectedException = IllegalStateException("RequestsExecutor was collected by GC. Can't continue getting updates by polling")

class UpdatesPoller(
    requestsExecutor: RequestsExecutor,
    private val requestsDelayMillis: Long = 1000,
    private val scope: CoroutineScope = CoroutineScope(Executors.newFixedThreadPool(4).asCoroutineDispatcher()),
    private val allowedUpdates: List<String>? = null,
    private val block: UpdateReceiver<Any>
) {
    private val executor = requestsExecutor.asReference()
    private var lastHandledUpdate: UpdateIdentifier = 0L
    private val mediaGroup: MutableList<MediaGroupUpdate> = mutableListOf()

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

    private suspend fun pushMediaGroupUpdate(mediaGroupUpdate: MediaGroupUpdate? = null) {
        val inputMediaGroupId = mediaGroupUpdate ?.data ?.mediaGroupId
        if (mediaGroup.isNotEmpty() && inputMediaGroupId ?.equals(mediaGroup.mediaGroupId) != true) {
            sendToBlock(listOf(*mediaGroup.toTypedArray()))
            mediaGroup.clear()
        }
        mediaGroupUpdate ?.let {
            mediaGroup.add(it)
        }
    }

    private suspend fun getUpdates(): List<Update> {
        return executor.get() ?.execute(
            GetUpdates(
                lastHandledUpdate + 1, // incremented because offset counted from 1 when updates id from 0
                allowed_updates = allowedUpdates
            )
        ) ?.map {
            it.asUpdate
        } ?: throw updatesPollerRequestExecutorCollectedException
    }

    private suspend fun handleUpdates(updates: List<Update>) {
        updates.forEach { update ->
            val mediaGroupUpdate = (update as? BaseMessageUpdate) ?.toMediaGroupUpdate()
            mediaGroupUpdate ?.let { _ ->
                pushMediaGroupUpdate(mediaGroupUpdate)
            } ?: let {
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
                    handleUpdates(getUpdates())
                } catch (e: Exception) {
                    if (e == updatesPollerRequestExecutorCollectedException) {
                        throw IllegalArgumentException(e.message)
                    }
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