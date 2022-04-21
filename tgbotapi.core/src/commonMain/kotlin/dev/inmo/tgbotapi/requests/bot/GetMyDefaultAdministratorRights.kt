package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.member.AdministratorChatMemberImpl
import kotlinx.serialization.*

@Serializable
data class GetMyDefaultAdministratorRights(
    @SerialName(forChannelsField)
    val forChannels: Boolean? = null
) : SimpleRequest<AdministratorChatMemberImpl> {
    override fun method(): String = "getMyDefaultAdministratorRights"
    override val resultDeserializer: DeserializationStrategy<AdministratorChatMemberImpl>
        get() = AdministratorChatMemberImpl.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()

    companion object {
        val ForChannels = GetMyDefaultAdministratorRights(true)
        val ForGroups = GetMyDefaultAdministratorRights(false)
    }
}
