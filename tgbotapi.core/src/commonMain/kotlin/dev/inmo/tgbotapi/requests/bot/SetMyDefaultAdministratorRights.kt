package dev.inmo.tgbotapi.requests.bot

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.*
import dev.inmo.tgbotapi.types.chat.member.ChatCommonAdministratorRights
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*
import kotlinx.serialization.builtins.serializer

@Serializable
class SetMyDefaultAdministratorRights(
    @SerialName(rightsField)
    val rights: ChatCommonAdministratorRights,
    @SerialName(forChannelsField)
    val forChannels: Boolean? = null
) : SimpleRequest<Unit> {
    override fun method(): String = "setMyDefaultAdministratorRights"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
