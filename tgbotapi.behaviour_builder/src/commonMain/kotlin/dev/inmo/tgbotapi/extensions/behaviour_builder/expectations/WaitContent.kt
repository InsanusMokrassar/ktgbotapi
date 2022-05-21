@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.update.media_group.SentMediaGroupUpdate
import dev.inmo.tgbotapi.types.update.abstracts.BaseSentMessageUpdate
import kotlinx.coroutines.flow.Flow

typealias CommonMessageToContentMapper<T> = suspend CommonMessage<T>.() -> T?

private suspend inline fun <reified O : MessageContent> BehaviourContext.waitContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<O>>? = null
): Flow<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val messages = when (it) {
        is SentMediaGroupUpdate -> {
            if (includeMediaGroups) {
                it.data
            } else {
                emptyList()
            }
        }
        is BaseSentMessageUpdate -> listOf(it.data)
        else -> return@expectFlow emptyList()
    }
    messages.mapNotNull { message ->
        val asCommonMessage = (message as CommonMessage<*>).withContent<O>() ?: return@mapNotNull null
        if (filter == null || filter(asCommonMessage)) {
            asCommonMessage.content
        } else {
            null
        }
    }
}


suspend fun BehaviourContext.waitContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitPoll(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<PollContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null
) = waitContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null
) = waitContent(count, initRequest, false, errorFactory, filter)
