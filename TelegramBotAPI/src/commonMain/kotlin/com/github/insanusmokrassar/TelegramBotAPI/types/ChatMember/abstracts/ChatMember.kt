package com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.abstracts

import com.github.insanusmokrassar.TelegramBotAPI.types.ChatMember.RawChatMember
import com.github.insanusmokrassar.TelegramBotAPI.types.User
import kotlinx.serialization.*

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
    override fun patch(decoder: Decoder, old: ChatMember): ChatMember = throw UpdateNotSupportedException("ChatMember")
}
