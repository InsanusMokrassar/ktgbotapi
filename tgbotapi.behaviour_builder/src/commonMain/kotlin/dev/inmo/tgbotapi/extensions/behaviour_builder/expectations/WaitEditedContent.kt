@file:Suppress("unused")

package dev.inmo.tgbotapi.extensions.behaviour_builder.expectations

import dev.inmo.tgbotapi.extensions.behaviour_builder.BehaviourContext
import dev.inmo.tgbotapi.extensions.behaviour_builder.utils.SimpleFilter
import dev.inmo.tgbotapi.extensions.utils.asCommonMessage
import dev.inmo.tgbotapi.extensions.utils.withContent
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.message.abstracts.CommonMessage
import dev.inmo.tgbotapi.types.message.abstracts.MediaGroupMessage
import dev.inmo.tgbotapi.types.message.content.*
import dev.inmo.tgbotapi.types.message.content.abstracts.*
import dev.inmo.tgbotapi.types.message.content.media.*
import dev.inmo.tgbotapi.types.message.content.media.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.media.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.payments.InvoiceContent
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import kotlinx.coroutines.flow.toList

private suspend fun <O> BehaviourContext.waitEditedCommonMessage(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: suspend CommonMessage<MessageContent>.() -> O?
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val messages = when (it) {
        is BaseEditMessageUpdate -> {
            val commonMessage = it.data.asCommonMessage()
            if (commonMessage !is MediaGroupMessage<*> || includeMediaGroups) {
                listOf(commonMessage)
            } else {
                emptyList()
            }
        }
        else -> return@expectFlow emptyList()
    }
    messages.mapNotNull { message ->
        val asCommonMessage = message as CommonMessage<MessageContent>
        if (filter == null || filter(asCommonMessage)) {
            asCommonMessage.mapper()
        } else {
            null
        }
    }
}.toList().toList()

private suspend inline fun <reified T : MessageContent> BehaviourContext.waitEditedContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    noinline filter: SimpleFilter<CommonMessage<T>>? = null,
    noinline mapper: CommonMessageToContentMapper<T>? = null
) : List<T> = waitEditedCommonMessage<T>(
    count,
    initRequest,
    includeMediaGroups,
    errorFactory,
    filter ?.let {
        {
            it.withContent<T>() ?.let { filter(it) } == true
        }
    },
    contentConverter(mapper)
)

suspend fun BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null,
    mapper: CommonMessageToContentMapper<MessageContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null,
    mapper: CommonMessageToContentMapper<ContactContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null,
    mapper: CommonMessageToContentMapper<DiceContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null,
    mapper: CommonMessageToContentMapper<GameContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null,
    mapper: CommonMessageToContentMapper<LocationContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null,
    mapper: CommonMessageToContentMapper<LiveLocationContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null,
    mapper: CommonMessageToContentMapper<StaticLocationContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null,
    mapper: CommonMessageToContentMapper<TextContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null,
    mapper: CommonMessageToContentMapper<VenueContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<AudioMediaGroupContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<DocumentMediaGroupContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null,
    mapper: CommonMessageToContentMapper<MediaContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<MediaGroupContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null,
    mapper: CommonMessageToContentMapper<VisualMediaGroupContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedTextedMediaGroupMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaGroupMediaContent>>? = null,
    mapper: CommonMessageToContentMapper<TextedMediaGroupMediaContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null,
    mapper: CommonMessageToContentMapper<TextedMediaContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null,
    mapper: CommonMessageToContentMapper<AnimationContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null,
    mapper: CommonMessageToContentMapper<AudioContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null,
    mapper: CommonMessageToContentMapper<DocumentContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null,
    mapper: CommonMessageToContentMapper<PhotoContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null,
    mapper: CommonMessageToContentMapper<StickerContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null,
    mapper: CommonMessageToContentMapper<VideoContent>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null,
    mapper: CommonMessageToContentMapper<VideoNoteContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null,
    mapper: CommonMessageToContentMapper<VoiceContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
suspend fun BehaviourContext.waitEditedInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null,
    mapper: CommonMessageToContentMapper<InvoiceContent>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter, mapper)
