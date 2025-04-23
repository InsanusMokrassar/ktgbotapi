package dev.inmo.tgbotapi.extensions.utils.updates.retrieving

import dev.inmo.micro_utils.coroutines.launchSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.utils.updates.convertWithMediaGroupUpdates
import dev.inmo.tgbotapi.types.message.abstracts.PossiblyMediaGroupMessage
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
    debounceTimeMillis: Long = 1000L,
): UpdateReceiver<Update> {
    val updatesChannel = Channel<Update>(Channel.UNLIMITED)
    val mediaGroupChannel = Channel<Pair<String, BaseMessageUpdate>>(Channel.UNLIMITED)
    val mediaGroupAccumulatedChannel = mediaGroupChannel.accumulateByKey(
        debounceTimeMillis,
        scope = this,
    )

    launch {
        launchSafelyWithoutExceptions {
            for (update in updatesChannel) {
                val data = update.data
                when {
                    data is PossiblyMediaGroupMessage<*> && data.mediaGroupId != null -> {
                        mediaGroupChannel.send("${data.mediaGroupId}${update::class.simpleName}" to update as BaseMessageUpdate)
                    }
                    else -> output(update)
                }
            }
        }
        launchSafelyWithoutExceptions {
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
fun CoroutineScope.updateHandlerWithMediaGroupsAdaptation(output: UpdateReceiver<Update>) = updateHandlerWithMediaGroupsAdaptation(output, 1000L)
