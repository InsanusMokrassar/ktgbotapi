package com.github.insanusmokrassar.TelegramBotAPI.requests.send.polls

import com.github.insanusmokrassar.TelegramBotAPI.bot.RequestsExecutor
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.ReplyingMarkupSendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.requests.send.abstracts.SendMessageRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.*
import com.github.insanusmokrassar.TelegramBotAPI.types.buttons.KeyboardMarkup
import com.github.insanusmokrassar.TelegramBotAPI.types.chat.abstracts.Chat
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.ContentMessage
import com.github.insanusmokrassar.TelegramBotAPI.types.message.abstracts.TelegramBotAPIMessageDeserializationStrategyClass
import com.github.insanusmokrassar.TelegramBotAPI.types.message.content.PollContent
import com.github.insanusmokrassar.TelegramBotAPI.types.polls.*
import kotlinx.serialization.*

private val commonResultDeserializer: DeserializationStrategy<ContentMessage<PollContent>> = TelegramBotAPIMessageDeserializationStrategyClass()

private fun checkPollInfo(
    question: String,
    options: List<String>
) {
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

fun SendPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = SendRegularPoll(
    chatId,
    question,
    options,
    isAnonymous,
    isClosed,
    disableNotification = disableNotification,
    replyToMessageId = replyToMessageId,
    replyMarkup = replyMarkup
)

/**
 * @return [SendPoll] in case when all is right. It can return [SendRegularPoll] for [QuizPoll] in case if
 * [QuizPoll.correctOptionId] equal to null
 */
fun Poll.createRequest(
    chatId: ChatIdentifier,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = when (this) {
    is RegularPoll -> SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        allowMultipleAnswers,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
    is QuizPoll -> correctOptionId ?.let { correctOptionId ->
        SendQuizPoll(
            chatId,
            question,
            options.map { it.text },
            correctOptionId,
            isAnonymous,
            isClosed,
            disableNotification,
            replyToMessageId,
            replyMarkup
        )
    } ?: SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        false,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
    is UnknownPollType -> SendRegularPoll(
        chatId,
        question,
        options.map { it.text },
        isAnonymous,
        isClosed,
        false,
        disableNotification,
        replyToMessageId,
        replyMarkup
    )
}

sealed class SendPoll : SendMessageRequest<ContentMessage<PollContent>>,
    ReplyingMarkupSendMessageRequest<ContentMessage<PollContent>> {
    abstract val question: String
    abstract val options: List<String>
    abstract val isAnonymous: Boolean
    abstract val isClosed: Boolean
    abstract val type: String

    override fun method(): String = "sendPoll"
    override val resultDeserializer: DeserializationStrategy<ContentMessage<PollContent>>
        get() = commonResultDeserializer
}

@Serializable
data class SendRegularPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(allowsMultipleAnswersField)
    val allowMultipleAnswers: Boolean = false,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = regularPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        checkPollInfo(question, options)
    }
}

@Serializable
data class SendQuizPoll(
    @SerialName(chatIdField)
    override val chatId: ChatIdentifier,
    @SerialName(questionField)
    override val question: String,
    @SerialName(optionsField)
    override val options: List<String>,
    @SerialName(correctOptionIdField)
    val correctOptionId: Int,
    @SerialName(isAnonymousField)
    override val isAnonymous: Boolean = true,
    @SerialName(isClosedField)
    override val isClosed: Boolean = false,
    @SerialName(disableNotificationField)
    override val disableNotification: Boolean = false,
    @SerialName(replyToMessageIdField)
    override val replyToMessageId: MessageIdentifier? = null,
    @SerialName(replyMarkupField)
    override val replyMarkup: KeyboardMarkup? = null
) : SendPoll() {
    override val type: String = quizPollType
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    init {
        checkPollInfo(question, options)
        val correctOptionIdRange = 0 .. options.size
        if (correctOptionId !in correctOptionIdRange) {
            throw IllegalArgumentException("Correct option id must be in range of $correctOptionIdRange, but actual " +
                "value is $correctOptionId")
        }
    }
}


suspend fun RequestsExecutor.sendRegularPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
    )
)
suspend fun RequestsExecutor.sendRegularPoll(
    chatId: ChatIdentifier,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendRegularPoll(
        chatId, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendRegularPoll(
    chat: Chat,
    question: String,
    options: List<String>,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    allowMultipleAnswers: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(
    chat.id, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
)

suspend fun RequestsExecutor.sendRegularPoll(
    chat: Chat,
    poll: RegularPoll,
    isClosed: Boolean = false,
    question: String = poll.question,
    options: List<String> = poll.options.map { it.text },
    isAnonymous: Boolean = poll.isAnonymous,
    allowMultipleAnswers: Boolean = poll.allowMultipleAnswers,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendRegularPoll(
    chat.id, question, options, isAnonymous, isClosed, allowMultipleAnswers, disableNotification, replyToMessageId, replyMarkup
)


suspend fun RequestsExecutor.sendQuizPoll(
    chatId: ChatIdentifier,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = execute(
    SendQuizPoll(
        chatId, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
    )
)

suspend fun RequestsExecutor.sendQuizPoll(
    chat: Chat,
    question: String,
    options: List<String>,
    correctOptionId: Int,
    isAnonymous: Boolean = true,
    isClosed: Boolean = false,
    disableNotification: Boolean = false,
    replyToMessageId: MessageIdentifier? = null,
    replyMarkup: KeyboardMarkup? = null
) = sendQuizPoll(
    chat.id, question, options, correctOptionId, isAnonymous, isClosed, disableNotification, replyToMessageId, replyMarkup
)
