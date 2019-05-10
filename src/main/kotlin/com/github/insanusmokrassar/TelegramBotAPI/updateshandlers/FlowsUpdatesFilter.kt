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
    private val channelQueryChannel: BroadcastChannel<CallbackQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val shippingQueryChannel: BroadcastChannel<ShippingQueryUpdate> = BroadcastChannel(Channel.CONFLATED)
    private val preCheckoutQueryChannel: BroadcastChannel<PreCheckoutQueryUpdate> = BroadcastChannel(Channel.CONFLATED)

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
        channelQueryChannel.createUpdateReceiver(),
        shippingQueryChannel.createUpdateReceiver(),
        preCheckoutQueryChannel.createUpdateReceiver()
    )

    val messageChannelFlow: Flow<MessageUpdate> = messageChannel.asFlow()
    val messageMediaGroupChannelFlow: Flow<MessageMediaGroupUpdate> = messageMediaGroupChannel.asFlow()
    val editedMessageChannelFlow: Flow<EditMessageUpdate> = editedMessageChannel.asFlow()
    val editedMessageMediaGroupChannelFlow: Flow<EditMessageMediaGroupUpdate> = editedMessageMediaGroupChannel.asFlow()
    val channelPostChannelFlow: Flow<ChannelPostUpdate> = channelPostChannel.asFlow()
    val channelPostMediaGroupChannelFlow: Flow<ChannelPostMediaGroupUpdate> = channelPostMediaGroupChannel.asFlow()
    val editedChannelPostChannelFlow: Flow<EditChannelPostUpdate> = editedChannelPostChannel.asFlow()
    val editedChannelPostMediaGroupChannelFlow: Flow<EditChannelPostMediaGroupUpdate> = editedChannelPostMediaGroupChannel.asFlow()
    val chosenInlineResultChannelFlow: Flow<ChosenInlineResultUpdate> = chosenInlineResultChannel.asFlow()
    val inlineQueryChannelFlow: Flow<InlineQueryUpdate> = inlineQueryChannel.asFlow()
    val channelQueryChannelFlow: Flow<CallbackQueryUpdate> = channelQueryChannel.asFlow()
    val shippingQueryChannelFlow: Flow<ShippingQueryUpdate> = shippingQueryChannel.asFlow()
    val preCheckoutQueryChannelFlow: Flow<PreCheckoutQueryUpdate> = preCheckoutQueryChannel.asFlow()
}