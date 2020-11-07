package dev.inmo.tgbotapi.types.ChatMember.abstracts

import dev.inmo.tgbotapi.types.ChatMember.RawChatMember
import dev.inmo.tgbotapi.types.User
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

interface ChatMember {
    val user: User
}

internal object AdministratorChatMemberSerializerWithoutDeserialization : KSerializer<AdministratorChatMember> {
    override val descriptor: SerialDescriptor = ChatMemberDeserializationStrategy.descriptor

    override fun deserialize(decoder: Decoder): AdministratorChatMember
        = ChatMemberDeserializationStrategy.deserialize(decoder) as AdministratorChatMember
    override fun serialize(encoder: Encoder, value: AdministratorChatMember) = throw UnsupportedOperationException()
}

internal object ChatMemberDeserializationStrategy : DeserializationStrategy<ChatMember> {
    override val descriptor: SerialDescriptor = RawChatMember.serializer().descriptor

    override fun deserialize(decoder: Decoder): ChatMember = RawChatMember.serializer().deserialize(decoder).asChatMember
}
