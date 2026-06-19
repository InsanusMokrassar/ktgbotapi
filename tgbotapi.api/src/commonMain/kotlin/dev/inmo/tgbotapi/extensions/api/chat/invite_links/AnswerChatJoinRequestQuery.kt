package dev.inmo.tgbotapi.extensions.api.chat.invite_links

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.chat.invite_links.AnswerChatJoinRequestQuery
import dev.inmo.tgbotapi.requests.chat.invite_links.ChatJoinRequestQueryResult
import dev.inmo.tgbotapi.types.ChatJoinRequestQueryId
import dev.inmo.tgbotapi.types.chat.ChatJoinRequest
import dev.inmo.tgbotapi.types.update.ChatJoinRequestUpdate

public suspend fun TelegramBot.answerChatJoinRequestQuery(
    chatJoinRequestQueryId: ChatJoinRequestQueryId,
    result: ChatJoinRequestQueryResult
): Unit = execute(AnswerChatJoinRequestQuery(chatJoinRequestQueryId, result))

public suspend fun TelegramBot.answerChatJoinRequestQuery(
    chatJoinRequest: ChatJoinRequest,
    result: ChatJoinRequestQueryResult
): Unit = answerChatJoinRequestQuery(
    requireNotNull(chatJoinRequest.queryId) {
        "ChatJoinRequest.queryId is null, this request can't be answered with answerChatJoinRequestQuery"
    },
    result
)

public suspend fun TelegramBot.answerChatJoinRequestQuery(
    chatJoinRequestUpdate: ChatJoinRequestUpdate,
    result: ChatJoinRequestQueryResult
): Unit = answerChatJoinRequestQuery(chatJoinRequestUpdate.data, result)
