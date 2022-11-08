package dev.inmo.tgbotapi.extensions.api.chat.get

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.get.GetChat
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.chat.ChannelChat
import dev.inmo.tgbotapi.types.chat.ChannelChatImpl
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.chat.CommonUser
import dev.inmo.tgbotapi.types.chat.ExtendedChannelChat
import dev.inmo.tgbotapi.types.chat.ExtendedChannelChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedGroupChat
import dev.inmo.tgbotapi.types.chat.ExtendedGroupChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedPrivateChat
import dev.inmo.tgbotapi.types.chat.ExtendedPrivateChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedPublicChat
import dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChat
import dev.inmo.tgbotapi.types.chat.ExtendedSupergroupChatImpl
import dev.inmo.tgbotapi.types.chat.ExtendedUser
import dev.inmo.tgbotapi.types.chat.GroupChat
import dev.inmo.tgbotapi.types.chat.GroupChatImpl
import dev.inmo.tgbotapi.types.chat.PrivateChat
import dev.inmo.tgbotapi.types.chat.PrivateChatImpl
import dev.inmo.tgbotapi.types.chat.PublicChat
import dev.inmo.tgbotapi.types.chat.SupergroupChat
import dev.inmo.tgbotapi.types.chat.SupergroupChatImpl
import dev.inmo.tgbotapi.utils.PreviewFeature

suspend fun TelegramBot.getChat(
    chatId: ChatIdentifier
) = execute(GetChat(chatId))

suspend fun TelegramBot.getChat(
    chat: Chat
) = getChat(chat.id)

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPublicChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: PublicChat
) = getChat(chat.id) as ExtendedPublicChat


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedChannelChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: ChannelChat
) = getChat(chat.id) as ExtendedChannelChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedChannelChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: ChannelChatImpl
) = getChat(chat.id) as ExtendedChannelChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedGroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: GroupChat
) = getChat(chat.id) as ExtendedGroupChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedGroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: GroupChatImpl
) = getChat(chat.id) as ExtendedGroupChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedSupergroupChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: SupergroupChat
) = getChat(chat.id) as ExtendedSupergroupChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedSupergroupChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: SupergroupChatImpl
) = getChat(chat.id) as ExtendedSupergroupChatImpl


/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPrivateChat] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: PrivateChat
) = getChat(chat.id) as ExtendedPrivateChat

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedPrivateChatImpl] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: PrivateChatImpl
) = getChat(chat.id) as ExtendedPrivateChatImpl

/**
 * Will cast incoming [dev.inmo.tgbotapi.types.chat.ExtendedChat] to a
 * [ExtendedUser] with unsafe operator "as"
 *
 * @throws ClassCastException
 */
@PreviewFeature
suspend fun TelegramBot.getChat(
    chat: CommonUser
) = getChat(chat.id) as ExtendedUser
