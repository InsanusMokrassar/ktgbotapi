@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.shortcuts.chat
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.flow.filter

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.onMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (List<MediaGroupMessage<T>>) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<T>>>
) = flowsUpdatesFilter.expectFlow(bot) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        if (mediaGroup.all { message -> message.content is T } && (additionalFilter == null || additionalFilter(mediaGroup as List<MediaGroupMessage<T>>))) {
            listOf(mediaGroup as List<MediaGroupMessage<T>>)
        } else {
            null
        }
    } ?: emptyList()
}.subscribeSafelyWithoutExceptions(scope) { mediaGroup ->
    val (jobToCancel, scenario) = if (includeFilterByChatInBehaviourSubContext) {
        val subFilter = FlowsUpdatesFilter()
        val subBehaviourContext = copy(flowsUpdatesFilter = subFilter)
        val mediaGroupChat = mediaGroup.chat!!

        flowsUpdatesFilter.allUpdatesFlow.filter {
            val chat = it.sourceChat() ?: return@filter false
            chat.id.chatId == mediaGroupChat.id.chatId
        }.subscribeSafelyWithoutExceptions(scope, subFilter.asUpdateReceiver) to subBehaviourContext
    } else {
        null to this
    }
    safelyWithoutExceptions { scenario.scenarioReceiver(mediaGroup) }
    jobToCancel ?.cancel()
}

suspend fun BehaviourContext.onPlaylist(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<AudioMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<AudioMediaGroupContent>>>
) = onMediaGroup(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onDocumentsGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<DocumentMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<DocumentMediaGroupContent>>>
) = onMediaGroup(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVisualGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<VisualMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = onMediaGroup(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onPhotoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<PhotoContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<PhotoContent>>>
) = onMediaGroup(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVideoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<VideoContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VideoContent>>>
) = onMediaGroup(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)

