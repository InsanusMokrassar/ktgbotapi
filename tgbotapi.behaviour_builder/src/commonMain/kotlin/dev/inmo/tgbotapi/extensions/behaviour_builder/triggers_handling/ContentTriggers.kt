@file:Suppress("unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.whenCommonMessage
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update

typealias CommonMessageFilter<T> = SimpleFilter<CommonMessage<T>>

internal suspend inline fun <BC : BehaviourContext, reified T : MessageContent> BC.onContentMessageWithType(
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<T>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any>? = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    when (it) {
        is BaseSentMessageUpdate -> it.data.whenCommonMessage(::listOfNotNull)
        else -> null
    } ?.mapNotNull { message ->
        if (message.content is T) message as CommonMessage<T> else null
    }
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
suspend fun <BC : BehaviourContext> BC.onContentMessage(
    initialFilter: CommonMessageFilter<MessageContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MessageContent>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MessageContent>, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MessageContent>>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onContact(
    initialFilter: CommonMessageFilter<ContactContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ContactMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ContactMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ContactMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onDice(
    initialFilter: CommonMessageFilter<DiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, DiceMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in DiceMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, DiceMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onGame(
    initialFilter: CommonMessageFilter<GameContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, GameMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in GameMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, GameMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onLocation(
    initialFilter: CommonMessageFilter<LocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, LocationMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in LocationMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, LocationMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onLiveLocation(
    initialFilter: CommonMessageFilter<LiveLocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, LiveLocationMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in LiveLocationMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, LiveLocationMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onStaticLocation(
    initialFilter: CommonMessageFilter<StaticLocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, StaticLocationMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in StaticLocationMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, StaticLocationMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onPoll(
    initialFilter: CommonMessageFilter<PollContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PollMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in PollMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PollMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onText(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onStory(
    initialFilter: CommonMessageFilter<StoryContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, StoryMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in StoryMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, StoryMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onTextedContent(
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextedMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextedMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextedMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onVenue(
    initialFilter: CommonMessageFilter<VenueContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, VenueMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in VenueMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, VenueMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onAudioMediaGroup(
    initialFilter: CommonMessageFilter<AudioMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, AudioMediaGroupMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in AudioMediaGroupMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, AudioMediaGroupMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onDocumentMediaGroupContent(
    initialFilter: CommonMessageFilter<DocumentMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, DocumentMediaGroupMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in DocumentMediaGroupMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, DocumentMediaGroupMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onTextedMediaContent(
    initialFilter: CommonMessageFilter<TextedMediaContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, TextedMediaMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in TextedMediaMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, TextedMediaMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMediaCollection(
    initialFilter: CommonMessageFilter<MediaCollectionContent<TelegramMediaFile>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaCollectionMessage<TelegramMediaFile>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in MediaCollectionMessage<TelegramMediaFile>, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaCollectionMessage<TelegramMediaFile>>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMedia(
    initialFilter: CommonMessageFilter<MediaContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in MediaMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onAnimation(
    initialFilter: CommonMessageFilter<AnimationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, AnimationMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in AnimationMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, AnimationMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onAudio(
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, AudioMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in AudioMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, AudioMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onDocument(
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, DocumentMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in DocumentMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, DocumentMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onPhoto(
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, PhotoMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in PhotoMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, PhotoMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onSticker(
    initialFilter: CommonMessageFilter<StickerContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, StickerMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in StickerMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, StickerMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onVideo(
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, VideoMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in VideoMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, VideoMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onVideoNote(
    initialFilter: CommonMessageFilter<VideoNoteContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, VideoNoteMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in VideoNoteMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, VideoNoteMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onVoice(
    initialFilter: CommonMessageFilter<VoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, VoiceMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in VoiceMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, VoiceMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onInvoice(
    initialFilter: CommonMessageFilter<InvoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, InvoiceMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in InvoiceMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, InvoiceMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onVisualContent(
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, VisualMediaGroupMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in VisualMediaGroupMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, VisualMediaGroupMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onMediaContent(
    initialFilter: CommonMessageFilter<MediaContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, MediaMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in MediaMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, MediaMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

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
suspend fun <BC : BehaviourContext> BC.onGiveawayContent(
    initialFilter: CommonMessageFilter<GiveawayContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ScheduledGiveawayContentMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in ScheduledGiveawayContentMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ScheduledGiveawayContentMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)


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
suspend fun <BC : BehaviourContext> BC.onGiveawayPublicResultsContent(
    initialFilter: CommonMessageFilter<GiveawayPublicResultsContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, GiveawayPublicResultsContentMessage, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in GiveawayPublicResultsContentMessage, Any>? = ByChatMessageMarkerFactory,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, GiveawayPublicResultsContentMessage>
) = onContentMessageWithType(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
