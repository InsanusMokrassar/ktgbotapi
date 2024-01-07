package dev.inmo.tgbotapi.types

import dev.inmo.tgbotapi.abstracts.SpoilerableData
import dev.inmo.tgbotapi.types.chat.SuperPublicChat
import dev.inmo.tgbotapi.types.dice.Dice
import dev.inmo.tgbotapi.types.files.*
import dev.inmo.tgbotapi.types.games.RawGame
import dev.inmo.tgbotapi.types.giveaway.GiveawayPublicResults
import dev.inmo.tgbotapi.types.giveaway.ScheduledGiveaway
import dev.inmo.tgbotapi.types.location.Location
import dev.inmo.tgbotapi.types.message.MessageOrigin
import dev.inmo.tgbotapi.types.message.abstracts.AccessibleMessage
import dev.inmo.tgbotapi.types.message.abstracts.Message
import dev.inmo.tgbotapi.types.payments.Invoice
import dev.inmo.tgbotapi.types.polls.Poll
import dev.inmo.tgbotapi.types.stories.Story
import dev.inmo.tgbotapi.types.venue.Venue
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@ClassCastsIncluded
sealed interface ReplyInfo {
    val messageMeta: Message.MetaInfo?

    data class Internal(
        val message: AccessibleMessage
    ): ReplyInfo {
        override val messageMeta: Message.MetaInfo
            get() = message.metaInfo
    }

    @Serializable(External.Companion::class)
    sealed interface External : ReplyInfo {
        val origin: MessageOrigin
        val chat: SuperPublicChat?

        interface ContentVariant

        @Serializable
        data class Text(
            override val origin: MessageOrigin,
            override val chat: SuperPublicChat?,
            override val messageMeta: Message.MetaInfo?,
            val linkPreviewOptions: LinkPreviewOptions?
        ) : External

        @Serializable(External.Companion::class)
        sealed interface Content : External {
            val content: ContentVariant


            @Serializable
            data class Simple(
                override val origin: MessageOrigin,
                override val chat: SuperPublicChat?,
                override val messageMeta: Message.MetaInfo?,
                override val content: ContentVariant
            ) : Content

            @Serializable
            data class Media<T>(
                override val origin: MessageOrigin,
                override val chat: SuperPublicChat?,
                override val messageMeta: Message.MetaInfo?,
                override val spoilered: Boolean,
                override val content: T
            ) : Content, SpoilerableData where T: ContentVariant, T : TelegramMediaFile
        }

        @Serializable
        private data class Surrogate(
            val origin: MessageOrigin,
            val chat: SuperPublicChat? = null,
            val message_id: MessageId? = null,
            val link_preview_options: LinkPreviewOptions? = null,
            val has_media_spoiler: Boolean? = null,
            private val story: Story? = null,
            private val audio: AudioFile? = null,
            private val document: DocumentFile? = null,
            private val animation: AnimationFile? = null,
            private val game: RawGame? = null,
            @Serializable(PhotoSerializer::class)
            private val photo: Photo? = null,
            private val sticker: Sticker? = null,
            private val video: VideoFile? = null,
            private val voice: VoiceFile? = null,
            private val video_note: VideoNoteFile? = null,
            private val contact: Contact? = null,
            private val location: Location? = null,
            private val venue: Venue? = null,
            private val poll: Poll? = null,
            private val invoice: Invoice? = null,
            private val dice: Dice? = null,
            private val giveaway: ScheduledGiveaway? = null,
            private val giveaway_winners: GiveawayPublicResults? = null,
        ) {
            val asExternalReplyInfo: External
                get() {
                    val messageMeta = chat ?.let {
                        message_id ?.let {
                            Message.MetaInfo(
                                chat.id,
                                message_id
                            )
                        }
                    }
                    val content: ContentVariant? = when {
                        story != null -> story
                        audio != null -> audio
                        video != null -> video
                        video_note != null -> video_note
                        animation != null -> animation
                        document != null -> document
                        voice != null -> voice
                        photo != null -> photo
                        sticker != null -> sticker
                        dice != null -> dice
                        game != null -> game.asGame
                        contact != null -> contact
                        location != null -> location
                        venue != null -> venue
                        poll != null -> poll
                        invoice != null -> invoice
                        giveaway != null -> giveaway
                        giveaway_winners != null -> giveaway_winners
                        else -> null
                    }

                    return content ?.let {
                        when (it) {
                            is TelegramMediaFile -> {
                                Content.Media(
                                    origin,
                                    chat,
                                    messageMeta,
                                    has_media_spoiler == true,
                                    it
                                )
                            }
                            else -> Content.Simple(
                                origin,
                                chat,
                                messageMeta,
                                it
                            )
                        }
                    } ?: Text(
                        origin,
                        chat,
                        messageMeta,
                        link_preview_options
                    )
                }
        }

        @RiskFeature("This serializer currently support only deserialization, but not serialization")
        companion object : KSerializer<External> {
            override val descriptor: SerialDescriptor
                get() = Surrogate.serializer().descriptor

            override fun deserialize(decoder: Decoder): External {
                return Surrogate.serializer().deserialize(decoder).asExternalReplyInfo
            }

            override fun serialize(encoder: Encoder, value: External) {

            }
        }
    }
}