package dev.inmo.tgbotapi.updateshandlers

import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.flow.*

interface FlowsUpdatesFilter : UpdatesFilter {
    override val allowedUpdates: List<String>
        get() = ALL_UPDATES_LIST
    val allUpdatesFlow: Flow<Update>
    val allUpdatesWithoutMediaGroupsGroupingFlow: Flow<Update>

    val messageFlow: Flow<MessageUpdate>
    val messageMediaGroupFlow: Flow<MessageMediaGroupUpdate>
    val editedMessageFlow: Flow<EditMessageUpdate>
    val editedMessageMediaGroupFlow: Flow<EditMessageMediaGroupUpdate>
    val channelPostFlow: Flow<ChannelPostUpdate>
    val channelPostMediaGroupFlow: Flow<ChannelPostMediaGroupUpdate>
    val editedChannelPostFlow: Flow<EditChannelPostUpdate>
    val editedChannelPostMediaGroupFlow: Flow<EditChannelPostMediaGroupUpdate>
    val chosenInlineResultFlow: Flow<ChosenInlineResultUpdate>
    val inlineQueryFlow: Flow<InlineQueryUpdate>
    val callbackQueryFlow: Flow<CallbackQueryUpdate>
    val shippingQueryFlow: Flow<ShippingQueryUpdate>
    val preCheckoutQueryFlow: Flow<PreCheckoutQueryUpdate>
    val pollFlow: Flow<PollUpdate>
    val pollAnswerFlow: Flow<PollAnswerUpdate>
    val chatMemberUpdatedFlow: Flow<CommonChatMemberUpdatedUpdate>
    val myChatMemberUpdatedFlow: Flow<MyChatMemberUpdatedUpdate>
    val unknownUpdateTypeFlow: Flow<UnknownUpdate>
}

/**
 * Creates [DefaultFlowsUpdatesFilter]
 */
@Suppress("FunctionName")
fun FlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100
) = DefaultFlowsUpdatesFilter(broadcastChannelsSize)

@Suppress("EXPERIMENTAL_API_USAGE", "unused")
class DefaultFlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100
): FlowsUpdatesFilter {
    private val updatesSharedFlow = MutableSharedFlow<Update>(extraBufferCapacity = broadcastChannelsSize)
    @Suppress("MemberVisibilityCanBePrivate")
    override val allUpdatesFlow: Flow<Update> = updatesSharedFlow.asSharedFlow()
    @Suppress("MemberVisibilityCanBePrivate")
    override val allUpdatesWithoutMediaGroupsGroupingFlow: Flow<Update> = allUpdatesFlow.flatMapConcat {
        when (it) {
            is SentMediaGroupUpdate -> it.origins.asFlow()
            is EditMediaGroupUpdate -> flowOf(it.origin)
            else -> flowOf(it)
        }
    }

    override val asUpdateReceiver: UpdateReceiver<Update> = {
        updatesSharedFlow.emit(it)
    }

    override val messageFlow: Flow<MessageUpdate> = allUpdatesFlow.filterIsInstance()
    override val messageMediaGroupFlow: Flow<MessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedMessageFlow: Flow<EditMessageUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedMessageMediaGroupFlow: Flow<EditMessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val channelPostFlow: Flow<ChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    override val channelPostMediaGroupFlow: Flow<ChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedChannelPostFlow: Flow<EditChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedChannelPostMediaGroupFlow: Flow<EditChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val chosenInlineResultFlow: Flow<ChosenInlineResultUpdate> = allUpdatesFlow.filterIsInstance()
    override val inlineQueryFlow: Flow<InlineQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val callbackQueryFlow: Flow<CallbackQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val shippingQueryFlow: Flow<ShippingQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val preCheckoutQueryFlow: Flow<PreCheckoutQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val pollFlow: Flow<PollUpdate> = allUpdatesFlow.filterIsInstance()
    override val pollAnswerFlow: Flow<PollAnswerUpdate> = allUpdatesFlow.filterIsInstance()
    override val chatMemberUpdatedFlow: Flow<CommonChatMemberUpdatedUpdate> = allUpdatesFlow.filterIsInstance()
    override val myChatMemberUpdatedFlow: Flow<MyChatMemberUpdatedUpdate> = allUpdatesFlow.filterIsInstance()
    override val unknownUpdateTypeFlow: Flow<UnknownUpdate> = allUpdatesFlow.filterIsInstance()
}