package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceUser
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.chat.member.ChatMemberUpdated
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.payments.PreCheckoutQuery
import dev.inmo.tgbotapi.types.payments.ShippingQuery
import dev.inmo.tgbotapi.types.queries.callback.CallbackQuery
import dev.inmo.tgbotapi.types.update.abstracts.Update

/**
 * Allow only events from the same chat as base [AccessibleMessage]
 */
val MessageFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, AccessibleMessage, Update> = { message, update ->
    update.sourceChat() ?.let {
        it.id == message.chat.id
    } != false
}

/**
 * Allow only events from the same chat as base [List] of [AccessibleMessage]
 */
val MessagesFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, List<AccessibleMessage>, Update> = { messages, update ->
    val sourceChatId = update.sourceChat() ?.id
    sourceChatId != null && messages.all { sourceChatId == it.chat.id }
}

/**
 * Allow only updates from the same user as base [CallbackQuery.user]
 */
val CallbackQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, CallbackQuery, Update> = { query, update ->
    update.sourceUser() ?.let {
        it.id == query.user.id
    } != false
}

/**
 * Allow only updates from the same user as base [ShippingQuery.user]
 */
val ShippingQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, ShippingQuery, Update> = { query, update ->
    update.sourceUser() ?.let {
        it.id == query.user.id
    } != false
}

/**
 * Allow only updates from the same user as base [ShippingQuery.user]
 */
val PreCheckoutQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, PreCheckoutQuery, Update> = { query, update ->
    update.sourceUser() ?.let {
        it.id == query.user.id
    } != false
}

/**
 * Allow only updates from the same user as base [InlineQuery.from]
 */
val InlineQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, InlineQuery, Update> = { query, update ->
    update.sourceUser() ?.let {
        it.id == query.user.id
    } != false
}

/**
 * Allow only events from the same chat as base [ChatMemberUpdated]
 */
val ChatMemberUpdatedFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update> = { updated, update ->
    update.sourceChat() ?.let {
        it.id == updated.chat.id
    } != false
}

/**
 * Allow only events from the same chat as base [ChatMemberUpdated]
 */
val ChatJoinRequestFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, ChatJoinRequest, Update> = { updated, update ->
    update.sourceChat() ?.id == updated.chat.id
}
