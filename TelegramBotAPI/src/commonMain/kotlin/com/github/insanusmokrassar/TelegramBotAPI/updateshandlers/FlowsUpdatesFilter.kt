package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.types.ALL_UPDATES_LIST
import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.*

@Suppress("EXPERIMENTAL_API_USAGE", "unused")
class FlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100
): UpdatesFilter {
    private val updatesReceivingChannel = BroadcastChannel<Update>(broadcastChannelsSize)
    @Suppress("MemberVisibilityCanBePrivate")
    val allUpdatesFlow: Flow<Update> = updatesReceivingChannel.asFlow()

    override val allowedUpdates: List<String>
        get() = ALL_UPDATES_LIST
    override val asUpdateReceiver: UpdateReceiver<Update> = {
        updatesReceivingChannel.send(it)
    }

    val messageFlow: Flow<MessageUpdate> = allUpdatesFlow.filterIsInstance()
    val messageMediaGroupFlow: Flow<MessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    val editedMessageFlow: Flow<EditMessageUpdate> = allUpdatesFlow.filterIsInstance()
    val editedMessageMediaGroupFlow: Flow<EditMessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    val channelPostFlow: Flow<ChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    val channelPostMediaGroupFlow: Flow<ChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    val editedChannelPostFlow: Flow<EditChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    val editedChannelPostMediaGroupFlow: Flow<EditChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    val chosenInlineResultFlow: Flow<ChosenInlineResultUpdate> = allUpdatesFlow.filterIsInstance()
    val inlineQueryFlow: Flow<InlineQueryUpdate> = allUpdatesFlow.filterIsInstance()
    val callbackQueryFlow: Flow<CallbackQueryUpdate> = allUpdatesFlow.filterIsInstance()
    val shippingQueryFlow: Flow<ShippingQueryUpdate> = allUpdatesFlow.filterIsInstance()
    val preCheckoutQueryFlow: Flow<PreCheckoutQueryUpdate> = allUpdatesFlow.filterIsInstance()
    val pollFlow: Flow<PollUpdate> = allUpdatesFlow.filterIsInstance()
    val pollAnswerFlow: Flow<PollAnswerUpdate> = allUpdatesFlow.filterIsInstance()
    val unknownUpdateTypeFlow: Flow<UnknownUpdate> = allUpdatesFlow.filterIsInstance()
}