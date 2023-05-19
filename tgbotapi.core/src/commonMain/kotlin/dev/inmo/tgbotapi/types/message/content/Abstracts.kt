package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.abstracts.SpoilerableData
import dev.inmo.tgbotapi.abstracts.TextedInput
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.MessageThreadId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.types.threadId
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.modules.*

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
    fun asTelegramMedia(): TelegramMedia

    override fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId?,
        disableNotification: Boolean,
        protectContent: Boolean,
        replyToMessageId: MessageId?,
        allowSendingWithoutReply: Boolean?,
        replyMarkup: KeyboardMarkup?
    ): Request<out ContentMessage<MediaContent>> {
        TODO("Not yet implemented")
    }
}

sealed interface SpoilerableMediaContent : MediaContent, SpoilerableData

@ClassCastsIncluded
sealed interface ResendableContent {
    fun createResend(
        chatId: ChatIdentifier,
        messageThreadId: MessageThreadId? = chatId.threadId,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        replyToMessageId: MessageId? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<out Message>
}
