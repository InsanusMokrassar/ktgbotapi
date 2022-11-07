package dev.inmo.tgbotapi.extensions.api.utils

import dev.inmo.tgbotapi.extensions.api.InternalUtils.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.types.message.abstracts.PossiblySentViaBotCommonMessage
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.updateshandlers.UpdateReceiver
import dev.inmo.tgbotapi.utils.extensions.accumulateByKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

/**
 * Create [UpdateReceiver] object which will correctly accumulate updates and send into output updates which INCLUDE
 * [dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate]s.
 *
 * @see UpdateReceiver
 */
fun CoroutineScope.updateHandlerWithMediaGroupsAdaptation(
    output: UpdateReceiver<Update>,
    mediaGroupsDebounceMillis: Long = 1000L
): UpdateReceiver<Update> {
    val updatesChannel = Channel<Update>(Channel.UNLIMITED)
    val mediaGroupChannel = Channel<Pair<String, BaseMessageUpdate>>(Channel.UNLIMITED)
    val mediaGroupAccumulatedChannel = mediaGroupChannel.accumulateByKey(
        mediaGroupsDebounceMillis,
        scope = this
    )

    launch {
        launch {
            for (update in updatesChannel) {
                val dataAsPossiblySentViaBotCommonMessage = update.data as? PossiblySentViaBotCommonMessage<*>

                if (dataAsPossiblySentViaBotCommonMessage == null) {
                    output(update)
                    continue
                }

                val mediaGroupId = dataAsPossiblySentViaBotCommonMessage.mediaGroupId

                if (mediaGroupId == null) {
                    output(update)
                    continue
                }

                mediaGroupChannel.send("${mediaGroupId}${update::class.simpleName}" to update as BaseMessageUpdate)
            }
        }
        launch {
            for ((_, mediaGroup) in mediaGroupAccumulatedChannel) {
                mediaGroup.convertWithMediaGroupUpdates().forEach {
                    output(it)
                }
            }
        }
    }

    return { updatesChannel.send(it) }
}
