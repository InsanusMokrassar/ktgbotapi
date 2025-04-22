package dev.inmo.tgbotapi.types.chat.member

import dev.inmo.tgbotapi.utils.RiskFeature
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(AdministratorChatMemberSerializer::class)
sealed interface AdministratorChatMember : SpecialRightsChatMember, ChatAdministratorRights {
    val canBeEdited: Boolean
    val customTitle: String?

    val canManageVoiceChats: Boolean
        get() = canManageVideoChats
}

@RiskFeature
object AdministratorChatMemberSerializer : KSerializer<AdministratorChatMember> {
    override val descriptor: SerialDescriptor = ChatMemberSerializer.descriptor

    override fun deserialize(decoder: Decoder): AdministratorChatMember =
        ChatMemberSerializer.deserialize(
            decoder,
        ) as AdministratorChatMember

    override fun serialize(
        encoder: Encoder,
        value: AdministratorChatMember,
    ) = ChatMemberSerializer.serialize(encoder, value)
}
