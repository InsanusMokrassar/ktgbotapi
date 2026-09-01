package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendRichMessageDraft
import dev.inmo.tgbotapi.types.DirectMessageThreadId
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.rich.InputRichMessage

/**
 * @param chatId Numeric target chat identifier.
 * @param directMessageThreadId Direct messages topic identifier. Defaults from [chatId].
 */
public suspend fun TelegramBot.sendRichMessageDraft(
    chatId: IdChatIdentifier,
    draftId: Long,
    richMessage: InputRichMessage,
    directMessageThreadId: DirectMessageThreadId? = chatId.directMessageThreadId,
    canStop: Boolean? = null,
    keepOnStop: Boolean? = null
): Unit = execute(
    SendRichMessageDraft(
        chatId = chatId,
        draftId = draftId,
        richMessage = richMessage,
        directMessageThreadId = directMessageThreadId,
        canStop = canStop,
        keepOnStop = keepOnStop
    )
)
