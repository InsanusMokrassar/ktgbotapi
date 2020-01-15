package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.RawChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

interface ChatMember {
    val user: User
}

internal object AdministratorChatMemberSerializerWithoutDeserialization : KSerializer<AdministratorChatMember> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("ChatMemberSerializerWithoutDeserialization")

    override fun deserialize(decoder: Decoder): AdministratorChatMember
        = ChatMemberDeserializationStrategy.deserialize(decoder) as AdministratorChatMember
    override fun serialize(encoder: Encoder, obj: AdministratorChatMember) = throw UnsupportedOperationException()
}

internal object ChatMemberDeserializationStrategy : DeserializationStrategy<ChatMember> {
    override val descriptor: SerialDescriptor = StringDescriptor.withName("ChatMemberDeserializationStrategy")

    override fun deserialize(decoder: Decoder): ChatMember = RawChatMember.serializer().deserialize(decoder).asChatMember
    override fun patch(decoder: Decoder, old: ChatMember): ChatMember = throw UpdateNotSupportedException(descriptor.name)
}
