package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendDice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import kotlinx.serialization.Serializable

@Serializable
data class DiceContent(
    val dice: Dice
) : MessageContent {
    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyParameters: ReplyParameters?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<DiceContent>> = SendDice(
        chatId,
        dice.animationType,
        messageThreadId,
        disableNotification,
        protectContent,
        replyParameters,
        replyMarkup
    )
}
