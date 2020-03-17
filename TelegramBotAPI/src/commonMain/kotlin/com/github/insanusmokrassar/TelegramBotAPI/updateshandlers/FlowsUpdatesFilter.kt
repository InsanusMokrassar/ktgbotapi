package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.abstracts.Update
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

private fun <T> BroadcastChannel<T>.createUpdateReceiver(): UpdateReceiver<T> = ::send

class FlowsUpdatesFilter(
    broadcastChannelsSize: Int = Channel.CONFLATED
): UpdatesFilter {
    private val messageChannel: BroadcastChannel<MessageUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val messageMediaGroupChannel: BroadcastChannel<MessageMediaGroupUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val editedMessageChannel: BroadcastChannel<EditMessageUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val editedMessageMediaGroupChannel: BroadcastChannel<EditMessageMediaGroupUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val channelPostChannel: BroadcastChannel<ChannelPostUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val channelPostMediaGroupChannel: BroadcastChannel<ChannelPostMediaGroupUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val editedChannelPostChannel: BroadcastChannel<EditChannelPostUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val editedChannelPostMediaGroupChannel: BroadcastChannel<EditChannelPostMediaGroupUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val chosenInlineResultChannel: BroadcastChannel<ChosenInlineResultUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val inlineQueryChannel: BroadcastChannel<InlineQueryUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val callbackQueryChannel: BroadcastChannel<CallbackQueryUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val shippingQueryChannel: BroadcastChannel<ShippingQueryUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val preCheckoutQueryChannel: BroadcastChannel<PreCheckoutQueryUpdate> = BroadcastChannel(broadcastChannelsSize)
    private val pollChannel: BroadcastChannel<PollUpdate> = BroadcastChannel(broadcastChannelsSize)

    override val allowedUpdates: List<String>
        get() = filter.allowedUpdates
    override val asUpdateReceiver: UpdateReceiver<Update>
        get() = filter.asUpdateReceiver

    val filter = SimpleUpdatesFilter(
        messageChannel.createUpdateReceiver(),
        messageMediaGroupChannel.createUpdateReceiver(),
        editedMessageChannel.createUpdateReceiver(),
        editedMessageMediaGroupChannel.createUpdateReceiver(),
        channelPostChannel.createUpdateReceiver(),
        channelPostMediaGroupChannel.createUpdateReceiver(),
        editedChannelPostChannel.createUpdateReceiver(),
        editedChannelPostMediaGroupChannel.createUpdateReceiver(),
        chosenInlineResultChannel.createUpdateReceiver(),
        inlineQueryChannel.createUpdateReceiver(),
        callbackQueryChannel.createUpdateReceiver(),
        shippingQueryChannel.createUpdateReceiver(),
        preCheckoutQueryChannel.createUpdateReceiver(),
        pollChannel.createUpdateReceiver()
    )

    val messageFlow: Flow<MessageUpdate> = messageChannel.asFlow()
    val messageMediaGroupFlow: Flow<MessageMediaGroupUpdate> = messageMediaGroupChannel.asFlow()
    val editedMessageFlow: Flow<EditMessageUpdate> = editedMessageChannel.asFlow()
    val editedMessageMediaGroupFlow: Flow<EditMessageMediaGroupUpdate> = editedMessageMediaGroupChannel.asFlow()
    val channelPostFlow: Flow<ChannelPostUpdate> = channelPostChannel.asFlow()
    val channelPostMediaGroupFlow: Flow<ChannelPostMediaGroupUpdate> = channelPostMediaGroupChannel.asFlow()
    val editedChannelPostFlow: Flow<EditChannelPostUpdate> = editedChannelPostChannel.asFlow()
    val editedChannelPostMediaGroupFlow: Flow<EditChannelPostMediaGroupUpdate> = editedChannelPostMediaGroupChannel.asFlow()
    val chosenInlineResultFlow: Flow<ChosenInlineResultUpdate> = chosenInlineResultChannel.asFlow()
    val inlineQueryFlow: Flow<InlineQueryUpdate> = inlineQueryChannel.asFlow()
    val callbackQueryFlow: Flow<CallbackQueryUpdate> = callbackQueryChannel.asFlow()
    val shippingQueryFlow: Flow<ShippingQueryUpdate> = shippingQueryChannel.asFlow()
    val preCheckoutQueryFlow: Flow<PreCheckoutQueryUpdate> = preCheckoutQueryChannel.asFlow()
    val pollFlow: Flow<PollUpdate> = pollChannel.asFlow()
}