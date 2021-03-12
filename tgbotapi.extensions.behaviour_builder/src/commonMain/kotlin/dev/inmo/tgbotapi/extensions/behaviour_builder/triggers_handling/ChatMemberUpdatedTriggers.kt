package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.asChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.ChatMemberUpdated
import dev.inmo.tgbotapi.types.update.CommonChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.MyChatMemberUpdatedUpdate
import dev.inmo.tgbotapi.types.update.abstracts.ChatMemberUpdatedUpdate

internal suspend inline fun <reified U : ChatMemberUpdatedUpdate> BehaviourContext.onChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    noinline additionalFilter: (suspend (ChatMemberUpdated) -> Boolean)? = null,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = flowsUpdatesFilter.expectFlow(bot) {
    (it as? U) ?.data ?.let { chatMemberUpdated ->
        if (additionalFilter == null || additionalFilter(chatMemberUpdated)) chatMemberUpdated else null
    }.let(::listOfNotNull)
}.subscribeSafelyWithoutExceptions(scope) { triggerChatMemberUpdated ->
    doInSubContextWithUpdatesFilter(
        updatesFilter = if (includeFilterByChatInBehaviourSubContext) {
            { it.sourceChat() ?.id ?.chatId == triggerChatMemberUpdated.chat.id.chatId }
        } else {
            null
        }
    ) {
        scenarioReceiver(triggerChatMemberUpdated)
    }
}

suspend fun BehaviourContext.onChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatMemberUpdated) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdated<ChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    scenarioReceiver
)

suspend fun BehaviourContext.onCommonChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatMemberUpdated) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdated<CommonChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    scenarioReceiver
)

suspend fun BehaviourContext.onMyChatMemberUpdated(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: (suspend (ChatMemberUpdated) -> Boolean)? = null,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, ChatMemberUpdated>
) = onChatMemberUpdated<MyChatMemberUpdatedUpdate>(
    includeFilterByChatInBehaviourSubContext,
    additionalFilter,
    scenarioReceiver
)
