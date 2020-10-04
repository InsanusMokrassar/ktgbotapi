package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendDice
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.dice.DiceAnimationType
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message

suspend fun TelegramBot.sendDice(
    chatId: ChatIdentifier,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDice(chatId, animationType, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun TelegramBot.sendDice(
    chat: Chat,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(chat.id, animationType, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.reply(
    to: Message,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(to.chat, animationType, disableNotification, to.messageId, replyMarkup)
