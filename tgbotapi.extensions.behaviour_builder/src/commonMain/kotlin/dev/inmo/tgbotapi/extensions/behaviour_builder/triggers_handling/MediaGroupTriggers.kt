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
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.ContactContent
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.flow.filter

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.onMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = flowsUpdatesFilter.expectFlow(bot) { update ->
    update.asSentMediaGroupUpdate() ?.data ?.let { mediaGroup ->
        if (mediaGroup.all { message -> message.content is T } && (additionalFilter == null || additionalFilter(mediaGroup))) {
            listOf(mediaGroup)
        } else {
            null
        }
    } ?: emptyList()
}.subscribeSafelyWithoutExceptions(scope) { mediaGroup ->
    val (jobToCancel, scenario) = if (includeFilterByChatInBehaviourSubContext) {
        val subFilter = FlowsUpdatesFilter()
        val subBehaviourContext = copy(flowsUpdatesFilter = subFilter)

        flowsUpdatesFilter.allUpdatesFlow.filter {
            val chat = it.sourceChat() ?: return@filter false
            chat.id.chatId == mediaGroup.chat!!.id.chatId
        }.subscribeSafelyWithoutExceptions(scope, subFilter.asUpdateReceiver) to subBehaviourContext
    } else {
        null to this
    }
    safelyWithoutExceptions { scenario.scenarioReceiver(mediaGroup) }
    jobToCancel ?.cancel()
}

suspend fun BehaviourContext.onPlaylist(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = onMediaGroup<AudioMediaGroupContent>(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onDocumentsGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = onMediaGroup<DocumentMediaGroupContent>(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVisualGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = onMediaGroup<VisualMediaGroupContent>(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onPhotoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = onMediaGroup<PhotoContent>(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVideoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage>>
) = onMediaGroup<VideoContent>(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)

