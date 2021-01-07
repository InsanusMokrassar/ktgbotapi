package dev.inmo.tgbotapi.extensions.steps.triggers_handling


import dev.inmo.micro_utils.coroutines.safelyWithoutExceptions
import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.steps.Scenario
import dev.inmo.tgbotapi.extensions.steps.ScenarioAndTypeReceiver
import dev.inmo.tgbotapi.extensions.steps.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.abstracts.ContentMessage
import dev.inmo.tgbotapi.types.message.content.ContactContent
import dev.inmo.tgbotapi.types.message.content.abstracts.MessageContent
import dev.inmo.tgbotapi.updateshandlers.FlowsUpdatesFilter
import kotlinx.coroutines.flow.filter
import dev.inmo.tgbotapi.types.message.content.DiceContent
import dev.inmo.tgbotapi.types.message.content.GameContent
import dev.inmo.tgbotapi.types.message.content.LocationContent
import dev.inmo.tgbotapi.types.message.content.PollContent
import dev.inmo.tgbotapi.types.message.content.VenueContent
import dev.inmo.tgbotapi.types.message.content.abstracts.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.abstracts.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaCollectionContent
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaContent
import dev.inmo.tgbotapi.types.message.content.abstracts.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.abstracts.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.AnimationContent
import dev.inmo.tgbotapi.types.message.content.media.AudioContent
import dev.inmo.tgbotapi.types.message.content.media.DocumentContent
import dev.inmo.tgbotapi.types.message.content.media.PhotoContent
import dev.inmo.tgbotapi.types.message.content.media.StickerContent
import dev.inmo.tgbotapi.types.message.content.media.VideoContent
import dev.inmo.tgbotapi.types.message.content.media.VideoNoteContent
import dev.inmo.tgbotapi.types.message.content.media.VoiceContent
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent


internal suspend inline fun <reified T : MessageContent> Scenario.onContent(
    includeFilterByChatInSubScenario: Boolean = true,
    noinline additionalFilter: (suspend (ContentMessage<T>) -> Boolean)? = null,
    noinline scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<T>>
) = flowsUpdatesFilter.expectFlow(bot) {
    it.asMessageUpdate() ?.data ?.asContentMessage() ?.let { message ->
        if (message.content is T) {
            val adaptedMessage = message as ContentMessage<T>
            if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
        } else {
            null
        }
    }
}.subscribeSafelyWithoutExceptions(scope) { triggerMessage ->
    val (jobToCancel, scenario) = if (includeFilterByChatInSubScenario) {
        val subFilter = FlowsUpdatesFilter()
        val subScenario = copy(flowsUpdatesFilter = subFilter)

        flowsUpdatesFilter.allUpdatesFlow.filter {
            it.asMessageUpdate() ?.data ?.let { it.chat.id.chatId == triggerMessage.chat.id.chatId } == true
        }.subscribeSafelyWithoutExceptions(scope, subFilter.asUpdateReceiver) to subScenario
    } else {
        null to this
    }
    safelyWithoutExceptions { scenario.scenarioReceiver(triggerMessage) }
    jobToCancel ?.cancel()
}

suspend fun Scenario.onContact(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<ContactContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<ContactContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onDice(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<DiceContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<DiceContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onGame(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<GameContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<GameContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onLocation(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<LocationContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<LocationContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onPoll(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<PollContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<PollContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onText(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<TextContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<TextContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onVenue(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<VenueContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<VenueContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onAudioMediaGroup(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<AudioMediaGroupContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<AudioMediaGroupContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onDocumentMediaGroup(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<DocumentMediaGroupContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<DocumentMediaGroupContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMediaCollection(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<MediaCollectionContent<TelegramMediaFile>>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<MediaCollectionContent<TelegramMediaFile>>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMedia(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<MediaContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<MediaContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onMediaGroup(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<MediaGroupContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<MediaGroupContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onVisualMediaGroup(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<VisualMediaGroupContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<VisualMediaGroupContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onAnimation(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<AnimationContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<AnimationContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onAudio(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<AudioContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<AudioContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onDocument(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<DocumentContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<DocumentContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onPhoto(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<PhotoContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<PhotoContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onSticker(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<StickerContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<StickerContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onVideo(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<VideoContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<VideoContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onVideoNote(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<VideoNoteContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<VideoNoteContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onVoice(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<VoiceContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<VoiceContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
suspend fun Scenario.onInvoice(
    includeFilterByChatInSubScenario: Boolean = true,
    additionalFilter: (suspend (ContentMessage<InvoiceContent>) -> Boolean)? = null,
    scenarioReceiver: ScenarioAndTypeReceiver<Unit, ContentMessage<InvoiceContent>>
) = onContent(includeFilterByChatInSubScenario, additionalFilter, scenarioReceiver)
