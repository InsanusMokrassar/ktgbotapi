package dev.inmo.tgbotapi.extensions.utils.updates

import dev.inmo.tgbotapi.extensions.utils.withContentOrNull
import dev.inmo.tgbotapi.types.MediaGroupIdentifier
import dev.inmo.tgbotapi.types.UpdateIdentifier
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.*
import dev.inmo.tgbotapi.utils.extensions.asMediaGroupMessage

/**
 * @return If [this] is [SentMediaGroupUpdate] - [Update.updateId] of [last] element, or its own [Update.updateId]
 */
@Deprecated("Redundant", ReplaceWith("updateId"))
fun Update.lastUpdateIdentifier(): UpdateIdentifier {
    return updateId
}

/**
 * @return The biggest [UpdateIdentifier] OR null
 *
 * @see [Update.lastUpdateIdentifier]
 */
fun List<Update>.lastUpdateIdentifier(): UpdateIdentifier? {
    return maxByOrNull { it.updateId } ?.updateId
}

/**
 * Will convert incoming list of updates to list with [MediaGroupUpdate]s
 */
fun List<Update>.convertWithMediaGroupUpdates(): List<Update> {
    val resultUpdates = mutableListOf<Update>()
    val mediaGroups = mutableMapOf<MediaGroupIdentifier, MutableList<Pair<BaseSentMessageUpdate, PossiblySentViaBotCommonMessage<MediaGroupPartContent>>>>()

    for (update in this) {
        val message = (update.data as? PossiblySentViaBotCommonMessage<*>) ?.withContentOrNull<MediaGroupPartContent>()
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
            update.copy(updatesWithMessages.map { it.second }.asMediaGroupMessage())
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
fun BaseEditMessageUpdate.toEditMediaGroupUpdate() = this
