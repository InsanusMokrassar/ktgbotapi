package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asPassportMessage
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.flow.toList

typealias PassportMessageMapper = suspend PassportMessage.() -> PassportData

@RiskFeature("Do not use this message directly, use waitPassportMessagesWith or waitAnyPassportMessages instead")
suspend inline fun <reified O : EncryptedPassportElement> BehaviourContext.waitPassportMessagesWith(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<PassportMessage>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asMessageUpdate() ?.data ?.asPassportMessage() ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        data.passportData.data.filterIsInstance<O>()
    } else {
        emptyList()
    }
}.toList().toList()

suspend fun BehaviourContext.waitAnyPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PassportMessage>? = null
) = waitPassportMessagesWith<EncryptedPassportElement>(count, initRequest, errorFactory, filter)
