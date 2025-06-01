package dev.inmo.tgbotapi.types.business_connection

import dev.inmo.tgbotapi.abstracts.types.WithBusinessConnectionId
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.PreviewUser
import dev.inmo.tgbotapi.utils.internal.ClassCastsIncluded
import kotlinx.serialization.*
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(BusinessConnection.Companion::class)
@ClassCastsIncluded
sealed interface BusinessConnection : WithBusinessConnectionId {
    val id: BusinessConnectionId
    val user: PreviewUser
    val userChatId: ChatId
    val date: TelegramDate
    val rights: BusinessBotRights
    val canReply: Boolean
        get() = rights.canReply
    val isEnabled: Boolean

    override val businessConnectionId: BusinessConnectionId
        get() = id

    @Serializable
    data class Enabled(
        @SerialName(idField)
        override val id: BusinessConnectionId,
        @SerialName(userField)
        override val user: PreviewUser,
        @SerialName(userChatIdField)
        override val userChatId: ChatId,
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(rightsField)
        override val rights: BusinessBotRights = BusinessBotRights(),
    ) : BusinessConnection {
        @EncodeDefault
        override val isEnabled: Boolean = true
    }

    @Serializable
    data class Disabled(
        @SerialName(idField)
        override val id: BusinessConnectionId,
        @SerialName(userField)
        override val user: PreviewUser,
        @SerialName(userChatIdField)
        override val userChatId: ChatId,
        @SerialName(dateField)
        override val date: TelegramDate,
        @SerialName(rightsField)
        override val rights: BusinessBotRights = BusinessBotRights(),
    ) : BusinessConnection {
        @EncodeDefault
        override val isEnabled: Boolean = false
    }

    companion object : KSerializer<BusinessConnection> {
        override val descriptor: SerialDescriptor
            get() = RawBusinessConnection.serializer().descriptor

        override fun deserialize(decoder: Decoder): BusinessConnection {
            return RawBusinessConnection.serializer().deserialize(decoder).asBusinessConnection

        }

        override fun serialize(encoder: Encoder, value: BusinessConnection) {
            RawBusinessConnection.serializer().serialize(encoder, RawBusinessConnection(value))
        }
    }
}
