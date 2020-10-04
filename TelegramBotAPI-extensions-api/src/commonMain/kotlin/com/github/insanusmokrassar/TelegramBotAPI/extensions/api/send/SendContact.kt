package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.SendContact
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat

suspend fun TelegramBot.sendContact(
    chatId: ChatIdentifier,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendContact(
        chatId, phoneNumber, firstName, lastName, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun TelegramBot.sendContact(
    chatId: ChatIdentifier,
    contact: Contact,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendContact(
        chatId, contact, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun TelegramBot.sendContact(
    chat: Chat,
    phoneNumber: String,
    firstName: String,
    lastName: String? = null,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    chat.id, phoneNumber, firstName, lastName, disableNotification, replyToMessageId, replyMarkup
)

suspend fun TelegramBot.sendContact(
    chat: Chat,
    contact: Contact,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendContact(
    chat.id, contact, disableNotification, replyToMessageId, replyMarkup
)
