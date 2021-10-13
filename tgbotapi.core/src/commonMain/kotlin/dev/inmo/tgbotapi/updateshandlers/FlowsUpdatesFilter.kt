package dev.inmo.tgbotapi.updateshandlers

import dev.inmo.micro_utils.coroutines.plus
import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.flow.*

interface FlowsUpdatesFilter : UpdatesFilter {
    override val allowedUpdates: List<String>
        get() = ALL_UPDATES_LIST
    val allUpdatesFlow: Flow<Update>
    val allUpdatesWithoutMediaGroupsGroupingFlow: Flow<Update>

    val messagesFlow: Flow<MessageUpdate>
    val messageMediaGroupsFlow: Flow<MessageMediaGroupUpdate>
    val editedMessagesFlow: Flow<EditMessageUpdate>
    val editedMessageMediaGroupsFlow: Flow<EditMessageMediaGroupUpdate>
    val channelPostsFlow: Flow<ChannelPostUpdate>
    val channelPostMediaGroupsFlow: Flow<ChannelPostMediaGroupUpdate>
    val editedChannelPostsFlow: Flow<EditChannelPostUpdate>
    val editedChannelPostMediaGroupsFlow: Flow<EditChannelPostMediaGroupUpdate>
    val chosenInlineResultsFlow: Flow<ChosenInlineResultUpdate>
    val inlineQueriesFlow: Flow<InlineQueryUpdate>
    val callbackQueriesFlow: Flow<CallbackQueryUpdate>
    val shippingQueriesFlow: Flow<ShippingQueryUpdate>
    val preCheckoutQueriesFlow: Flow<PreCheckoutQueryUpdate>
    val pollsFlow: Flow<PollUpdate>
    val pollAnswersFlow: Flow<PollAnswerUpdate>
    val chatMemberUpdatesFlow: Flow<CommonChatMemberUpdatedUpdate>
    val myChatMemberUpdatesFlow: Flow<MyChatMemberUpdatedUpdate>
    val unknownUpdatesFlow: Flow<UnknownUpdate>
}

abstract class AbstractFlowsUpdatesFilter : FlowsUpdatesFilter {
    override val allUpdatesWithoutMediaGroupsGroupingFlow: Flow<Update>
        get() = allUpdatesFlow.flatMapConcat {
            when (it) {
                is SentMediaGroupUpdate -> it.origins.asFlow()
                is EditMediaGroupUpdate -> flowOf(it.origin)
                else -> flowOf(it)
            }
        }

    override val messagesFlow: Flow<MessageUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val messageMediaGroupsFlow: Flow<MessageMediaGroupUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val editedMessagesFlow: Flow<EditMessageUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val editedMessageMediaGroupsFlow: Flow<EditMessageMediaGroupUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val channelPostsFlow: Flow<ChannelPostUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val channelPostMediaGroupsFlow: Flow<ChannelPostMediaGroupUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val editedChannelPostsFlow: Flow<EditChannelPostUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val editedChannelPostMediaGroupsFlow: Flow<EditChannelPostMediaGroupUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val chosenInlineResultsFlow: Flow<ChosenInlineResultUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val inlineQueriesFlow: Flow<InlineQueryUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val callbackQueriesFlow: Flow<CallbackQueryUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val shippingQueriesFlow: Flow<ShippingQueryUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val preCheckoutQueriesFlow: Flow<PreCheckoutQueryUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val pollsFlow: Flow<PollUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val pollAnswersFlow: Flow<PollAnswerUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val chatMemberUpdatesFlow: Flow<CommonChatMemberUpdatedUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val myChatMemberUpdatesFlow: Flow<MyChatMemberUpdatedUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
    override val unknownUpdatesFlow: Flow<UnknownUpdate> by lazy { allUpdatesFlow.filterIsInstance() }
}

/**
 * Creates [DefaultFlowsUpdatesFilter]
 */
@Suppress("FunctionName")
fun FlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    upstreamUpdatesFlow: Flow<Update>? = null
) = DefaultFlowsUpdatesFilter(broadcastChannelsSize, onBufferOverflow, upstreamUpdatesFlow)

@Suppress("unused")
class DefaultFlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    upstreamUpdatesFlow: Flow<Update>? = null
): AbstractFlowsUpdatesFilter() {
    private val additionalUpdatesSharedFlow = MutableSharedFlow<Update>(0, broadcastChannelsSize, onBufferOverflow)
    @Suppress("MemberVisibilityCanBePrivate")
    override val allUpdatesFlow: Flow<Update> = (additionalUpdatesSharedFlow.asSharedFlow()).let {
        if (upstreamUpdatesFlow != null) {
            (it + upstreamUpdatesFlow).distinctUntilChanged { old, new -> old.updateId == new.updateId }
        } else {
            it
        }
    }
    @Suppress("MemberVisibilityCanBePrivate")
    override val allUpdatesWithoutMediaGroupsGroupingFlow: Flow<Update> = allUpdatesFlow.flatMapConcat {
        when (it) {
            is SentMediaGroupUpdate -> it.origins.asFlow()
            is EditMediaGroupUpdate -> flowOf(it.origin)
            else -> flowOf(it)
        }
    }

    override val asUpdateReceiver: UpdateReceiver<Update> = additionalUpdatesSharedFlow::emit
}
