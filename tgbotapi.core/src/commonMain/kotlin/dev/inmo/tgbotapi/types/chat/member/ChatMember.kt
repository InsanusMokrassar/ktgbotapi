package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.statusField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(ChatMemberSerializer::class)
sealed interface ChatMember : WithUser {
    @Serializable
    enum class Status(val status: String) {
        Creator("creator"),
        Administrator("administrator"),
        Member("member"),
        Restricted("restricted"),
        Left("left"),
        Kicked("kicked")
    }
    val status: Status
}

@RiskFeature
object ChatMemberSerializer : KSerializer<ChatMember> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): ChatMember {
        val json = JsonObject.serializer().deserialize(decoder)
        val status = ChatMember.Status.valueOf(
            json[statusField] ?.jsonPrimitive ?.content ?: error("Status field of chat member must be specified, but incoming json contains next: $json")
        )
        return when (status) {
            ChatMember.Status.Creator -> nonstrictJsonFormat.decodeFromJsonElement(OwnerChatMember.serializer(), json)
            ChatMember.Status.Administrator -> nonstrictJsonFormat.decodeFromJsonElement(AdministratorChatMemberImpl.serializer(), json)
            ChatMember.Status.Member -> nonstrictJsonFormat.decodeFromJsonElement(MemberChatMemberImpl.serializer(), json)
            ChatMember.Status.Restricted -> nonstrictJsonFormat.decodeFromJsonElement(RestrictedChatMember.serializer(), json)
            ChatMember.Status.Left -> nonstrictJsonFormat.decodeFromJsonElement(LeftChatMemberImpl.serializer(), json)
            ChatMember.Status.Kicked -> nonstrictJsonFormat.decodeFromJsonElement(KickedChatMember.serializer(), json)
        }
    }

    override fun serialize(encoder: Encoder, value: ChatMember) {
        when (value) {
            is OwnerChatMember -> OwnerChatMember.serializer().serialize(encoder, value)
            is AdministratorChatMemberImpl -> AdministratorChatMemberImpl.serializer().serialize(encoder, value)
            is MemberChatMemberImpl -> MemberChatMemberImpl.serializer().serialize(encoder, value)
            is RestrictedChatMember -> RestrictedChatMember.serializer().serialize(encoder, value)
            is LeftChatMemberImpl -> LeftChatMemberImpl.serializer().serialize(encoder, value)
            is KickedChatMember -> KickedChatMember.serializer().serialize(encoder, value)
        }
    }
}
