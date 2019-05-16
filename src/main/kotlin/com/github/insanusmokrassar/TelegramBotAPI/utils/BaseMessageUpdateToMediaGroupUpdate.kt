package com.github.insanusmokrassar.TelegramBotAPI.utils

import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update

private inline val Pair<MediaGroupMessage, *>.message
    get() = first

internal fun List<BaseMessageUpdate>.convertWithMediaGroupUpdates(): List<Update> {
    val resultUpdates = mutableListOf<Update>()
    val mediaGroups = mutableMapOf<MediaGroupIdentifier, MutableList<BaseMessageUpdate>>()
    for (update in this) {
        val asEditMediaGroupMessage = update.toEditMediaGroupUpdate()
        if (asEditMediaGroupMessage != null) {
            resultUpdates.add(asEditMediaGroupMessage)
        } else {
            val data = update.data
            if (data is MediaGroupMessage) {
                (mediaGroups[data.mediaGroupId] ?: mutableListOf<BaseMessageUpdate>().also { mediaGroups[data.mediaGroupId] = it }).add(update)
            } else {
                resultUpdates.add(update)
            }
        }
    }
    mediaGroups.values.map {
        it.toSentMediaGroupUpdate() ?.let { mediaGroupUpdate ->
            resultUpdates.add(mediaGroupUpdate)
        }
    }
    return resultUpdates.sortedBy { it.updateId }
}

internal fun List<BaseMessageUpdate>.toSentMediaGroupUpdate(): SentMediaGroupUpdate? = (this as? SentMediaGroupUpdate) ?: let {
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

internal fun BaseMessageUpdate.toEditMediaGroupUpdate(): EditMediaGroupUpdate? = (this as? EditMediaGroupUpdate) ?: let {
    when (this) {
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> null
    }
}
