package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.PassportData
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.coroutines.flow.toList

typealias PassportMessageMapper = suspend PassportMessage.() -> PassportData

@RiskFeature("Do not use this message directly, use waitPassportMessagesWith or waitAnyPassportMessages instead")
suspend fun <O> BehaviourContext.waitPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    mapper: suspend PassportMessage.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    it.asMessageUpdate() ?.data ?.asPassportMessage() ?.mapper().let(::listOfNotNull)
}.toList().toList()

suspend inline fun <reified T : EncryptedPassportElement> BehaviourContext.waitPassportMessagesWith(
    count: Int = 1,
    initRequest: Request<*>? = null,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: PassportMessageMapper? = null
) : List<PassportData> = waitPassportMessages(
    initRequest,
    errorFactory,
    count
) {
    if (passportData.data.any { it is T }) {
        if (filter == null) {
            passportData
        } else {
            filter(this)
        }
    } else {
        null
    }
}

suspend fun BehaviourContext.waitAnyPassportMessages(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: PassportMessageMapper? = null
) = waitPassportMessagesWith<EncryptedPassportElement>(count, initRequest, errorFactory, filter)
