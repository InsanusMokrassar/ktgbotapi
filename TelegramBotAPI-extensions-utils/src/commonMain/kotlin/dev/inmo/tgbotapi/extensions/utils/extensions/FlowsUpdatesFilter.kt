package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge

val FlowsUpdatesFilter.allSentMessagesFlow: Flow<BaseSentMessageUpdate>
    get() = merge(
        messageFlow,
        channelPostFlow
    )

val FlowsUpdatesFilter.allSentMediaGroupsFlow: Flow<SentMediaGroupUpdate>
    get() = merge(
        messageMediaGroupFlow,
        channelPostMediaGroupFlow
    )
