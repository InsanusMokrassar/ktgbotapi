package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.*
import dev.inmo.tgbotapi.utils.PreviewFeature

public suspend fun TelegramBot.getChat(
    chatId: ChatIdentifier
): ExtendedChat = execute(GetChat(chatId))

public suspend fun TelegramBot.getChat(
    chat: Chat
): ExtendedChat = getChat(chat.id)

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPublicChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: PublicChat
): ExtendedPublicChat = getChat(chat.id) as ExtendedPublicChat


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedChannelChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: ChannelChat
): ExtendedChannelChat = getChat(chat.id) as ExtendedChannelChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedChannelChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: ChannelChatImpl
): ExtendedChannelChatImpl = getChat(chat.id) as ExtendedChannelChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedGroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: GroupChat
): ExtendedGroupChat = getChat(chat.id) as ExtendedGroupChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedGroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: GroupChatImpl
): ExtendedGroupChatImpl = getChat(chat.id) as ExtendedGroupChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedSupergroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: SupergroupChat
): ExtendedSupergroupChat = getChat(chat.id) as ExtendedSupergroupChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedSupergroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: SupergroupChatImpl
): ExtendedSupergroupChatImpl = getChat(chat.id) as ExtendedSupergroupChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPrivateChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: PrivateChat
): ExtendedPrivateChat = getChat(chat.id) as ExtendedPrivateChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPrivateChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: PrivateChatImpl
): ExtendedPrivateChatImpl = getChat(chat.id) as ExtendedPrivateChatImpl

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedUser] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
public suspend fun TelegramBot.getChat(
    chat: CommonUser
): ExtendedUser = getChat(chat.id) as ExtendedUser
