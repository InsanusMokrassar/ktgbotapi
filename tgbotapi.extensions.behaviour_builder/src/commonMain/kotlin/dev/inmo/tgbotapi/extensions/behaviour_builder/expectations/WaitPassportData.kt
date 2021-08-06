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
suspend fun <O> BehaviourContext.waitPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PassportMessage>? = null,
    mapper: suspend PassportMessage.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val data = it.asMessageUpdate() ?.data ?.asPassportMessage() ?: return@expectFlow emptyList()
    if (filter == null || filter(data)) {
        data.mapper().let(::listOfNotNull)
    } else {
        emptyList()
    }
}.toList().toList()

suspend inline fun <reified T : EncryptedPassportElement> BehaviourContext.waitPassportMessagesWith(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: SimpleFilter<PassportMessage>? = null,
    noinline mapper: PassportMessageMapper? = null
) : List<PassportData> = waitPassportMessages(
    initRequest,
    errorFactory,
    count,
    filter
) {
    if (passportData.data.any { it is T }) {
        if (mapper == null) {
            passportData
        } else {
            mapper(this)
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitAnyPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<PassportMessage>? = null,
    mapper: PassportMessageMapper? = null
) = waitPassportMessagesWith<EncryptedPassportElement>(count, initRequest, errorFactory, filter, mapper)
