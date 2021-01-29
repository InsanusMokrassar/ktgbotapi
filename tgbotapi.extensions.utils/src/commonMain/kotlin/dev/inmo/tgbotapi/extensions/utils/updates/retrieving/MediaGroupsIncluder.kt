package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.tgbotapi.extensions.utils.updates.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaGroupContent
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
    debounceTimeMillis: Long = 1000L
): UpdateReceiver<Update> {
    val updatesChannel = Channel<Update>(Channel.UNLIMITED)
    val mediaGroupChannel = Channel<Pair<String, BaseMessageUpdate>>(Channel.UNLIMITED)
    val mediaGroupAccumulatedChannel = mediaGroupChannel.accumulateByKey(
        debounceTimeMillis,
        scope = this
    )

    launch {
        launch {
            for (update in updatesChannel) {
                when (val data = update.data) {
                    is MediaGroupMessage<*> -> mediaGroupChannel.send("${data.mediaGroupId}${update::class.simpleName}" to update as BaseMessageUpdate)
                    else -> output(update)
                }
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

/**
 * Create [UpdateReceiver] object which will correctly accumulate updates and send into output updates which INCLUDE
 * [dev.inmo.tgbotapi.types.update.MediaGroupUpdates.MediaGroupUpdate]s.
 *
 * @see UpdateReceiver
 */
fun CoroutineScope.updateHandlerWithMediaGroupsAdaptation(
    output: UpdateReceiver<Update>
) = updateHandlerWithMediaGroupsAdaptation(output, 1000L)