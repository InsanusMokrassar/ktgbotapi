package dev.inmo.tgbotapi.extensions.utils.extensions

import dev.inmo.tgbotapi.abstracts.FromUser
import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.extensions.utils.asUser
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.queries.callback.*
import dev.inmo.tgbotapi.types.update.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature
import dev.inmo.tgbotapi.utils.toUser

fun CallbackQuery.sourceChat() =
    when (this) {
        is InlineMessageIdDataCallbackQuery -> null
        is AbstractMessageCallbackQuery -> message.chat
        is InlineMessageIdGameShortNameCallbackQuery -> null
        is UnknownCallbackQueryType -> null
    }

@PreviewFeature
fun Update.sourceChatWithConverters(
    baseMessageUpdateConverter: (BaseMessageUpdate) -> Chat? = { it.data.chat },
    chatJoinRequestUpdateConverter: (ChatJoinRequestUpdate) -> Chat? = { it.data.chat },
    shippingQueryUpdateConverter: (ShippingQueryUpdate) -> Chat? = { null },
    pollAnswerUpdateConverter: (PollAnswerUpdate) -> Chat? = { null },
    preCheckoutQueryUpdateConverter: (PreCheckoutQueryUpdate) -> Chat? = { it.data.from },
    callbackQueryUpdateConverter: (CallbackQueryUpdate) -> Chat? = { it.data.sourceChat() },
    chosenInlineResultUpdateConverter: (ChosenInlineResultUpdate) -> Chat? = { null },
    inlineQueryUpdateConverter: (InlineQueryUpdate) -> Chat? = { null },
    pollUpdateConverter: (PollUpdate) -> Chat? = { null },
    channelPostUpdateConverter: (ChannelPostUpdate) -> Chat? = { it.data.chat },
    messageUpdateConverter: (MessageUpdate) -> Chat? = { it.data.chat },
    editChannelPostUpdateConverter: (EditChannelPostUpdate) -> Chat? = { it.data.chat },
    editMessageUpdateConverter: (EditMessageUpdate) -> Chat? = { it.data.chat },
    myChatMemberUpdatedUpdateConverter: (MyChatMemberUpdatedUpdate) -> Chat? = { it.data.chat },
    chatMessageReactionUpdatedUpdateConverter: (ChatMessageReactionUpdatedUpdate) -> Chat? = { it.data.chat },
    chatMessageReactionsCountUpdatedUpdateConverter: (ChatMessageReactionsCountUpdatedUpdate) -> Chat? = { it.data.chat },
    chatBoostUpdatedUpdateFlow: (ChatBoostUpdatedUpdate) -> Chat? = { it.data.chat },
    chatBoostRemovedUpdateFlow: (ChatBoostRemovedUpdate) -> Chat? = { it.data.chat },
    businessConnectionUpdateConverter: (BusinessConnectionUpdate) -> Chat? = { it.data.user },
    businessMessageUpdateConverter: (BusinessMessageUpdate) -> Chat? = { it.data.chat },
    editBusinessMessageUpdateConverter: (EditBusinessMessageUpdate) -> Chat? = { it.data.chat },
    deleteBusinessMessageUpdateConverter: (DeletedBusinessMessageUpdate) -> Chat? = { it.data.chat },
    paidMediaPurchasedUpdatesFlowConverter: (PaidMediaPurchasedUpdate) -> Chat? = { it.data.user },
    commonChatMemberUpdatedUpdateConverter: (CommonChatMemberUpdatedUpdate) -> Chat? = { it.data.chat },
): Chat? =
    when (this) {
        is BaseMessageUpdate -> baseMessageUpdateConverter(this)
        is ChatJoinRequestUpdate -> chatJoinRequestUpdateConverter(this)
        is ShippingQueryUpdate -> shippingQueryUpdateConverter(this)
        is PollAnswerUpdate -> pollAnswerUpdateConverter(this)
        is PreCheckoutQueryUpdate -> preCheckoutQueryUpdateConverter(this)
        is CallbackQueryUpdate -> callbackQueryUpdateConverter(this)
        is ChosenInlineResultUpdate -> chosenInlineResultUpdateConverter(this)
        is InlineQueryUpdate -> inlineQueryUpdateConverter(this)
        is PollUpdate -> pollUpdateConverter(this)
        is ChannelPostUpdate -> channelPostUpdateConverter(this)
        is MessageUpdate -> messageUpdateConverter(this)
        is EditChannelPostUpdate -> editChannelPostUpdateConverter(this)
        is EditMessageUpdate -> editMessageUpdateConverter(this)
        is MyChatMemberUpdatedUpdate -> myChatMemberUpdatedUpdateConverter(this)
        is CommonChatMemberUpdatedUpdate -> commonChatMemberUpdatedUpdateConverter(this)
        is ChatMessageReactionUpdatedUpdate -> chatMessageReactionUpdatedUpdateConverter(this)
        is ChatMessageReactionsCountUpdatedUpdate -> chatMessageReactionsCountUpdatedUpdateConverter(this)
        is ChatBoostUpdatedUpdate -> chatBoostUpdatedUpdateFlow(this)
        is ChatBoostRemovedUpdate -> chatBoostRemovedUpdateFlow(this)
        is BusinessConnectionUpdate -> businessConnectionUpdateConverter(this)
        is BusinessMessageUpdate -> businessMessageUpdateConverter(this)
        is EditBusinessMessageUpdate -> editBusinessMessageUpdateConverter(this)
        is DeletedBusinessMessageUpdate -> deleteBusinessMessageUpdateConverter(this)
        is PaidMediaPurchasedUpdate -> paidMediaPurchasedUpdatesFlowConverter(this)
        else -> {
            when (val data = data) {
                is FromUser -> data.from
                is WithUser -> data.user
                else -> null
            }
        }
    }

@PreviewFeature
fun Update.sourceChat(): Chat? = sourceChatWithConverters()

/**
 * Trying to get the user from [Update]. In some cases it can be the user without actual fields like
 * [dev.inmo.tgbotapi.types.chat.CommonUser.isPremium] due to in these cases will be used [toUser] cast
 */
@PreviewFeature
fun Update.sourceUser(): User? =
    when (val data = data) {
        is FromUser -> data.from
        is WithUser -> data.user
        else -> sourceChat() ?.asUser() ?: ((sourceChat() as? PrivateChat) ?.toUser())
    }
