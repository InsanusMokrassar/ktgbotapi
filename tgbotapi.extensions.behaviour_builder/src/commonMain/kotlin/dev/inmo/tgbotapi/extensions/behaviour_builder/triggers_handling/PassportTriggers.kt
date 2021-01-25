package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.message.PassportMessage
import dev.inmo.tgbotapi.types.passport.encrypted_data.abstracts.EncryptedPassportElement

suspend inline fun <reified T : EncryptedPassportElement> BehaviourContext.onPassportMessageWith(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (PassportMessage) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asMessageUpdate() ?.data ?.asPassportMessage() ?.let { message ->
        if (message.passportData.data.any { it is T }) {
            if (additionalFilter == null || additionalFilter(message)) message else null
        } else {
            null
        }
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptions(scope) { triggerMessage ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerMessage.chat.id.chatId }
        } else null
    ) {
        scenarioReceiver(triggerMessage)
    }
}

suspend fun BehaviourContext.onPassportMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (PassportMessage) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, PassportMessage>
) = onPassportMessageWith<EncryptedPassportElement>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    scenarioReceiver
)

