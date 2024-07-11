package dev.inmo.tgbotapi.extensions.behaviour_builder.utils.extensions

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.CommonMessageFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling.onCommand
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.requests.send.SendTextMessage
import dev.inmo.tgbotapi.types.ReplyParameters
import dev.inmo.tgbotapi.types.message.ParseMode
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextMessage
import dev.inmo.tgbotapi.types.message.textsources.TextSource
import dev.inmo.tgbotapi.types.update.abstracts.Update

const val DefaultKTgBotAPIPrivacyCommand = "privacy"

/**
 * Adding default handler for `/privacy` command
 *
 * It is required handler in case you do not want your bot to be banned by telegram
 */
suspend fun <BC : BehaviourContext> BC.onCommandPrivacy(
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = null,
    markerFactory: MarkerFactory<in TextMessage, Any>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) {
    onCommand(
        DefaultKTgBotAPIPrivacyCommand,
        requireOnlyCommandInMessage,
        initialFilter,
        subcontextUpdatesFilter,
        markerFactory,
        scenarioReceiver
    )
}


/**
 * Adding default handler for `/privacy` command. It will send text message with [textSources] as text.
 *
 * It is required handler in case you do not want your bot to be banned by telegram
 */
suspend fun <BC : BehaviourContext> BC.onCommandPrivacy(
    textSources: List<TextSource>,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = null,
    markerFactory: MarkerFactory<in TextMessage, Any>? = null,
) = onCommandPrivacy(requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory) {
    execute(
        SendTextMessage(
            it.chat.id,
            textSources,
            replyParameters = ReplyParameters(it.metaInfo)
        )
    )
}


/**
 * Adding default handler for `/privacy` command. It will send text message with [text] as text and [parseMode] if passed.
 *
 * It is required handler in case you do not want your bot to be banned by telegram
 */
suspend fun <BC : BehaviourContext> BC.onCommandPrivacy(
    text: String,
    parseMode: ParseMode? = null,
    requireOnlyCommandInMessage: Boolean = true,
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = null,
    markerFactory: MarkerFactory<in TextMessage, Any>? = null,
) = onCommandPrivacy(requireOnlyCommandInMessage, initialFilter, subcontextUpdatesFilter, markerFactory) {
    execute(
        SendTextMessage(
            it.chat.id,
            text,
            parseMode,
            replyParameters = ReplyParameters(it.metaInfo)
        )
    )
}
