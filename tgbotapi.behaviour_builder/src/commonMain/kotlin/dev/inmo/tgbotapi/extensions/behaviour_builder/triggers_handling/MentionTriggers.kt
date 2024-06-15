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
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.AnimationContent
import dev.inmo.tgbotapi.types.message.content.AudioContent
import dev.inmo.tgbotapi.types.message.content.DocumentContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.PhotoContent
import dev.inmo.tgbotapi.types.message.content.TextContent
import dev.inmo.tgbotapi.types.message.content.TextedContent
import dev.inmo.tgbotapi.types.message.content.VideoContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupPartContent
import dev.inmo.tgbotapi.types.message.content.VoiceContent
import dev.inmo.tgbotapi.types.update.abstracts.Update

internal suspend inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onMention(
    username: Username,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any>? = AnyMarkerFactory(),
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithMention(username)
    },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

internal suspend inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onTextMention(
    userId: UserId,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any>? = AnyMarkerFactory(),
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithTextMention(userId)
    },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)

internal suspend inline fun <BC : BehaviourContext, reified T : TextedContent> BC.onMention(
    user: User,
    initialFilter: CommonMessageFilter<T>? = null,
    noinline subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<T>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<T>, Any>? = AnyMarkerFactory(),
    noinline scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<T>>
) = onContentMessageWithType<BC, T>(
    initialFilter * {
        it.content.isWithMention(user)
    },
    subcontextUpdatesFilter,
    markerFactory,
    scenarioReceiver
)


/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAnyContent(
    username: Username,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextedContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithAnyContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextedContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAnyContent(
    user: User,
    initialFilter: CommonMessageFilter<TextedContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextedContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextedContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextedContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVoiceContent(
    username: Username,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VoiceContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithVoiceContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VoiceContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVoiceContent(
    user: User,
    initialFilter: CommonMessageFilter<VoiceContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VoiceContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VoiceContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VoiceContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithMediaGroupContent(
    username: Username,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithMediaGroupContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupContent<MediaGroupPartContent>>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithMediaGroupContent(
    user: User,
    initialFilter: CommonMessageFilter<MediaGroupContent<MediaGroupPartContent>>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupContent<MediaGroupPartContent>>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupContent<MediaGroupPartContent>>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupContent<MediaGroupPartContent>>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithMediaGroupPartContent(
    username: Username,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupPartContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithMediaGroupPartContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupPartContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithMediaGroupPartContent(
    user: User,
    initialFilter: CommonMessageFilter<MediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<MediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<MediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<MediaGroupPartContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAudioContent(
    username: Username,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AudioContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithAudioContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AudioContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAudioContent(
    user: User,
    initialFilter: CommonMessageFilter<AudioContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AudioContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AudioContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AudioContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithDocumentContent(
    username: Username,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<DocumentContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithDocumentContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<DocumentContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithDocumentContent(
    user: User,
    initialFilter: CommonMessageFilter<DocumentContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<DocumentContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<DocumentContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<DocumentContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVisualMediaGroupPartContent(
    username: Username,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VisualMediaGroupPartContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithVisualMediaGroupPartContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VisualMediaGroupPartContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVisualMediaGroupPartContent(
    user: User,
    initialFilter: CommonMessageFilter<VisualMediaGroupPartContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VisualMediaGroupPartContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VisualMediaGroupPartContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VisualMediaGroupPartContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVideoContent(
    username: Username,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VideoContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithVideoContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VideoContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithVideoContent(
    user: User,
    initialFilter: CommonMessageFilter<VideoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<VideoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<VideoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<VideoContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithPhotoContent(
    username: Username,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<PhotoContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithPhotoContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<PhotoContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithPhotoContent(
    user: User,
    initialFilter: CommonMessageFilter<PhotoContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<PhotoContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<PhotoContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<PhotoContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAnimationContent(
    username: Username,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AnimationContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithAnimationContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AnimationContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithAnimationContent(
    user: User,
    initialFilter: CommonMessageFilter<AnimationContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<AnimationContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<AnimationContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<AnimationContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)



/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithTextContent(
    username: Username,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextContent>>
) = onMention(username, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onTextMentionWithTextContent(
    userId: UserId,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextContent>>
) = onTextMention(userId, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

/**
 * @param [markerFactory] **Pass null to handle requests fully parallel**. Will be used to identify different "stream".
 * [scenarioReceiver] will be called synchronously in one "stream". Output of [markerFactory] will be used as a key for
 * "stream"
 */
suspend fun <BC : BehaviourContext> BC.onMentionWithTextContent(
    user: User,
    initialFilter: CommonMessageFilter<TextContent>? = null,
    subcontextUpdatesFilter: CustomBehaviourContextAndTwoTypesReceiver<BC, Boolean, CommonMessage<TextContent>, Update>? = null,
    markerFactory: MarkerFactory<in CommonMessage<TextContent>, Any>? = AnyMarkerFactory(),
    scenarioReceiver: CustomBehaviourContextAndTypeReceiver<BC, Unit, CommonMessage<TextContent>>
) = onMention(user, initialFilter, subcontextUpdatesFilter, markerFactory, scenarioReceiver)

