package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SetMessageReactions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.reactions.Reaction
import kotlin.js.JsName
import kotlin.jvm.JvmName

suspend fun TelegramBot.setMessageReactions(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
) = execute(
    SetMessageReactions(chatId, messageId, reactions, big)
)

suspend fun TelegramBot.setMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reaction: Reaction? = null,
    big: Boolean = false
) = setMessageReactions(chatId, messageId, listOfNotNull(reaction), big)

suspend fun TelegramBot.setMessageReactions(
    chat: Chat,
    messageId: MessageId,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
) = setMessageReactions(chat.id, messageId, reactions, big)

suspend fun TelegramBot.setMessageReaction(
    chat: Chat,
    messageId: MessageId,
    reaction: Reaction? = null,
    big: Boolean = false
) = setMessageReaction(chat.id, messageId, reaction, big)

suspend fun TelegramBot.setMessageReactions(
    meta: Message.MetaInfo,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
) = setMessageReactions(meta.chatId, meta.messageId, reactions, big)

suspend fun TelegramBot.setMessageReaction(
    meta: Message.MetaInfo,
    reaction: Reaction? = null,
    big: Boolean = false
) = setMessageReaction(meta.chatId, meta.messageId, reaction, big)

suspend fun TelegramBot.setMessageReactions(
    message: AccessibleMessage,
    reactions: List<Reaction> = emptyList(),
    big: Boolean = false
) = setMessageReactions(message.metaInfo, reactions, big)

suspend fun TelegramBot.setMessageReaction(
    message: AccessibleMessage,
    reaction: Reaction? = null,
    big: Boolean = false
) = setMessageReaction(message.metaInfo, reaction, big)

@JvmName("setMessageReactionsStrings")
suspend fun TelegramBot.setMessageReactions(
    chatId: ChatIdentifier,
    messageId: MessageId,
    emojis: List<String>,
    big: Boolean = false
) = setMessageReactions(chatId, messageId, emojis.map { Reaction.Emoji(it) }, big)

suspend fun TelegramBot.setMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    emoji: String?,
    big: Boolean = false
) = setMessageReaction(chatId, messageId, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
suspend fun TelegramBot.setMessageReactions(
    chat: Chat,
    messageId: MessageId,
    emojis: List<String>,
    big: Boolean = false
) = setMessageReactions(chat, messageId, emojis.map { Reaction.Emoji(it) }, big)

suspend fun TelegramBot.setMessageReaction(
    chat: Chat,
    messageId: MessageId,
    emoji: String?,
    big: Boolean = false
) = setMessageReaction(chat, messageId, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
suspend fun TelegramBot.setMessageReactions(
    meta: Message.MetaInfo,
    emojis: List<String>,
    big: Boolean = false
) = setMessageReactions(meta, emojis.map { Reaction.Emoji(it) }, big)

suspend fun TelegramBot.setMessageReaction(
    meta: Message.MetaInfo,
    emoji: String?,
    big: Boolean = false
) = setMessageReaction(meta, emoji ?.let { Reaction.Emoji(it) }, big)

@JvmName("setMessageReactionsStrings")
suspend fun TelegramBot.setMessageReactions(
    message: AccessibleMessage,
    emojis: List<String>,
    big: Boolean = false
) = setMessageReactions(message, emojis.map { Reaction.Emoji(it) }, big)

suspend fun TelegramBot.setMessageReaction(
    message: AccessibleMessage,
    emoji: String?,
    big: Boolean = false
) = setMessageReaction(message, emoji ?.let { Reaction.Emoji(it) }, big)
