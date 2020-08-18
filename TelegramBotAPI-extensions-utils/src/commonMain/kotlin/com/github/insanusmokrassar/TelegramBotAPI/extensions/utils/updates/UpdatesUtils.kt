package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.updates

import com.github.insanusmokrassar.TelegramBotAPI.types.MediaGroupIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.UpdateIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.MediaGroupMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*

/**
 * @return If [this] is [SentMediaGroupUpdate] - [Update.updateId] of [last] element, or its own [Update.updateId]
 */
fun Update.lastUpdateIdentifier(): UpdateIdentifier {
    return if (this is SentMediaGroupUpdate) {
        origins.last().updateId
    } else {
        updateId
    }
}

/**
 * @return The biggest [UpdateIdentifier] OR null
 *
 * @see [Update.lastUpdateIdentifier]
 */
fun List<Update>.lastUpdateIdentifier(): UpdateIdentifier? {
    return maxByOrNull { it.updateId } ?.lastUpdateIdentifier()
}

/**
 * Will convert incoming list of updates to list with [MediaGroupUpdate]s
 */
fun List<Update>.convertWithMediaGroupUpdates(): List<Update> {
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

/**
 * @receiver List of [BaseSentMessageUpdate] where [BaseSentMessageUpdate.data] is [MediaGroupMessage] and all messages
 * have the same [MediaGroupMessage.mediaGroupId]
 * @return [MessageMediaGroupUpdate] in case if [first] object of [this] is [MessageUpdate]. When [first] object is
 * [ChannelPostUpdate] instance - will return [ChannelPostMediaGroupUpdate]. Otherwise will be returned null
 */
fun List<BaseSentMessageUpdate>.toSentMediaGroupUpdate(): SentMediaGroupUpdate? = (this as? SentMediaGroupUpdate) ?: let {
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

/**
 * @return [EditMessageMediaGroupUpdate] in case if [this] is [EditMessageUpdate]. When [this] object is
 * [EditChannelPostUpdate] instance - will return [EditChannelPostMediaGroupUpdate]
 *
 * @throws IllegalStateException
 */
fun BaseEditMessageUpdate.toEditMediaGroupUpdate(): EditMediaGroupUpdate = (this as? EditMediaGroupUpdate) ?: let {
    when (this) {
        is EditMessageUpdate -> EditMessageMediaGroupUpdate(this)
        is EditChannelPostUpdate -> EditChannelPostMediaGroupUpdate(this)
        else -> error("Unsupported type of ${BaseEditMessageUpdate::class.simpleName}")
    }
}
