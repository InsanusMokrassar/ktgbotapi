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
import dev.inmo.tgbotapi.types.message.content.AudioMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.DocumentMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.MediaGroupContent
import dev.inmo.tgbotapi.types.message.content.VisualMediaGroupContent
import dev.inmo.tgbotapi.types.message.content.InvoiceContent
import dev.inmo.tgbotapi.types.update.abstracts.BaseEditMessageUpdate
import kotlinx.coroutines.flow.toList

private suspend inline fun <reified O : MessageContent> BehaviourContext.waitEditedContent(
    count: Int = 1,
    initRequest: Request<*>? = null,
    includeMediaGroups: Boolean = true,
    noinline errorFactory: NullableRequestBuilder<*> = { null },
    filter: SimpleFilter<CommonMessage<O>>? = null
): List<O> = expectFlow(
    initRequest,
    count,
    errorFactory
) {
    val messages = when (it) {
        is BaseEditMessageUpdate -> {
            val commonMessage = it.data.asCommonMessage() ?: return@expectFlow emptyList()
            if (commonMessage !is MediaGroupMessage<*> || includeMediaGroups) {
                listOf(commonMessage)
            } else {
                emptyList()
            }
        }
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
}.toList().toList()

suspend fun BehaviourContext.waitEditedContentMessage(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MessageContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedContact(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<ContactContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedDice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<DiceContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedGame(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<GameContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LocationContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedLiveLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<LiveLocationContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedStaticLocation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StaticLocationContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedText(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<TextContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedVenue(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VenueContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedAudioMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<AudioMediaGroupContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedDocumentMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<DocumentMediaGroupContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedMedia(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<MediaContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedAnyMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<MediaGroupContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedVisualMediaGroupContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<VisualMediaGroupContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedTextedMediaContent(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = true,
    filter: SimpleFilter<CommonMessage<TextedMediaContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedAnimation(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<AnimationContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedAudio(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<AudioContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedDocument(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<DocumentContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedPhoto(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<PhotoContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedSticker(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<StickerContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedVideo(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    includeMediaGroups: Boolean = false,
    filter: SimpleFilter<CommonMessage<VideoContent>>? = null
) = waitEditedContent(count, initRequest, includeMediaGroups, errorFactory, filter)
suspend fun BehaviourContext.waitEditedVideoNote(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VideoNoteContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedVoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<VoiceContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
suspend fun BehaviourContext.waitEditedInvoice(
    initRequest: Request<*>? = null,
    errorFactory: NullableRequestBuilder<*> = { null },
    count: Int = 1,
    filter: SimpleFilter<CommonMessage<InvoiceContent>>? = null
) = waitEditedContent(count, initRequest, false, errorFactory, filter)
