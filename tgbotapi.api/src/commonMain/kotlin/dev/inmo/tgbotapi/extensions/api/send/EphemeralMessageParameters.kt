package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.types.CallbackQueryId
import dev.inmo.tgbotapi.types.EphemeralMessageParameters
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.polls.PollOptionPersistentId

@PublishedApi
internal fun ephemeralMessageParametersOrNull(
    receiverUserId: UserId?,
    callbackQueryId: CallbackQueryId?,
    replaceCallbackQueryMessage: Boolean?
): EphemeralMessageParameters? =
    if (receiverUserId == null && callbackQueryId == null) {
        null
    } else {
        EphemeralMessageParameters(receiverUserId, callbackQueryId, replaceCallbackQueryMessage)
    }

/** Telegram requires an ephemeral reply to reference a regular message in the destination chat. */
@PublishedApi
internal fun regularReplyParameters(
    sourceChatId: IdChatIdentifier,
    messageId: MessageId,
    destinationChatId: IdChatIdentifier?,
    ephemeralMessageParameters: EphemeralMessageParameters?,
    allowSendingWithoutReply: Boolean?,
    checklistTaskId: ChecklistTaskId?,
    pollOptionId: PollOptionPersistentId?
): ReplyParameters.Chat {
    if (ephemeralMessageParameters?.receiverUserId != null && destinationChatId != null) {
        require(sourceChatId.chatId == destinationChatId.chatId) { "An ephemeral message cannot reply to a message in another chat" }
    }
    return ReplyParameters(
        chatIdentifier = sourceChatId.takeIf { ephemeralMessageParameters == null },
        messageId = messageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
}
