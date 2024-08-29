package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.abstracts.WithUser
import dev.inmo.tgbotapi.types.statusField
import dev.inmo.tgbotapi.types.untilDateField
import dev.inmo.tgbotapi.utils.RiskFeature
import dev.inmo.tgbotapi.utils.nonstrictJsonFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable(ChatMemberSerializer::class)
sealed interface ChatMember : WithUser {
    @Serializable(StatusSerializer::class)
    enum class Status(
        val status: String,
        val deserializationStrategy: DeserializationStrategy<ChatMember>,
        val checker: (String, JsonObject) -> Boolean = { outerStatus, _ -> outerStatus == status },
    ) {
        Creator("creator", OwnerChatMember.serializer()),
        Administrator("administrator", AdministratorChatMemberImpl.serializer()),
        Member("member", MemberChatMemberImpl.serializer(), { status, json -> status == "member" && json[untilDateField] ?.jsonPrimitive == null }),
        SubscriptionMember("member", SubscriptionMemberChatMemberImpl.serializer(), { status, json -> status == "member" && json[untilDateField] ?.jsonPrimitive != null }),
        Restricted("restricted", RestrictedMemberChatMember.serializer()),
        Left("left", LeftChatMemberImpl.serializer()),
        Kicked("kicked", KickedChatMember.serializer())
    }

    object StatusSerializer : KSerializer<Status> {
        override val descriptor: SerialDescriptor
            get() = String.serializer().descriptor

        override fun deserialize(decoder: Decoder): Status {
            val status = decoder.decodeString()
            return Status.values().first {
                it.status == status
            }
        }

        override fun serialize(encoder: Encoder, value: Status) {
            encoder.encodeString(value.status)
        }
    }

    val status: Status
}

@RiskFeature
object ChatMemberSerializer : KSerializer<ChatMember> {
    override val descriptor: SerialDescriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): ChatMember {
        val json = JsonObject.serializer().deserialize(decoder)
        val status = json[statusField] ?.jsonPrimitive ?.content ?: error("Status field of chat member must be specified, but incoming json contains next: $json")
        return ChatMember.Status.values().firstNotNullOfOrNull {
            if (it.checker(status, json)) {
                nonstrictJsonFormat.decodeFromJsonElement(it.deserializationStrategy, json)
            } else {
                null
            }
        } ?: error("Unknown type of chat member in json: $json")
    }

    override fun serialize(encoder: Encoder, value: ChatMember) {
        when (value) {
            is OwnerChatMember -> OwnerChatMember.serializer().serialize(encoder, value)
            is AdministratorChatMemberImpl -> AdministratorChatMemberImpl.serializer().serialize(encoder, value)
            is SubscriptionMemberChatMemberImpl -> SubscriptionMemberChatMemberImpl.serializer().serialize(encoder, value)
            is MemberChatMemberImpl -> MemberChatMemberImpl.serializer().serialize(encoder, value)
            is RestrictedMemberChatMember -> RestrictedMemberChatMember.serializer().serialize(encoder, value)
            is LeftChatMemberImpl -> LeftChatMemberImpl.serializer().serialize(encoder, value)
            is KickedChatMember -> KickedChatMember.serializer().serialize(encoder, value)
        }
    }
}
