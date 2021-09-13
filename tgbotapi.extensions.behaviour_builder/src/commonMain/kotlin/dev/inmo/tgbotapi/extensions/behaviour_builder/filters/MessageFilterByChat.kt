package dev.inmo.tgbotapi.extensions.behaviour_builder.filters

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.CallbackQuery.CallbackQuery
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.InlineQueries.query.InlineQuery
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.update.abstracts.Update

val MessageFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, Message, Update> = { message, update ->
    update.sourceChat() ?.id == message.chat.id
}
val MessagesFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, List<Message>, Update> = { messages, update ->
    val sourceChatId = update.sourceChat() ?.id
    sourceChatId != null && messages.all { sourceChatId == it.chat.id }
}

val CallbackQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, CallbackQuery, Update> = { query, update ->
    update.sourceChat() ?.id == query.user.id
}
val InlineQueryFilterByUser: BehaviourContextAndTwoTypesReceiver<Boolean, InlineQuery, Update> = { query, update ->
    update.sourceChat() ?.id == query.from.id
}
val ChatMemberUpdatedFilterByChat: BehaviourContextAndTwoTypesReceiver<Boolean, ChatMemberUpdated, Update> = { updated, update ->
    update.sourceChat() ?.id == updated.chat.id
}
