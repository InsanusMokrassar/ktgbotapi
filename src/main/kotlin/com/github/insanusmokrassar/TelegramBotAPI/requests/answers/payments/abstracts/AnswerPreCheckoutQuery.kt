package com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.PreCheckoutQueryId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

interface AnswerPreCheckoutQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerPreCheckoutQuery"
    override fun resultDeserializer(): KSerializer<Boolean> = Boolean.serializer()

    val preCheckoutQueryId: PreCheckoutQueryId
    val isOk: Boolean
}