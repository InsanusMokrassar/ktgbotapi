package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SetMessageReactions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.reactions.Reaction
import kotlin.jvm.JvmName

public suspend fun TelegramBot.setMessageReactions(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
): Boolean = execute(
    SetMessageReactions(chatId, messageId, reactions, big)
)

public suspend fun TelegramBot.setMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reaction: Reaction? = null,
    big: Boolean = false
): Boolean = setMessageReactions(chatId, messageId, listOfNotNull(reaction), big)

public suspend fun TelegramBot.setMessageReactions(
    chat: Chat,
    messageId: MessageId,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
): Boolean = setMessageReactions(chat.id, messageId, reactions, big)

public suspend fun TelegramBot.setMessageReaction(
    chat: Chat,
    messageId: MessageId,
    reaction: Reaction? = null,
    big: Boolean = false
): Boolean = setMessageReaction(chat.id, messageId, reaction, big)

public suspend fun TelegramBot.setMessageReactions(
    meta: Message.MetaInfo,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
): Boolean = setMessageReactions(meta.chatId, meta.messageId, reactions, big)

public suspend fun TelegramBot.setMessageReaction(
    meta: Message.MetaInfo,
    reaction: Reaction? = null,
    big: Boolean = false
): Boolean = setMessageReaction(meta.chatId, meta.messageId, reaction, big)

public suspend fun TelegramBot.setMessageReactions(
    message: AccessibleMessage,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
): Boolean = setMessageReactions(message.metaInfo, reactions, big)

public suspend fun TelegramBot.setMessageReaction(
    message: AccessibleMessage,
    reaction: Reaction? = null,
    big: Boolean = false
): Boolean = setMessageReaction(message.metaInfo, reaction, big)

@JvmName("setMessageReactionsStrings")
public suspend fun TelegramBot.setMessageReactions(
    chatId: ChatIdentifier,
    messageId: MessageId,
    emojis: List<String>,
    big: Boolean = false
): Boolean = setMessageReactions(chatId, messageId, emojis.map { Reaction.Emoji(it) }, big)

public suspend fun TelegramBot.setMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    emoji: String?,
    big: Boolean = false
): Boolean = setMessageReaction(chatId, messageId, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
public suspend fun TelegramBot.setMessageReactions(
    chat: Chat,
    messageId: MessageId,
    emojis: List<String>,
    big: Boolean = false
): Boolean = setMessageReactions(chat, messageId, emojis.map { Reaction.Emoji(it) }, big)

public suspend fun TelegramBot.setMessageReaction(
    chat: Chat,
    messageId: MessageId,
    emoji: String?,
    big: Boolean = false
): Boolean = setMessageReaction(chat, messageId, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
public suspend fun TelegramBot.setMessageReactions(
    meta: Message.MetaInfo,
    emojis: List<String>,
    big: Boolean = false
): Boolean = setMessageReactions(meta, emojis.map { Reaction.Emoji(it) }, big)

public suspend fun TelegramBot.setMessageReaction(
    meta: Message.MetaInfo,
    emoji: String?,
    big: Boolean = false
): Boolean = setMessageReaction(meta, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
public suspend fun TelegramBot.setMessageReactions(
    message: AccessibleMessage,
    emojis: List<String>,
    big: Boolean = false
): Boolean = setMessageReactions(message, emojis.map { Reaction.Emoji(it) }, big)

public suspend fun TelegramBot.setMessageReaction(
    message: AccessibleMessage,
    emoji: String?,
    big: Boolean = false
): Boolean = setMessageReaction(message, emoji ?.let { Reaction.Emoji(it) }, big)
