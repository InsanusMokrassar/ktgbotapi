package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.types.EphemeralMessageId
import dev.inmo.tgbotapi.types.IdChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.checklists.ChecklistTaskId
import dev.inmo.tgbotapi.types.ephemeralReplyParametersOrNull
import dev.inmo.tgbotapi.types.message.abstracts.ChatMessage
import dev.inmo.tgbotapi.types.polls.PollOptionPersistentId

/** An ephemeral reply references the regular message in the destination chat. A callback supplies its own chat. */
@PublishedApi
internal fun regularReplyParameters(
    sourceChatId: IdChatIdentifier,
    messageId: MessageId,
    destinationChatId: IdChatIdentifier?,
    receiverUserId: UserId?,
    allowSendingWithoutReply: Boolean?,
    checklistTaskId: ChecklistTaskId?,
    pollOptionId: PollOptionPersistentId?
): ReplyParameters.Chat {
    if (receiverUserId != null && destinationChatId != null) {
        require(sourceChatId.chatId == destinationChatId.chatId) { "An ephemeral message cannot reply to a message in another chat" }
    }
    return ReplyParameters(
        chatIdentifier = sourceChatId.takeIf { destinationChatId != null && receiverUserId == null },
        messageId = messageId,
        allowSendingWithoutReply = allowSendingWithoutReply,
        checklistTaskId = checklistTaskId,
        pollOptionId = pollOptionId
    )
}

/** Preserves the source's ephemeral message ID or quotes the source's regular message ID. */
@PublishedApi
internal fun ChatMessage.replyParametersForSend(
    ephemeralMessageId: EphemeralMessageId?,
    destinationChatId: IdChatIdentifier?,
    receiverUserId: UserId?,
    allowSendingWithoutReply: Boolean?,
    checklistTaskId: ChecklistTaskId?,
    pollOptionId: PollOptionPersistentId?,
    allowSendingWithoutRegularReply: Boolean? = allowSendingWithoutReply
): ReplyParameters = ephemeralReplyParametersOrNull(
    allowSendingWithoutReply = allowSendingWithoutReply,
    ephemeralMessageId = ephemeralMessageId,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId,
) ?: regularReplyParameters(
    chat.id, messageId, destinationChatId, receiverUserId,
    allowSendingWithoutReply = allowSendingWithoutRegularReply,
    checklistTaskId = checklistTaskId,
    pollOptionId = pollOptionId
)
