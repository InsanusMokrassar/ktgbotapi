@file:Suppress("unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptionsAsync
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.*
import dev.inmo.tgbotapi.extensions.utils.extensions.sourceChat
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.utils.PreviewFeature

typealias CommonMessageFilter<T> = (suspend (CommonMessage<T>) -> Boolean)

@PreviewFeature
internal suspend inline fun <reified T : MessageContent> BehaviourContext.onContent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = true,
    noinline additionalFilter: CommonMessageFilter<T>? = null,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<T>>
) = flowsUpdatesFilter.expectFlow(bot) {
    if (includeMediaGroups) {
        it.asSentMediaGroupUpdate() ?.data ?.mapNotNull {
            if (it.content is T) {
                val adaptedMessage = it as CommonMessage<T>
                if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
            } else {
                null
            }
        } ?.let {
            return@expectFlow it
        }
    }
    it.asBaseSentMessageUpdate() ?.data ?.asCommonMessage() ?.let { message ->
        if (message.content is T) {
            val adaptedMessage = message as CommonMessage<T>
            if (additionalFilter == null || additionalFilter(adaptedMessage)) adaptedMessage else null
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
        } else {
            null
        },
        stopOnCompletion = false
    ) {
        scenarioReceiver(triggerMessage)
    }
}

suspend fun BehaviourContext.onContentMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<MessageContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MessageContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MessageContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onContact(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<ContactContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<ContactContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<ContactContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onDice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<DiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onGame(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<GameContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<GameContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<GameContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onLocation(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<LocationContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<LocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LocationContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onPoll(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<PollContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PollContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PollContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onText(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVenue(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VenueContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VenueContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VenueContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onAudioMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<AudioMediaGroupContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioMediaGroupContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, true, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onDocumentMediaGroupContent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<DocumentMediaGroupContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentMediaGroupContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onMediaCollection(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: (suspend (CommonMessage<MediaCollectionContent<TelegramMediaFile>>) -> Boolean)? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaCollectionContent<TelegramMediaFile>>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onMedia(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<MediaContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onAnimation(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<AnimationContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AnimationContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onAudio(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<AudioContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onDocument(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<DocumentContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<PhotoContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PhotoContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onSticker(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<StickerContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<StickerContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StickerContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVideo(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<VideoContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVideoNote(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VideoNoteContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoNoteContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoNoteContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onVoice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VoiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VoiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
suspend fun BehaviourContext.onInvoice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<InvoiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<InvoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<InvoiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, markerFactory, scenarioReceiver)
