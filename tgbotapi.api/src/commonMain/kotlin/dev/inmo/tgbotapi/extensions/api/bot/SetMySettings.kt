package dev.inmo.tgbotapi.extensions.api.bot

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.bot.SetMySettings
import dev.inmo.tgbotapi.types.CallbackQueryMode
import dev.inmo.tgbotapi.types.InlineFeedbackShareDenominator

/** Applies the supplied bot settings. Omitted parameters retain their current values. */
public suspend fun TelegramBot.setMySettings(
    supportsInlineQueries: Boolean? = null,
    inlineQueryPlaceholder: String? = null,
    needsInlineQueryLocation: Boolean? = null,
    inlineFeedbackShareDenominator: InlineFeedbackShareDenominator? = null,
    canJoinGroups: Boolean? = null,
    canReadAllGroupMessages: Boolean? = null,
    canConnectToBusiness: Boolean? = null,
    canManageBots: Boolean? = null,
    supportsGuestQueries: Boolean? = null,
    supportsJoinRequestQueries: Boolean? = null,
    hasTopicsEnabled: Boolean? = null,
    allowsUsersToCreateTopics: Boolean? = null,
    hasTopicSidebar: Boolean? = null,
    canCommunicateWithBots: Boolean? = null,
    callbackQueryMode: CallbackQueryMode? = null
): Unit = execute(SetMySettings(
    supportsInlineQueries = supportsInlineQueries,
    inlineQueryPlaceholder = inlineQueryPlaceholder,
    needsInlineQueryLocation = needsInlineQueryLocation,
    inlineFeedbackShareDenominator = inlineFeedbackShareDenominator,
    canJoinGroups = canJoinGroups,
    canReadAllGroupMessages = canReadAllGroupMessages,
    canConnectToBusiness = canConnectToBusiness,
    canManageBots = canManageBots,
    supportsGuestQueries = supportsGuestQueries,
    supportsJoinRequestQueries = supportsJoinRequestQueries,
    hasTopicsEnabled = hasTopicsEnabled,
    allowsUsersToCreateTopics = allowsUsersToCreateTopics,
    hasTopicSidebar = hasTopicSidebar,
    canCommunicateWithBots = canCommunicateWithBots,
    callbackQueryMode = callbackQueryMode
))
