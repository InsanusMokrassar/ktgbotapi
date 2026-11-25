package dev.inmo.tgbotapi.types.message.ChatEvents.forum

import dev.inmo.tgbotapi.types.chat.User
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.ForumEvent
import dev.inmo.tgbotapi.types.message.ChatEvents.abstracts.PrivateEvent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import dev.inmo.micro_utils.serialization.mapper.MapperSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(WriteAccessAllowed.Companion::class)
sealed interface WriteAccessAllowed : PrivateEvent, ForumEvent {
    val webAppName: String?
        get() = null
    val fromRequest: Boolean
        get() = false
    val fromAttachmentMenu: Boolean
        get() = false
    /** Bot owner who invited the user to the private chat. */
    val inviter: User?
        get() = null

    @Serializable
    object Other : WriteAccessAllowed
    @Serializable
    data class FromWebAppLink(
        override val webAppName: String
    ) : WriteAccessAllowed
    @Serializable
    object FromRequest : WriteAccessAllowed {
        override val fromRequest: Boolean
            get() = true
    }

    @Serializable
    object FromAttachmentMenu : WriteAccessAllowed {
        override val fromAttachmentMenu: Boolean
            get() = true
    }

    @Serializable(Companion.ForInvitation::class)
    data class FromInvitation(override val inviter: User) : WriteAccessAllowed

    @Serializable
    private class WriteAccessAllowedRaw(
        val web_app_name: String? = null,
        val from_request: Boolean = false,
        val from_attachment_menu: Boolean = false,
        val inviter: User? = null
    )

    companion object : KSerializer<WriteAccessAllowed> {
        object ForInvitation : MapperSerializer<WriteAccessAllowed, FromInvitation>(
            Companion, { value -> value }, { value ->
                value as? FromInvitation ?: throw SerializationException("Expected a bot owner invitation")
            }
        )

        override val descriptor: SerialDescriptor
            get() = WriteAccessAllowedRaw.serializer().descriptor

        override fun deserialize(decoder: Decoder): WriteAccessAllowed {
            val raw = WriteAccessAllowedRaw.serializer().deserialize(decoder)

            return when {
                raw.inviter != null -> FromInvitation(raw.inviter)
                raw.web_app_name != null -> FromWebAppLink(raw.web_app_name)
                raw.from_request -> FromRequest
                raw.from_attachment_menu -> FromAttachmentMenu
                else -> Other
            }
        }

        override fun serialize(encoder: Encoder, value: WriteAccessAllowed) {
            val raw = when (value) {
                is FromInvitation -> WriteAccessAllowedRaw(inviter = value.inviter)
                FromAttachmentMenu -> WriteAccessAllowedRaw(from_attachment_menu = true)
                FromRequest -> WriteAccessAllowedRaw(from_request = true)
                Other -> WriteAccessAllowedRaw()
                is FromWebAppLink -> WriteAccessAllowedRaw(web_app_name = value.webAppName)
            }
            WriteAccessAllowedRaw.serializer().serialize(encoder, raw)
        }

        operator fun invoke(webAppName: String?): WriteAccessAllowed = webAppName ?.let(::FromWebAppLink) ?: Other
    }
}
