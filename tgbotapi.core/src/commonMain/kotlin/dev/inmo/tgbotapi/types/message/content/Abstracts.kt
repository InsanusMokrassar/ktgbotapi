package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.SpoilerableData
import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.abstracts.WithCustomizableCaption
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.business_connection.BusinessConnectionId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.media.TelegramFreeMedia
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.Serializable
import kotlinx.serialization.modules.*

@Serializable
sealed interface MessageContent: ResendableContent {
    companion object {
        @RiskFeature("This serialization module can be changed in near releases")
        fun serializationModule(
            visualMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<VisualMediaGroupPartContent>.() -> Unit = {},
            documentMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<DocumentMediaGroupPartContent>.() -> Unit = {},
            audioMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<AudioMediaGroupPartContent>.() -> Unit = {},
            mediaGroupPartContentAdditionalBuilder: PolymorphicModuleBuilder<MediaGroupPartContent>.() -> Unit = {},
            textedMediaContentAdditionalBuilder: PolymorphicModuleBuilder<TextedMediaContent>.() -> Unit = {},
            mediaContentAdditionalBuilder: PolymorphicModuleBuilder<MediaContent>.() -> Unit = {},
            spoilerableMediaContentAdditionalBuilder: PolymorphicModuleBuilder<SpoilerableMediaContent>.() -> Unit = {},
            mediaCollectionContentAdditionalBuilder: PolymorphicModuleBuilder<MediaCollectionContent<*>>.() -> Unit = {},
            additionalBuilder: PolymorphicModuleBuilder<MessageContent>.() -> Unit = {}
        ) = SerializersModule {
            polymorphic(MessageContent::class) {

                subclass(ContactContent::class)
                subclass(VenueContent::class)
                subclass(PollContent::class)
                subclass(DiceContent::class)
                subclass(TextContent::class)

                subclass(LocationContent::class, LocationContentSerializer)

                subclass(PhotoContent::class)
                subclass(VideoContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                subclass(VoiceContent::class)
                subclass(VideoNoteContent::class)
                subclass(AnimationContent::class)
                subclass(StickerContent::class)
                subclass(InvoiceContent::class)
                subclass(StoryContent::class)
                subclass(GiveawayPublicResultsContent::class)
                subclass(GiveawayContent::class)

                additionalBuilder()
            }

            polymorphic(TextedContent::class) {
                subclass(TextContent::class)
                subclass(VoiceContent::class)
                subclass(MediaGroupContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)
                subclass(VideoContent::class)
                subclass(PhotoContent::class)
                subclass(AnimationContent::class)
            }

            polymorphic(MediaCollectionContent::class) {
                subclass(PhotoContent::class)

                mediaCollectionContentAdditionalBuilder()
            }
            polymorphic(MediaContent::class) {
                subclass(VideoNoteContent::class)
                subclass(VideoContent::class)
                subclass(StickerContent::class)
                subclass(PhotoContent::class)
                subclass(VoiceContent::class)
                subclass(AnimationContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                mediaContentAdditionalBuilder()
            }
            polymorphic(SpoilerableMediaContent::class) {
                subclass(VideoContent::class)
                subclass(PhotoContent::class)
                subclass(AnimationContent::class)

                spoilerableMediaContentAdditionalBuilder()
            }
            polymorphic(TextedMediaContent::class) {
                subclass(PhotoContent::class)
                subclass(VoiceContent::class)
                subclass(AnimationContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                textedMediaContentAdditionalBuilder()
            }
            polymorphic(MediaGroupPartContent::class) {
                subclass(PhotoContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                mediaGroupPartContentAdditionalBuilder()
            }
            polymorphic(AudioMediaGroupPartContent::class) {
                subclass(AudioContent::class)

                audioMediaGroupContentAdditionalBuilder()
            }
            polymorphic(DocumentMediaGroupPartContent::class) {
                subclass(DocumentContent::class)

                documentMediaGroupContentAdditionalBuilder()
            }
            polymorphic(VisualMediaGroupPartContent::class) {
                subclass(PhotoContent::class)
                subclass(VideoContent::class)

                visualMediaGroupContentAdditionalBuilder()
            }
        }
    }
}

sealed interface MediaCollectionContent<T: TelegramMediaFile>: MessageContent, MediaContent {
    val mediaCollection: List<T>
}

/**
 * All the subtypes of this content will have [text] and [textSources] fields
 */
sealed interface TextedContent : MessageContent, TextedInput

sealed interface MediaContent: MessageContent {
    val media: TelegramMediaFile
    fun asTelegramMedia(): TelegramFreeMedia
}

sealed interface SpoilerableMediaContent : MediaContent, SpoilerableData
sealed interface WithCustomizedCaptionMediaContent : MediaContent, TextedContent, WithCustomizableCaption

@ClassCastsIncluded
sealed interface ResendableContent {
    fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId? = chatId.threadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        effectId: EffectId? = null,
        replyParameters: ReplyParameters? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<out AccessibleMessage>

    fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId? = chatId.threadId,
        businessConnectionId: BusinessConnectionId? = chatId.businessConnectionId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        effectId: EffectId? = null,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<out AccessibleMessage> = createResend(
        chatId = chatId,
        messageThreadId = messageThreadId,
        businessConnectionId = businessConnectionId,
        disableNotification = disableNotification,
        protectContent = protectContent,
        effectId = effectId,
        replyParameters = replyToMessageId ?.let {
            ReplyParameters(
                chatId,
                replyToMessageId,
                allowSendingWithoutReply = allowSendingWithoutReply
            )
        },
        replyMarkup = replyMarkup
    )
}
