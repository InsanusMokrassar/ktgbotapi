@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.asSentMediaGroupUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.shortcuts.chat
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
internal suspend inline fun <reified T : MediaGroupContent> BehaviourContext.buildMediaGroupTrigger(
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
    val mediaGroupChat = mediaGroup.chat!!
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == mediaGroupChat.id.chatId }
        } else null,
        stopOnCompletion = false
    ) {
        scenarioReceiver(mediaGroup)
    }
}

suspend fun BehaviourContext.onMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<MediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<MediaGroupContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onPlaylist(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<AudioMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<AudioMediaGroupContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onDocumentsGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<DocumentMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<DocumentMediaGroupContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVisualGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<VisualMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVisualMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<VisualMediaGroupContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VisualMediaGroupContent>>>
) = onVisualGallery(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onPhotoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<PhotoContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<PhotoContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)
suspend fun BehaviourContext.onVideoGallery(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (List<MediaGroupMessage<VideoContent>>) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, List<MediaGroupMessage<VideoContent>>>
) = buildMediaGroupTrigger(includeFilterByChatInBehaviourSubContext, additionalFilter, scenarioReceiver)

