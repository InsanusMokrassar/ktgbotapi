package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.asPassportMessage
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.encrypted.abstracts.EncryptedPassportElement

internal suspend inline fun <reified T : EncryptedPassportElement> BehaviourContext.onPassportMessageWith(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: SimpleFilter<PassportMessage>? = null,
    markerFactory: MarkerFactory<in PassportMessage, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asMessageUpdate() ?.data ?.asPassportMessage() ?.let { message ->
        if (message.passportData.data.any { it is T }) {
            if (additionalFilter == null || additionalFilter(message)) message else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptionsAsync(
    scope,
    markerFactory::invoke
) { triggerMessage ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerMessage.chat.id.chatId }
        } else null,
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerMessage)
    }
}

suspend fun BehaviourContext.onPassportMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: SimpleFilter<PassportMessage>? = null,
    markerFactory: MarkerFactory<in PassportMessage, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = onPassportMessageWith<EncryptedPassportElement>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    markerFactory,
    scenarioReceiver
)

