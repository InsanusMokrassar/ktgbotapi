package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

@Deprecated("Will be removed soon", ReplaceWith("messageFlow + channelPostFlow"))
val FlowsUpdatesFilter.allSentMessagesFlow: Flow<BaseSentMessageUpdate>
    get() = merge(
        messagesFlow,
        channelPostsFlow
    )

@Deprecated("Will be removed soon", ReplaceWith("messageMediaGroupFlow + channelPostMediaGroupFlow"))
val FlowsUpdatesFilter.allSentMediaGroupsFlow: Flow<SentMediaGroupUpdate>
    get() = merge(
        messageMediaGroupsFlow,
        channelPostMediaGroupsFlow
    )
