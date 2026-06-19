package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.SendChatJoinRequestWebApp
import dev.inmo.tgbotapi.types.ChatJoinRequestQueryId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public suspend fun TelegramBot.sendChatJoinRequestWebApp(
    chatJoinRequestQueryId: ChatJoinRequestQueryId,
    webAppUrl: String
): Unit = execute(SendChatJoinRequestWebApp(chatJoinRequestQueryId, webAppUrl))

public suspend fun TelegramBot.sendChatJoinRequestWebApp(
    chatJoinRequest: ChatJoinRequest,
    webAppUrl: String
): Unit = sendChatJoinRequestWebApp(
    requireNotNull(chatJoinRequest.queryId) {
        "ChatJoinRequest.queryId is null, this request can't be processed with sendChatJoinRequestWebApp"
    },
    webAppUrl
)

public suspend fun TelegramBot.sendChatJoinRequestWebApp(
    chatJoinRequestUpdate: ChatJoinRequestUpdate,
    webAppUrl: String
): Unit = sendChatJoinRequestWebApp(chatJoinRequestUpdate.data, webAppUrl)
