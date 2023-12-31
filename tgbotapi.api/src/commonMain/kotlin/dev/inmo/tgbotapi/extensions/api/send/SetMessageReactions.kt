package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendAction
import dev.inmo.tgbotapi.requests.send.SetMessageReactions
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.actions.*
import dev.inmo.tgbotapi.types.chat.Chat
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.reactions.Reaction
import dev.inmo.tgbotapi.types.threadId

suspend fun TelegramBot.setMessageReactions(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reactions: List<Reaction>,
    big: Boolean = false
) = execute(
    SetMessageReactions(chatId, messageId, reactions, big)
)

suspend fun TelegramBot.setMessageReaction(
    chatId: ChatIdentifier,
    messageId: MessageId,
    reaction: Reaction?,
    big: Boolean = false
) = setMessageReactions(chatId, messageId, listOfNotNull(reaction), big)

suspend fun TelegramBot.setMessageReactions(
    chat: Chat,
    messageId: MessageId,
    reactions: List<Reaction>,
    big: Boolean = false
) = setMessageReactions(chat.id, messageId, reactions, big)

suspend fun TelegramBot.setMessageReaction(
    chat: Chat,
    messageId: MessageId,
    reaction: Reaction?,
    big: Boolean = false
) = setMessageReaction(chat.id, messageId, reaction, big)

suspend fun TelegramBot.setMessageReactions(
    message: Message,
    reactions: List<Reaction>,
    big: Boolean = false
) = setMessageReactions(message.chat, message.messageId, reactions, big)

suspend fun TelegramBot.setMessageReaction(
    message: Message,
    reaction: Reaction?,
    big: Boolean = false
) = setMessageReaction(message.chat, message.messageId, reaction, big)
