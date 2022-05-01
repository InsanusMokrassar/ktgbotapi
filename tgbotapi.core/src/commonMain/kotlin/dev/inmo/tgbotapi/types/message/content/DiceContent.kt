package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.requests.send.SendDice
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageIdentifier
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
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageIdentifier?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<ContentMessage<DiceContent>> = SendDice(
        chatId,
        dice.animationType,
        disableNotification,
        protectContent,
        replyToMessageId,
        allowSendingWithoutReply,
        replyMarkup
    )
}
