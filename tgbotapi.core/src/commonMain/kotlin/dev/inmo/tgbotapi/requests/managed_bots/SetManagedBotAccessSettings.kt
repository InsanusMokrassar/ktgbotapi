package dev.inmo.tgbotapi.requests.managed_bots

import dev.inmo.tgbotapi.requests.abstracts.SimpleRequest
import dev.inmo.tgbotapi.types.ChatId
import dev.inmo.tgbotapi.types.addedUserIdsField
import dev.inmo.tgbotapi.types.isAccessRestrictedField
import dev.inmo.tgbotapi.types.userIdField
import dev.inmo.tgbotapi.utils.serializers.UnitFromBooleanSerializer
import kotlinx.serialization.*

@Serializable
data class SetManagedBotAccessSettings(
    @SerialName(userIdField)
    val userId: ChatId,
    @SerialName(addedUserIdsField)
    val addedUserIds: List<ChatId>? = null
) : SimpleRequest<Unit> {
    @EncodeDefault
    @SerialName(isAccessRestrictedField)
    val isAccessRestricted: Boolean = addedUserIds != null
    override fun method(): String = "setManagedBotAccessSettings"
    override val resultDeserializer: DeserializationStrategy<Unit>
        get() = UnitFromBooleanSerializer
    override val requestSerializer: SerializationStrategy<*>
        get() = serializer()
}
