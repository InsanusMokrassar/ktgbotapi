package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTwoTypesReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.CustomBehaviourContextAndTypeReceiver
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.isWithMention
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.isWithTextMention
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.AnyMarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.marker_factories.MarkerFactory
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.times
import dev.inmo.tgbotapi.types.UserId
import dev.inmo.tgbotapi.types.Username
import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.abstracts.ChatContentMessage
import dev.inmo.tgbotapi.types.message.content.AnimationContent
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextedContent
import dev.inmo.tgbotapi.types.message.content.VideoContent
import dev.inmo.tgbotapi.types.message.content.LivePhotoContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.VoiceContent
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onMention(
    username: Username,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<T>, Any>? = AnyMarkerFactory(),
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<T>>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithMention(username)
    },
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

internal inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onTextMention(
    userId: UserId,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<T>, Any>? = AnyMarkerFactory(),
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<T>>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithTextMention(userId)
    },
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)

internal inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onMention(
    user: User,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<T>, Any>? = AnyMarkerFactory(),
    noinline additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<T>>? = null,
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithMention(user)
    },
    subcontextUpdatesFilter,
    markerFactory,
    additionalSubcontextInitialAction,
    scenarioReceiver
)


/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAnyContent(
    username: Username,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextedContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextedContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithAnyContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextedContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextedContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAnyContent(
    user: User,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextedContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextedContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVoiceContent(
    username: Username,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VoiceContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VoiceContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithVoiceContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VoiceContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VoiceContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVoiceContent(
    user: User,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VoiceContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VoiceContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithMediaGroupContent(
    username: Username,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithMediaGroupContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithMediaGroupContent(
    user: User,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithMediaGroupPartContent(
    username: Username,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupPartContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithMediaGroupPartContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupPartContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithMediaGroupPartContent(
    user: User,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<MediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<MediaGroupPartContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAudioContent(
    username: Username,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AudioContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AudioContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithAudioContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AudioContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AudioContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAudioContent(
    user: User,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AudioContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AudioContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithDocumentContent(
    username: Username,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<DocumentContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<DocumentContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithDocumentContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<DocumentContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<DocumentContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithDocumentContent(
    user: User,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<DocumentContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<DocumentContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVisualMediaGroupPartContent(
    username: Username,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VisualMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VisualMediaGroupPartContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithVisualMediaGroupPartContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VisualMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VisualMediaGroupPartContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVisualMediaGroupPartContent(
    user: User,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VisualMediaGroupPartContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VisualMediaGroupPartContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVideoContent(
    username: Username,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VideoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VideoContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithLivePhotoContent(
    username: Username,
    initialFilter: CommonMessageFilter<LivePhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<LivePhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<LivePhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<LivePhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<LivePhotoContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithVideoContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VideoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VideoContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithLivePhotoContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<LivePhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<LivePhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<LivePhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<LivePhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<LivePhotoContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithVideoContent(
    user: User,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<VideoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<VideoContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithLivePhotoContent(
    user: User,
    initialFilter: CommonMessageFilter<LivePhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<LivePhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<LivePhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<LivePhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<LivePhotoContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithPhotoContent(
    username: Username,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<PhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<PhotoContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithPhotoContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<PhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<PhotoContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithPhotoContent(
    user: User,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<PhotoContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<PhotoContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAnimationContent(
    username: Username,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AnimationContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AnimationContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithAnimationContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AnimationContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AnimationContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithAnimationContent(
    user: User,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<AnimationContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<AnimationContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithTextContent(
    username: Username,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onTextMentionWithTextContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
fun <BC : BehaviourContext> BC.onMentionWithTextContent(
    user: User,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, ChatContentMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in ChatContentMessage<TextContent>, Any>? = AnyMarkerFactory(),
    additionalSubcontextInitialAction: CustomBehaviourContextAndTwoTypesReceiver<BC, Unit, Update, ChatContentMessage<TextContent>>? = null,
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, ChatContentMessage<TextContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, additionalSubcontextInitialAction, scenarioReceiver)

