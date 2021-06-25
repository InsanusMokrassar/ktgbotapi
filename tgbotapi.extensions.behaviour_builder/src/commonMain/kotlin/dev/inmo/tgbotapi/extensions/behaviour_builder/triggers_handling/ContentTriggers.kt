@file:Suppress("unused", "UNCHECKED_CAST")

package dev.inmo.tgbotapi.extensions.behaviour_builder.triggers_handling

import dev.inmo.micro_utils.coroutines.subscribeSafelyWithoutExceptions
import dev.inmo.tgbotapi.extensions.behaviour_builder.*
import dev.inmo.tgbotapi.extensions.behaviour_builder.expectations.expectFlow
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.optionallyWrapWithLaunch
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
    performInParallel: Boolean = true,
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
}.subscribeSafelyWithoutExceptions(
    scope,
    optionallyWrapWithLaunch(performInParallel) { triggerMessage ->
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
)

suspend fun BehaviourContext.onContentMessage(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<MessageContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MessageContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onContact(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<ContactContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<ContactContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onDice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<DiceContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onGame(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<GameContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<GameContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onLocation(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<LocationContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<LocationContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onPoll(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<PollContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PollContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onText(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<TextContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<TextContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVenue(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VenueContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VenueContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onAudioMediaGroup(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<AudioMediaGroupContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioMediaGroupContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, true, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onDocumentMediaGroupContent(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<DocumentMediaGroupContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentMediaGroupContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMediaCollection(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: (suspend (CommonMessage<MediaCollectionContent<TelegramMediaFile>>) -> Boolean)? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaCollectionContent<TelegramMediaFile>>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onMedia(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = true,
    additionalFilter: CommonMessageFilter<MediaContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<MediaContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onAnimation(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<AnimationContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AnimationContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onAudio(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<AudioContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<AudioContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onDocument(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<DocumentContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<DocumentContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onPhoto(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<PhotoContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<PhotoContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onSticker(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<StickerContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<StickerContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVideo(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    includeMediaGroups: Boolean = false,
    additionalFilter: CommonMessageFilter<VideoContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, includeMediaGroups, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVideoNote(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VideoNoteContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VideoNoteContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onVoice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<VoiceContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<VoiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
suspend fun BehaviourContext.onInvoice(
    includeFilterByChatInBehaviourSubContext: Boolean = true,
    additionalFilter: CommonMessageFilter<InvoiceContent>? = null,
    performInParallel: Boolean = true,
    scenarioReceiver: BehaviourContextAndTypeReceiver<Unit, CommonMessage<InvoiceContent>>
) = onContent(includeFilterByChatInBehaviourSubContext, false, additionalFilter, performInParallel, scenarioReceiver)
