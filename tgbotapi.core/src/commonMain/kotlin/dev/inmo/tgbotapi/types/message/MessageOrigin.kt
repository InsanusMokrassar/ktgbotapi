package dev.inmo.tgbotapi.types.message

import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.*
import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
        override val type: String = Companion.type

        companion object {
            val type: String = "hidden_user"
        }
    }

    @Serializable
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
            override val type: String = Companion.type

            companion object {
                val type: String = "chat"
            }
        }

        @Serializable
        data class Channel(
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
            override val type: String = Companion.type

            companion object {
                val type: String = "channel"
            }
        }
    }
}