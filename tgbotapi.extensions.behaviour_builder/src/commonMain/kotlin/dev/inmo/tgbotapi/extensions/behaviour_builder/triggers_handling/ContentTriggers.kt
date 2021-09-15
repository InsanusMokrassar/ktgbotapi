@file:Suppress("unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.whenCommonMessage
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.update.MediaGroupUpdates.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

typealias CommonMessageFilter<T> = SimpleFilter<CommonMessage<T>>
inline fun <T : MessageContent> CommonMessageFilter(noinline block: CommonMessageFilter<T>) = block

internal suspend inline fun <reified T : MessageContent> BehaviourContext.onContent(
    noinline initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<T>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    when (it) {
        is BaseSentMessageUpdate -> it.data.whenCommonMessage(::listOfNotNull)
        is SentMediaGroupUpdate -> it.data
        else -> null
    } ?.mapNotNull { message ->
        if (message.content is T) message as CommonMessage<T> else null
    }
}

suspend fun BehaviourContext.onContentMessage(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<MessageContent>? = null,
    includeMediaGroups: Boolean = true,
    markerFactory: MarkerFactory<in CommonMessage<MessageContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MessageContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onContact(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<ContactContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<ContactContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<ContactContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onDice(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<DiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DiceContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onGame(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<GameContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<GameContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<GameContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onLocation(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<LocationContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<LocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LocationContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onPoll(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<PollContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PollContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PollContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onText(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onVenue(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<VenueContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VenueContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VenueContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onAudioMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<AudioMediaGroupContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioMediaGroupContent>>
) = onContent(
    additionalFilter,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onDocumentMediaGroupContent(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<DocumentMediaGroupContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentMediaGroupContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMediaCollection(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = false,
    additionalFilter: SimpleFilter<CommonMessage<MediaCollectionContent<TelegramMediaFile>>>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaCollectionContent<TelegramMediaFile>>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onMedia(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<MediaContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onAnimation(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<AnimationContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AnimationContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onAudio(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<AudioContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onDocument(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<DocumentContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<PhotoContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PhotoContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onSticker(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<StickerContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<StickerContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StickerContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onVideo(
    includeFilterByChatInBehaviourSubContext: Boolean,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<VideoContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoContent>>
) = onContent(
    additionalFilter ?: if (includeMediaGroups) null else CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onVideoNote(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<VideoNoteContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoNoteContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoNoteContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onVoice(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<VoiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VoiceContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)
suspend fun BehaviourContext.onInvoice(
    includeFilterByChatInBehaviourSubContext: Boolean,
    additionalFilter: CommonMessageFilter<InvoiceContent>? = null,
    markerFactory: MarkerFactory<in CommonMessage<InvoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<InvoiceContent>>
) = onContent(
    additionalFilter ?: CommonMessageFilterExcludeMediaGroups,
    if (includeFilterByChatInBehaviourSubContext) { MessageFilterByChat } else null,
    markerFactory,
    scenarioReceiver
)


/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onContentMessage(
    initialFilter: CommonMessageFilter<MessageContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MessageContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MessageContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MessageContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onContact(
    initialFilter: CommonMessageFilter<ContactContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<ContactContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<ContactContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<ContactContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onDice(
    initialFilter: CommonMessageFilter<DiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DiceContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onGame(
    initialFilter: CommonMessageFilter<GameContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<GameContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<GameContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<GameContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onLocation(
    initialFilter: CommonMessageFilter<LocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<LocationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<LocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LocationContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onLiveLocation(
    initialFilter: CommonMessageFilter<LiveLocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<LiveLocationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<LiveLocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LiveLocationContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onStaticLocation(
    initialFilter: CommonMessageFilter<StaticLocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<StaticLocationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<StaticLocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StaticLocationContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPoll(
    initialFilter: CommonMessageFilter<PollContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<PollContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<PollContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PollContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onText(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVenue(
    initialFilter: CommonMessageFilter<VenueContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VenueContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VenueContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VenueContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onAudioMediaGroup(
    initialFilter: CommonMessageFilter<AudioMediaGroupContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AudioMediaGroupContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AudioMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioMediaGroupContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onDocumentMediaGroupContent(
    initialFilter: CommonMessageFilter<DocumentMediaGroupContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DocumentMediaGroupContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DocumentMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentMediaGroupContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onMediaCollection(
    initialFilter: CommonMessageFilter<MediaCollectionContent<TelegramMediaFile>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaCollectionContent<TelegramMediaFile>>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onMedia(
    initialFilter: CommonMessageFilter<MediaContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MediaContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MediaContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onAnimation(
    initialFilter: CommonMessageFilter<AnimationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AnimationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AnimationContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onAudio(
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AudioContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onDocument(
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DocumentContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onPhoto(
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<PhotoContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PhotoContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onSticker(
    initialFilter: CommonMessageFilter<StickerContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<StickerContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<StickerContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StickerContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVideo(
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VideoContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVideoNote(
    initialFilter: CommonMessageFilter<VideoNoteContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VideoNoteContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VideoNoteContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoNoteContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onVoice(
    initialFilter: CommonMessageFilter<VoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VoiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VoiceContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

/**
 * @param initialFilter This filter will be called to remove unnecessary data BEFORE [scenarioReceiver] call
 * @param subcontextUpdatesFilter This filter will be applied to each update inside of [scenarioReceiver]. For example,
 * this filter will be used if you will call [dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.waitContentMessage]
 * @param [markerFactory] Will be used to identify different "stream". [scenarioReceiver] will be called synchronously
 * in one "stream". Output of [markerFactory] will be used as a key for "stream"
 * @param scenarioReceiver Main callback which will be used to handle incoming data if [initialFilter] will pass that
 * data
 */
suspend fun BehaviourContext.onInvoice(
    initialFilter: CommonMessageFilter<InvoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<InvoiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<InvoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<InvoiceContent>>
) = onContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
