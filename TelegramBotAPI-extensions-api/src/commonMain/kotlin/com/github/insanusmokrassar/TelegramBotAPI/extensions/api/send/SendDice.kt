package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendDice
import com.github.insanusmokrassar.TelegramBotAPI.types.ChatIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.MessageIdentifier
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.dice.DiceAnimationType

suspend fun RequestsExecutor.sendDice(
    chatId: ChatIdentifier,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendDice(chatId, animationType, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun RequestsExecutor.sendDice(
    chat: Chat,
    animationType: DiceAnimationType? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendDice(chat.id, animationType, disableNotification, replyToMessageId, replyMarkup)
