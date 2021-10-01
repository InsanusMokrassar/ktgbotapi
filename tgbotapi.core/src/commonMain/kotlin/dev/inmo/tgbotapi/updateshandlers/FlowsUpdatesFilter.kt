package dev.inmo.tgbotapi.updateshandlers

import dev.inmo.tgbotapi.types.ALL_UPDATES_LIST
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.*
import dev.inmo.tgbotapi.types.update.abstracts.UnknownUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import kotlinx.coroutines.channels.BufferOverflow
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

/**
 * Creates [DefaultFlowsUpdatesFilter]
 */
@Suppress("FunctionName")
fun FlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100
) = DefaultFlowsUpdatesFilter(broadcastChannelsSize)

@Suppress("unused")
class DefaultFlowsUpdatesFilter(
    broadcastChannelsSize: Int = 100,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
): FlowsUpdatesFilter {
    private val updatesSharedFlow = MutableSharedFlow<Update>(extraBufferCapacity = broadcastChannelsSize, onBufferOverflow = onBufferOverflow)
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

    override val messagesFlow: Flow<MessageUpdate> = allUpdatesFlow.filterIsInstance()
    override val messageMediaGroupsFlow: Flow<MessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedMessagesFlow: Flow<EditMessageUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedMessageMediaGroupsFlow: Flow<EditMessageMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val channelPostsFlow: Flow<ChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    override val channelPostMediaGroupsFlow: Flow<ChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedChannelPostsFlow: Flow<EditChannelPostUpdate> = allUpdatesFlow.filterIsInstance()
    override val editedChannelPostMediaGroupsFlow: Flow<EditChannelPostMediaGroupUpdate> = allUpdatesFlow.filterIsInstance()
    override val chosenInlineResultsFlow: Flow<ChosenInlineResultUpdate> = allUpdatesFlow.filterIsInstance()
    override val inlineQueriesFlow: Flow<InlineQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val callbackQueriesFlow: Flow<CallbackQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val shippingQueriesFlow: Flow<ShippingQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val preCheckoutQueriesFlow: Flow<PreCheckoutQueryUpdate> = allUpdatesFlow.filterIsInstance()
    override val pollsFlow: Flow<PollUpdate> = allUpdatesFlow.filterIsInstance()
    override val pollAnswersFlow: Flow<PollAnswerUpdate> = allUpdatesFlow.filterIsInstance()
    override val chatMemberUpdatesFlow: Flow<CommonChatMemberUpdatedUpdate> = allUpdatesFlow.filterIsInstance()
    override val myChatMemberUpdatesFlow: Flow<MyChatMemberUpdatedUpdate> = allUpdatesFlow.filterIsInstance()
    override val unknownUpdatesFlow: Flow<UnknownUpdate> = allUpdatesFlow.filterIsInstance()
}
