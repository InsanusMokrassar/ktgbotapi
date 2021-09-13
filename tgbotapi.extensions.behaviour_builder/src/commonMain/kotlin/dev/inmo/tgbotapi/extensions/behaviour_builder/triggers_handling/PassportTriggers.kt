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

suspend fun BehaviourContext.onPassportMessage(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: SimpleFilter<PassportMessage>? = null,
    markerFactory: MarkerFactory<in PassportMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = onPassportMessageWith<EncryptedPassportElement>(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) MessageFilterByChat else null,
    markerFactory,
    scenarioReceiver
)

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

