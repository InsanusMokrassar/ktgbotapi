package com.github.insanusmokrassar.TelegramBotAPI.requests.send

import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.message.RawMessage
import kotlinx.serialization.*

@Serializable
data class SendPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    val question: String,
    @SerialName(optionsField)
    val options: List<String>,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendMessageRequest<RawMessage>,
    ReplyingMarkupSendMessageRequest<RawMessage> {

    init {
        if (question.length !in pollQuestionTextLength) {
            throw IllegalArgumentException("The length of questions for polls must be in $pollQuestionTextLength range, but was ${question.length}")
        }
        options.forEach {
            if (it.length !in pollOptionTextLength) {
                throw IllegalArgumentException("The length of question option text for polls must be in $pollOptionTextLength range, but was ${it.length}")
            }
        }
        if (options.size !in pollOptionsLimit) {
            throw IllegalArgumentException("The amount of question options for polls must be in $pollOptionsLimit range, but was ${options.size}")
        }
    }

    override fun method(): String = "sendPoll"
    override fun resultSerializer(): KSerializer<RawMessage> = RawMessage.serializer()
}