package dev.inmo.tgbotapi.requests.answers.payments.abstracts

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.PreCheckoutQueryId
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

interface AnswerPreCheckoutQuery : SimpleRequest<Unit> {
    override fun method(): String = "answerPreCheckoutQuery"
    override val resultDeserializer: KSerializer<Unit>
        get() = UnitFromBooleanSerializer

    val preCheckoutQueryId: PreCheckoutQueryId
    val isOk: Boolean
}