package dev.inmo.tgbotapi.types.message.content

import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import dev.inmo.tgbotapi.requests.abstracts.Request
import dev.inmo.tgbotapi.types.ChatIdentifier
import dev.inmo.tgbotapi.types.MessageId
import dev.inmo.tgbotapi.types.buttons.KeyboardMarkup
import dev.inmo.tgbotapi.types.files.TelegramMediaFile
import dev.inmo.tgbotapi.types.media.TelegramMedia
import dev.inmo.tgbotapi.types.message.abstracts.*
import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.modules.*

sealed interface MessageContent: ResendableContent {
    companion object {
        @RiskFeature("This serialization module can be changed in near releases")
        fun serializationModule(
            visualMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<VisualMediaGroupContent>.() -> Unit = {},
            documentMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<DocumentMediaGroupContent>.() -> Unit = {},
            audioMediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<AudioMediaGroupContent>.() -> Unit = {},
            mediaGroupContentAdditionalBuilder: PolymorphicModuleBuilder<MediaGroupContent>.() -> Unit = {},
            textedMediaContentAdditionalBuilder: PolymorphicModuleBuilder<TextedMediaContent>.() -> Unit = {},
            mediaContentAdditionalBuilder: PolymorphicModuleBuilder<MediaContent>.() -> Unit = {},
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
            polymorphic(TextedMediaContent::class) {
                subclass(PhotoContent::class)
                subclass(VoiceContent::class)
                subclass(AnimationContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                textedMediaContentAdditionalBuilder()
            }
            polymorphic(MediaGroupContent::class) {
                subclass(PhotoContent::class)
                subclass(AudioContent::class)
                subclass(DocumentContent::class)

                mediaGroupContentAdditionalBuilder()
            }
            polymorphic(AudioMediaGroupContent::class) {
                subclass(AudioContent::class)

                audioMediaGroupContentAdditionalBuilder()
            }
            polymorphic(DocumentMediaGroupContent::class) {
                subclass(DocumentContent::class)

                documentMediaGroupContentAdditionalBuilder()
            }
            polymorphic(VisualMediaGroupContent::class) {
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

sealed interface MediaContent: MessageContent {
    val media: TelegramMediaFile
    fun asTelegramMedia(): TelegramMedia
}

@ClassCastsIncluded
sealed interface ResendableContent {
    fun createResend(
        chatId: ChatIdentifier,
        disableNotification: Boolean = false,
        protectContent: Boolean = false,
        replyToMessageId: MessageId? = null,
        allowSendingWithoutReply: Boolean? = null,
        replyMarkup: KeyboardMarkup? = null
    ): Request<out Message>
}
