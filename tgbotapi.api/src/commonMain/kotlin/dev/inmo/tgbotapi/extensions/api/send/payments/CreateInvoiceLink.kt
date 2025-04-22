package dev.inmo.tgbotapi.extensions.api.send.payments

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.payments.CreateInvoiceLink
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.payments.LabeledPrice
import dev.inmo.tgbotapi.types.payments.abstracts.Currency
import dev.inmo.tgbotapi.types.payments.abstracts.XTR
import korlibs.time.TimeSpan

public suspend fun TelegramBot.createInvoiceLink(
    title: String,
    description: String,
    payload: String,
    providerToken: String,
    currency: Currency,
    prices: List<LabeledPrice>,
    maxTipAmount: Int? = null,
    suggestedTipAmounts: List<Int>? = null,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
): String =
    execute(
        CreateInvoiceLink(
            title = title,
            description = description,
            payload = payload,
            providerToken = providerToken,
            currency = currency,
            prices = prices,
            subscriptionPeriod = null,
            maxTipAmount = maxTipAmount,
            suggestedTipAmounts = suggestedTipAmounts ?.sorted(),
            providerData = providerData,
            requireName = requireName,
            requirePhoneNumber = requirePhoneNumber,
            requireEmail = requireEmail,
            requireShippingAddress = requireShippingAddress,
            shouldSendPhoneNumberToProvider = shouldSendPhoneNumberToProvider,
            shouldSendEmailToProvider = shouldSendEmailToProvider,
            priceDependOnShipAddress = priceDependOnShipAddress,
        ),
    )

/**
 * For links witn XTR currency and using of Telegram Stars
 */
public suspend fun TelegramBot.createInvoiceLink(
    title: String,
    description: String,
    payload: String,
    prices: List<LabeledPrice>,
    businessConnectionId: BusinessConnectionId? = null,
    subscriptionPeriod: TimeSpan? = null,
    maxTipAmount: Int? = null,
    suggestedTipAmounts: List<Int>? = null,
    providerData: String? = null,
    requireName: Boolean = false,
    requirePhoneNumber: Boolean = false,
    requireEmail: Boolean = false,
    requireShippingAddress: Boolean = false,
    shouldSendPhoneNumberToProvider: Boolean = false,
    shouldSendEmailToProvider: Boolean = false,
    priceDependOnShipAddress: Boolean = false,
): String =
    execute(
        CreateInvoiceLink(
            title = title,
            description = description,
            payload = payload,
            providerToken = null,
            currency = Currency.XTR,
            businessConnectionId = businessConnectionId,
            prices = prices,
            subscriptionPeriod = subscriptionPeriod,
            maxTipAmount = maxTipAmount,
            suggestedTipAmounts = suggestedTipAmounts ?.sorted(),
            providerData = providerData,
            requireName = requireName,
            requirePhoneNumber = requirePhoneNumber,
            requireEmail = requireEmail,
            requireShippingAddress = requireShippingAddress,
            shouldSendPhoneNumberToProvider = shouldSendPhoneNumberToProvider,
            shouldSendEmailToProvider = shouldSendEmailToProvider,
            priceDependOnShipAddress = priceDependOnShipAddress,
        ),
    )
