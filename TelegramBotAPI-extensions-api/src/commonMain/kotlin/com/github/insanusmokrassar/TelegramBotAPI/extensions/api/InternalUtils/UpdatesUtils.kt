package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.InternalUtils

import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*

internal fun Update.lastUpdateIdentifier(): UpdateIdentifier {
    return if (this is SentMediaGroupUpdate) {
        origins.last().updateId
    } else {
        updateId
    }
}

internal fun List<Update>.lastUpdateIdentifier(): UpdateIdentifier? {
    return maxByOrNull { it.updateId } ?.lastUpdateIdentifier()
}

internal fun List<Update>.convertWithMediaGroupUpdates(): List<Update> {
    val resultUpdates = mutableListOf<Update>()
    val mediaGroups = mutableMapOf<MediaGroupIdentifier, MutableList<BaseSentMessageUpdate>>()
    for (update in this) {
        val data = (update.data as? MediaGroupMessage)
        if (data == null) {
            resultUpdates.add(update)
            continue
        }
        when (update) {
            is BaseEditMessageUpdate -> resultUpdates.add(
                update.toEditMediaGroupUpdate()
            )
            is BaseSentMessageUpdate -> {
                mediaGroups.getOrPut(data.mediaGroupId) {
                    mutableListOf()
                }.add(update)
            }
            else -> resultUpdates.add(update)
        }
    }
    mediaGroups.values.map {
        it.toSentMediaGroupUpdate() ?.let { mediaGroupUpdate ->
            resultUpdates.add(mediaGroupUpdate)
        }
    }
    resultUpdates.sortBy { it.updateId }
    return resultUpdates
}

internal fun List<BaseSentMessageUpdate>.toSentMediaGroupUpdate(): SentMediaGroupUpdate? = (this as? SentMediaGroupUpdate) ?: let {
    if (isEmpty()) {
        return@let null
    }
    val resultList = sortedBy { it.updateId }
    when (first()) {
        is MessageUpdate -> MessageMediaGroupUpdate(resultList)
        is ChannelPostUpdate -> ChannelPostMediaGroupUpdate(resultList)
        else -> null
    }
}

internal fun BaseEditMessageUpdate.toEditMediaGroupUpdate(): EditMediaGroupUpdate = (this as? EditMediaGroupUpdate) ?: let {
    when (this) {
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> error("Unsupported type of ${BaseEditMessageUpdate::class.simpleName}")
    }
}
