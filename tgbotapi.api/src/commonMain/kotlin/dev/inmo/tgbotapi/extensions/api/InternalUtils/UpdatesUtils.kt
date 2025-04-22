package dev.inmo.tgbotapi.extensions.api.InternalUtils

import dev.inmo.tgbotapi.types.MediaGroupId
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.utils.extensions.asMediaGroupMessage

/**
 * Will convert incoming list of updates to list with [MediaGroupUpdate]s
 */
internal fun List<Update>.convertWithMediaGroupUpdates(): List<Update> {
    val resultUpdates = mutableListOf<Update>()
    val mediaGroups =
        mutableMapOf<MediaGroupId, MutableList<Pair<BaseSentMessageUpdate, PossiblySentViaBotCommonMessage<MediaGroupPartContent>>>>()

    for (update in this) {
        val message =
            (update.data as? PossiblySentViaBotCommonMessage<*>) ?.let {
                if (it.content is MediaGroupPartContent) {
                    it as PossiblySentViaBotCommonMessage<MediaGroupPartContent>
                } else {
                    null
                }
            }
        val mediaGroupId = message ?.mediaGroupId
        if (message == null || mediaGroupId == null) {
            resultUpdates.add(update)
            continue
        }
        when (update) {
            is BaseSentMessageUpdate -> {
                mediaGroups.getOrPut(mediaGroupId) {
                    mutableListOf()
                }.add(update to message)
            }
            else -> resultUpdates.add(update)
        }
    }

    mediaGroups.map { (_, updatesWithMessages) ->
        val update = updatesWithMessages.maxBy { it.first.updateId }.first
        resultUpdates.add(
            update.copy(updatesWithMessages.map { it.second }.asMediaGroupMessage()),
        )
    }

    resultUpdates.sortBy { it.updateId }
    return resultUpdates
}

/**
 * @return [EditMessageMediaGroupUpdate] in case if [this] is [EditMessageUpdate]. When [this] object is
 * [EditChannelPostUpdate] instance - will return [EditChannelPostMediaGroupUpdate]
 *
 * @throws IllegalStateException
 */
internal fun BaseEditMessageUpdate.toEditMediaGroupUpdate() = this
