@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.extensions.utils.botCommandTextSourceOrNull
import dev.inmo.tgbotapi.extensions.utils.extensions.parseCommandsWithArgs
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature
import kotlinx.coroutines.Job

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
@PreviewFeature
suspend fun <BC : BehaviourContext> BC.unhandledCommand(
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>,
): Job =
    onText(
        CommonMessageFilter<TextContent> { message ->
            val content = message.content
            val textSources = content.textSources
            val sizeRequirement =
                if (requireOnlyCommandInMessage) {
                    textSources.size == 1
                } else {
                    true
                }
            sizeRequirement &&
                textSources.any {
                    val command = it.botCommandTextSourceOrNull() ?.command ?: return@any false
                    !triggersHolder.handleableCommandsHolder.isHandled(command)
                }
        }.let {
            initialFilter ?.times(it) ?: it
        },
        subcontextUpdatesFilter,
        markerFactory,
        additionalSubcontextInitialAction,
        scenarioReceiver,
    )

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
@PreviewFeature
suspend fun <BC : BehaviourContext> BC.onUnhandledCommand(
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>,
): Job =
    unhandledCommand(
        requireOnlyCommandInMessage,
        initialFilter,
        subcontextUpdatesFilter,
        markerFactory,
        additionalSubcontextInitialAction,
        scenarioReceiver,
    )

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
@PreviewFeature
suspend fun <BC : BehaviourContext> BC.unhandledCommandWithArgs(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Map<String, Array<String>>>,
) = onUnhandledCommand(
    requireOnlyCommandInMessage = false,
    initialFilter = initialFilter,
    subcontextUpdatesFilter = subcontextUpdatesFilter,
    markerFactory = markerFactory,
) {
    val args =
        it.parseCommandsWithArgs().let { commandsWithArgs ->
            commandsWithArgs
        }
    scenarioReceiver(it, args)
}

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
@PreviewFeature
suspend fun <BC : BehaviourContext> BC.onUnhandledCommandWithArgs(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, TextMessage>? = null,
    scenarioReceiver: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, TextMessage, Map<String, Array<String>>>,
): Job =
    unhandledCommandWithArgs(
        initialFilter,
        subcontextUpdatesFilter,
        markerFactory,
        additionalSubcontextInitialAction,
        scenarioReceiver,
    )
