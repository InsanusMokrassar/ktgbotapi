

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
 */@file:Suppress("unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.CommonMessageFilterExcludeMediaGroups
import dev.inmo.tgbotapi.extensions.behaviour_builder.filters.MessageFilterByChat
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.ByChatMessageMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.utils.asEditMessageUpdate
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.types.files.abstracts.TelegramMediaFile
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import dev.inmo.tgbotapi.types.update.abstracts.Update
import dev.inmo.tgbotapi.utils.PreviewFeature

@PreviewFeature
internal suspend inline fun <reified T : MessageContent> BehaviourContext.onEditedContent(
    noinline initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<T>, Update>? = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any> = ByChatMessageMarkerFactory,
    noinline scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<T>>
) = on(markerFactory, initialFilter, subcontextUpdatesFilter, scenarioReceiver) {
    when (it) {
        is BaseEditMessageUpdate -> (it.asEditMessageUpdate() ?.data ?.withContent<T>())
        else -> null
    } ?.let(::listOfNotNull)
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
suspend fun BehaviourContext.onEditedContentMessage(
    initialFilter: CommonMessageFilter<MessageContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MessageContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MessageContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MessageContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedContact(
    initialFilter: CommonMessageFilter<ContactContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<ContactContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<ContactContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<ContactContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedDice(
    initialFilter: CommonMessageFilter<DiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DiceContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedGame(
    initialFilter: CommonMessageFilter<GameContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<GameContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<GameContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<GameContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedLocation(
    initialFilter: CommonMessageFilter<LocationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<LocationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<LocationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LocationContent>>
)= onEditedContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

@Deprecated("Potentially, this trigger will never be used. Use `onPollUpdated` instead")
suspend fun BehaviourContext.onEditedPoll(
    initialFilter: CommonMessageFilter<PollContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<PollContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<PollContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PollContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedText(
    initialFilter: CommonMessageFilter<TextContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<TextContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedVenue(
    initialFilter: CommonMessageFilter<VenueContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VenueContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VenueContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VenueContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedAudioMediaGroup(
    initialFilter: CommonMessageFilter<AudioMediaGroupContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AudioMediaGroupContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AudioMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioMediaGroupContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedDocumentMediaGroupContent(
    initialFilter: CommonMessageFilter<DocumentMediaGroupContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DocumentMediaGroupContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DocumentMediaGroupContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentMediaGroupContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedMediaCollection(
    initialFilter: CommonMessageFilter<MediaCollectionContent<TelegramMediaFile>>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MediaCollectionContent<TelegramMediaFile>>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaCollectionContent<TelegramMediaFile>>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedMedia(
    initialFilter: CommonMessageFilter<MediaContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<MediaContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<MediaContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedAnimation(
    initialFilter: CommonMessageFilter<AnimationContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AnimationContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AnimationContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedAudio(
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<AudioContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedDocument(
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<DocumentContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedPhoto(
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<PhotoContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PhotoContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedSticker(
    initialFilter: CommonMessageFilter<StickerContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<StickerContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<StickerContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StickerContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedVideo(
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VideoContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedVideoNote(
    initialFilter: CommonMessageFilter<VideoNoteContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VideoNoteContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VideoNoteContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoNoteContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedVoice(
    initialFilter: CommonMessageFilter<VoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<VoiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VoiceContent>>
)= onEditedContent(
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
suspend fun BehaviourContext.onEditedInvoice(
    initialFilter: CommonMessageFilter<InvoiceContent>? = CommonMessageFilterExcludeMediaGroups,
    subcontextUpdatesFilter: BehaviourContextAndTwoTypesReceiver<Boolean, CommonMessage<InvoiceContent>, Update> = MessageFilterByChat,
    markerFactory: MarkerFactory<in CommonMessage<InvoiceContent>, Any> = ByChatMessageMarkerFactory,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<InvoiceContent>>
)= onEditedContent(
    initialFilter,
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)
