package dev.inmo.tgbotapi.requests.answers.payments.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.PreCheckoutQueryId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface AnswerPreCheckoutQuery : SimpleRequest<Boolean> {
    override fun method(): String = "answerPreCheckoutQuery"
    override val resultDeserializer: KSerializer<Boolean>
        get() = Boolean.serializer()

    val preCheckoutQueryId: PreCheckoutQueryId
    val isOk: Boolean
}