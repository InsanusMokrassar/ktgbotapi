@file:OptIn(ExperimentalSerializationApi::class)

package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable(MessageOrigin.Companion::class)
sealed interface MessageOrigin {
    val type: String
    val date: TelegramDate

    @Serializable
    data class User(
        @SerialName(senderUserField)
        val user: dev.inmo.tgbotapi.types.chat.User,
        @SerialName(dateField)
        override val date: TelegramDate
    ) : MessageOrigin {
        @SerialName(typeField)
        @Required
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            val type: String = "user"
        }
    }

    @Serializable
    data class HiddenUser(
        @SerialName(senderUserNameField)
        val name: String,
        @SerialName(dateField)
        override val date: TelegramDate
    ) : MessageOrigin {
        @SerialName(typeField)
        @Required
        @EncodeDefault
        override val type: String = Companion.type

        companion object {
            val type: String = "hidden_user"
        }
    }

    @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
    @Serializable(MessageOrigin.Companion::class)
    sealed interface Public : MessageOrigin {
        val chat: PublicChat
        val authorSignature: AuthorSignature?

        @Serializable
        data class Sender(
            @SerialName(senderChatField)
            override val chat: SuperPublicChat,
            @SerialName(dateField)
            override val date: TelegramDate,
            @SerialName(authorSignatureField)
            override val authorSignature: AuthorSignature? = null
        ) : Public {
            @SerialName(typeField)
            @Required
            @EncodeDefault
            override val type: String = Companion.type

            companion object {
                val type: String = "chat"
            }
        }

        @Serializable
        data class Channel(
            @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
            @SerialName(chatField)
            override val chat: ChannelChat,
            @SerialName(messageIdField)
            val messageId: MessageId,
            @SerialName(dateField)
            override val date: TelegramDate,
            @SerialName(authorSignatureField)
            override val authorSignature: AuthorSignature? = null
        ) : Public {
            @SerialName(typeField)
            @Required
            @EncodeDefault
            override val type: String = Companion.type

            companion object {
                val type: String = "channel"
            }
        }
    }

    @ConsistentCopyVisibility
    @Serializable
    data class Unknown internal constructor(
        override val type: String,
        override val date: TelegramDate,
        val source: JsonElement?
    ) : MessageOrigin

    @Serializable
    private data class Surrogate(
        @SerialName(typeField)
        @Required
        @EncodeDefault
        val type: String,
        @SerialName(senderChatField)
        val senderChat: PreviewChat? = null,
        @SerialName(chatField)
        val chat: PreviewChat? = null,
        @SerialName(dateField)
        val date: TelegramDate,
        @SerialName(authorSignatureField)
        val authorSignature: AuthorSignature? = null,
        @SerialName(messageIdField)
        val messageId: MessageId? = null,
        @SerialName(senderUserNameField)
        val name: String? = null,
        @Suppress("SERIALIZER_TYPE_INCOMPATIBLE")
        @SerialName(senderUserField)
        val user: PreviewUser? = null
    )

    companion object : KSerializer<MessageOrigin> {
        override val descriptor: SerialDescriptor
            get() = Surrogate.serializer().descriptor

        override fun deserialize(decoder: Decoder): MessageOrigin {
            val (surrogate, json) = if (decoder is JsonDecoder) {
                val json = decoder.decodeJsonElement()
                val surrogate = decoder.json.decodeFromJsonElement(Surrogate.serializer(), json)
                surrogate to json
            } else {
                Surrogate.serializer().deserialize(decoder) to null
            }

            return let {
                when (surrogate.type) {
                    HiddenUser.type -> HiddenUser(
                        surrogate.name ?: return@let null,
                        surrogate.date
                    )
                    Public.Channel.type -> Public.Channel(
                        surrogate.chat as? ChannelChat ?: return@let null,
                        surrogate.messageId ?: return@let null,
                        surrogate.date,
                        surrogate.authorSignature
                    )
                    Public.Sender.type -> Public.Sender(
                        surrogate.senderChat as? ChannelChat ?: return@let null,
                        surrogate.date,
                        surrogate.authorSignature
                    )
                    User.type -> User(
                        surrogate.user ?: return@let null,
                        surrogate.date
                    )
                    else -> null
                }
            } ?: Unknown(
                surrogate.type,
                surrogate.date,
                json
            )
        }

        override fun serialize(encoder: Encoder, value: MessageOrigin) {
            when (value) {
                is HiddenUser -> HiddenUser.serializer().serialize(encoder, value)
                is Public.Channel -> Public.Channel.serializer().serialize(encoder, value)
                is Public.Sender -> Public.Sender.serializer().serialize(encoder, value)
                is Unknown -> value.source ?.let { JsonElement.serializer().serialize(encoder, it) } ?: Unknown.serializer().serialize(encoder, value)
                is User -> User.serializer().serialize(encoder, value)
            }
        }

    }
}