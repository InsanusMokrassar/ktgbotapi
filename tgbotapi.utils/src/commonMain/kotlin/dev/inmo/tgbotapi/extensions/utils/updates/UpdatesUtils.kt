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
 * @return The biggest [UpdateIdentifier] OR null
 *
 * @see [Update.lastUpdateIdentifier]
 */
fun List<Update>.lastUpdateIdentifier(): UpdateIdentifier? {
    return maxByOrNull { it.updateId } ?.updateId ?.takeIf { it > -1 }
}

/**
 * Will convert incoming list of [Update]s to list with [Update]s, which include [dev.inmo.tgbotapi.types.message.abstracts.ContentMessage]s
 * with [dev.inmo.tgbotapi.types.message.content.MediaGroupContent]
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
