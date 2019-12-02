package com.github.insanusmokrassar.TelegramBotAPI.requests.answers.payments.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.requests.abstracts.SimpleRequest
import com.github.insanusmokrassar.TelegramBotAPI.types.PreCheckoutQueryId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.internal.BooleanSerializer
import kotlinx.serialization.serializer

interface AnswerPreCheckoutQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerPreCheckoutQuery"
    override val resultDeserializer: KSerializer<Boolean>
        get() = BooleanSerializer

    val preCheckoutQueryId: PreCheckoutQueryId
    val isOk: Boolean
}