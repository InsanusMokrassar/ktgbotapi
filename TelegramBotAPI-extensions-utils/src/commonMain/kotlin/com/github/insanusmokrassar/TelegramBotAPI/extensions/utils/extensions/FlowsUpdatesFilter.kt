package com.github.insanusmokrassar.TelegramBotAPI.extensions.utils.extensions

import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.BaseSentMessageUpdate
import com.github.insanusmokrassar.TelegramBotAPI.updateshandlers.FlowsUpdatesFilter
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
