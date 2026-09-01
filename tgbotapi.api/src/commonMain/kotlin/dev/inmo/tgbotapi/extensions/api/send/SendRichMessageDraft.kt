package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendRichMessageDraft
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.rich.InputRichMessage

/**
 * @param chatId Numeric target chat identifier. Identifiers carrying a thread supply the default [threadId].
 */
public suspend fun TelegramBot.sendRichMessageDraft(
    chatId: IdChatIdentifier,
    draftId: Long,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = chatId.threadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = execute(
    SendRichMessageDraft(
        chatId = chatId,
        draftId = draftId,
        richMessage = richMessage,
        threadId = threadId,
        canStop = canStop,
        keepOnStop = keepOnStop
    )
)
