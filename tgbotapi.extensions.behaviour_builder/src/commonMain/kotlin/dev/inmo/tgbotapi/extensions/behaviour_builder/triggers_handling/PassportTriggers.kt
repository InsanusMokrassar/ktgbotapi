package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asPassportMessage
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <reified T : EncryptedPassportElement> BehaviourContext.onPassportMessageWith(
    noinline initialFilter: SimpleFilter<PassportMessage>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, PassportMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in PassportMessage, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    (it.asMessageUpdate() ?.data ?.asPassportMessage() ?.takeIf { it.passportData.data.any { it is T } }) ?.let(::listOfNotNull)
}


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage].
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContextAndTwoTypesReceiver] function to create your own.
 * Use [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.plus] or [dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times]
 * to combinate several filters
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPassportMessage(
    initialFilter: SimpleFilter<PassportMessage>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, PassportMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in PassportMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = onPassportMessageWith<EncryptedPassportElement>(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

