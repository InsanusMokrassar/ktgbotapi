package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.messageUpdateOrNull
import dev.inmo.tgbotapi.extensions.utils.passportMessageOrNull
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.lowLevelRiskFeatureMessage
import kotlinx.coroutines.flow.Flow

typealias PassportMessageMapper = suspend PassportMessage.() -> PassportData

@RiskFeature(lowLevelRiskFeatureMessage)
inline fun <reified O : EncryptedPassportElement> BehaviourContext.waitPassportMessagesWith(
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null }
): Flow<O> = expectFlow(
    initRequest,
    errorFactory
) {
    it.messageUpdateOrNull() ?.data ?.passportMessageOrNull() ?.passportData ?.data ?.filterIsInstance<O>() ?: emptyList()
}

fun BehaviourContext.waitAnyPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null }
) = waitPassportMessagesWith<EncryptedPassportElement>(initRequest, errorFactory)
