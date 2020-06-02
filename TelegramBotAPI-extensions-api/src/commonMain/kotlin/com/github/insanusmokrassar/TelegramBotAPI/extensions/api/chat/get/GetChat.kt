package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.chat.get

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.chat.get.GetChat
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.*
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.extended.*
import com.github.insanusmokrassar.TelegramBotAPI.utils.PreviewFeature

suspend fun RequestsExecutor.getChat(
    chatId: ChatIdentifier
) = execute(GetChat(chatId))

suspend fun RequestsExecutor.getChat(
    chat: Chat
) = getChat(chat.id)

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedPublicChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: PublicChat
) = getChat(chat.id) as ExtendedPublicChat


/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedChannelChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: ChannelChat
) = getChat(chat.id) as ExtendedChannelChat

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedChannelChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: ChannelChatImpl
) = getChat(chat.id) as ExtendedChannelChatImpl


/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedGroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: GroupChat
) = getChat(chat.id) as ExtendedGroupChat

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedGroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: GroupChatImpl
) = getChat(chat.id) as ExtendedGroupChatImpl


/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedSupergroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: SupergroupChat
) = getChat(chat.id) as ExtendedSupergroupChat

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedSupergroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: SupergroupChatImpl
) = getChat(chat.id) as ExtendedSupergroupChatImpl


/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedPrivateChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: PrivateChat
) = getChat(chat.id) as ExtendedPrivateChat

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedPrivateChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: PrivateChatImpl
) = getChat(chat.id) as ExtendedPrivateChatImpl

/**
 * Will cast incoming [com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.extended.ExtendedChat] to a
 * [ExtendedUser] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun RequestsExecutor.getChat(
    chat: CommonUser
) = getChat(chat.id) as ExtendedUser
