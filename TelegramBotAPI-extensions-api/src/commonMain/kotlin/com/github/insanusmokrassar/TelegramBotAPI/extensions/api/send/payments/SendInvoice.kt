package com.github.insanusmokrassar.TelegramBotAPI.extensions.api.send.payments

import com.github.insanusmokrassar.TelegramBotAPI.bot.TelegramBot
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.payments.SendInvoice
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.InlineKeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.Message
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.LabeledPrice
import com.github.insanusmokrassar.TelegramBotAPI.types.payments.abstracts.Currency

suspend fun TelegramBot.sendInvoice(
    chatId: ChatId,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    startParameter: StartParameter,
    currency: Currency,
    prices: List<LabeledPrice>,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = execute(
    SendInvoice(chatId, title, description, payload, providerToken, startParameter, currency, prices, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, replyToMessageId, replyMarkup)
)

suspend fun TelegramBot.sendInvoice(
    user: CommonUser,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    startParameter: StartParameter,
    currency: Currency,
    prices: List<LabeledPrice>,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(user.id, title, description, payload, providerToken, startParameter, currency, prices, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, replyToMessageId, replyMarkup)

suspend inline fun TelegramBot.replyWithInvoice(
    to: Message,
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    startParameter: StartParameter,
    currency: Currency,
    prices: List<LabeledPrice>,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
    disableNotification: Boolean = false,
    replyMarkup: InlineKeyboardMarkup? = null
) = sendInvoice(to.chat.id, title, description, payload, providerToken, startParameter, currency, prices, providerData, requireName, requirePhoneNumber, requireEmail, requireShippingAddress, shouldSendPhoneNumberToProvider, shouldSendEmailToProvider, priceDependOnShipAddress, disableNotification, to.messageId, replyMarkup)
