package dev.inmo.tgbotapi.extensions.api.edit.checklist

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.edit.checklist.EditMessageChecklist
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.businessConnectionIdField
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.InlineKeyboardMarkup
import dev.inmo.tgbotapi.types.checklistField
import dev.inmo.tgbotapi.types.checklists.Checklist
import dev.inmo.tgbotapi.types.message.abstracts.BusinessContentMessage
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.ChecklistContent
import dev.inmo.tgbotapi.types.messageIdField
import dev.inmo.tgbotapi.types.replyMarkupField
import kotlinx.serialization.SerialName

public suspend fun TelegramBot.editMessageChecklist(
    chatId: ChatIdentifier,
    messageId: MessageId,
    businessConnectionId: BusinessConnectionId,
    checklist: Checklist.Input,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<ChecklistContent> = execute(
    EditMessageChecklist(
        chatId = chatId,
        messageId = messageId,
        businessConnectionId = businessConnectionId,
        checklist = checklist,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.editMessageChecklist(
    message: BusinessContentMessage<ChecklistContent>,
    checklist: Checklist.Input,
    replyMarkup: InlineKeyboardMarkup? = null
): ContentMessage<ChecklistContent> = execute(
    EditMessageChecklist(
        chatId = message.chat.id,
        messageId = message.messageId,
        businessConnectionId = message.businessConnectionId,
        checklist = checklist,
        replyMarkup = replyMarkup
    )
)
