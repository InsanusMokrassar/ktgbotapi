package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendRichMessageDraft
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.rich.InputRichMessage

public suspend fun TelegramBot.sendRichMessageDraft(
    chatId: ChatId,
    draftId: Long,
    richMessage: InputRichMessage,
    threadId: MessageThreadId? = null
): Unit = execute(
    SendRichMessageDraft(
        chatId = chatId,
        draftId = draftId,
        richMessage = richMessage,
        threadId = threadId
    )
)
