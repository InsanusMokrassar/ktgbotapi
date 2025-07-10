package dev.inmo.tgbotapi.extensions.api.send

import dev.inmo.tgbotapi.bot.TelegramBot
import dev.inmo.tgbotapi.requests.send.SendChecklist
import dev.inmo.tgbotapi.types.BusinessChatId
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.EffectId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.checklists.Checklist

public suspend fun TelegramBot.sendChecklist(
    chatId: ChatIdentifier,
    checklist: Checklist.Input,
    businessConnectionId: BusinessConnectionId,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendChecklist(
        chatId = chatId,
        checklist = checklist,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)

public suspend fun TelegramBot.sendChecklist(
    chatId: BusinessChatId,
    checklist: Checklist.Input,
    disableNotification: Boolean = false,
    protectContent: Boolean = false,
    effectId: EffectId? = null,
    replyParameters: ReplyParameters? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendChecklist(
        chatId = chatId,
        checklist = checklist,
        businessConnectionId = chatId.businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = replyParameters,
        replyMarkup = replyMarkup
    )
)
