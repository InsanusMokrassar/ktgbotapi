package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
class SetMyDefaultAdministratorRights(
    @SerialName(rightsField)
    val rights: ChatCommonAdministratorRights,
    @SerialName(forChannelsField)
    val forChannels: Boolean? = null,
) : SimpleRequest<Boolean> {
    override fun method(): String = "setMyDefaultAdministratorRights"

    override val resultDeserializer: DeserializationStrategy<Boolean>
        get() = Boolean.serializer()
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
