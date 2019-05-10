package com.github.insanusmokrassar.TelegramBotAPI.updateshandlers

import com.github.insanusmokrassar.TelegramBotAPI.types.update.*
import com.github.insanusmokrassar.TelegramBotAPI.types.update.MediaGroupUpdates.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.extensions.UpdateReceiver
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

private fun <T> BroadcastChannel<T>.createUpdateReceiver(): UpdateReceiver<T> = ::send

class FlowsUpdatesFilter {
    private val messageChannel: BroadcastChannel<MessageUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val messageMediaGroupChannel: BroadcastChannel<MessageMediaGroupUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val editedMessageChannel: BroadcastChannel<EditMessageUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val editedMessageMediaGroupChannel: BroadcastChannel<EditMessageMediaGroupUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val channelPostChannel: BroadcastChannel<ChannelPostUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val channelPostMediaGroupChannel: BroadcastChannel<ChannelPostMediaGroupUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val editedChannelPostChannel: BroadcastChannel<EditChannelPostUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val editedChannelPostMediaGroupChannel: BroadcastChannel<EditChannelPostMediaGroupUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val chosenInlineResultChannel: BroadcastChannel<ChosenInlineResultUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val inlineQueryChannel: BroadcastChannel<InlineQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val callbackQueryChannel: BroadcastChannel<CallbackQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val shippingQueryChannel: BroadcastChannel<ShippingQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val preCheckoutQueryChannel: BroadcastChannel<PreCheckoutQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val pollChannel: BroadcastChannel<PollUpdate> = BroadcastChannel(Channel.CONFLATED)

    val filter = UpdatesFilter(
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
        preCheckoutQueryChannel.createUpdateReceiver()
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